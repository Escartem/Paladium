package fr.paladium.palaschematicmod.server.command;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameterTabComplete;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicCopySession;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import fr.paladium.palaschematicmod.server.async.PasteCallback;
import fr.paladium.palaschematicmod.server.manager.PalaSchematicManager;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import scala.actors.threadpool.Arrays;

@Command(command = {"palaschematicmod", "psm"}, description = "Utiliser PalaSchematic")
public class PalaSchematicCommand {
  private static final Set<String> undoInProgress = new HashSet<>();
  
  @SubCommand(command = "palaschematicmod copy", description = "Copier une sélection dans le clipboard", permission = "palaschematic.command.copy", sender = {SenderType.PLAYER})
  public void copy(CommandContext context) {
    EntityPlayerMP player = context.getPlayer();
    SchematicCopySession session = PalaSchematicManager.getInstance().getOrCreateSession((EntityPlayer)player);
    if (session == null || session.firstPos == null || session.secondPos == null) {
      context.error("Veuillez faire une sélection avec la Hache en Diamant.");
      return;
    } 
    Schematic schematic = new Schematic(player.func_110124_au().toString(), player.func_70005_c_(), 1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    context.send("§7Copie de la sélection en cours...");
    BlockPos center = new BlockPos((Entity)player);
    SchematicUtils.generateSchematicData(player.field_70170_p, center, schematic, session);
    context.send("§7Sélection copiée : §e" + schematic.getBlocks().size() + "§7 blocs.");
    SchematicUtils.saveClipboard(schematic, player.func_110124_au().toString());
    context.success("Copiée dans le clipboard. Utilisez §e/psm paste§a pour coller.");
  }
  
  @SubCommand(command = "palaschematicmod save <name>", description = "Sauvegarder une sélection comme schematic", permission = "palaschematic.command.save", sender = {SenderType.PLAYER})
  public void save(CommandContext context, String name) {
    EntityPlayerMP player = context.getPlayer();
    SchematicCopySession session = PalaSchematicManager.getInstance().getOrCreateSession((EntityPlayer)player);
    if (session == null || session.firstPos == null || session.secondPos == null) {
      context.error("Veuillez faire une sélection avec la Hache en Diamant.");
      return;
    } 
    Schematic schematic = new Schematic(name, player.func_70005_c_(), 1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    context.send("§7Génération de la schematic en cours...");
    BlockPos center = new BlockPos((Entity)player);
    SchematicUtils.generateSchematicData(player.field_70170_p, center, schematic, session);
    context.send("§7Schematic générée : §e" + schematic.getBlocks().size() + "§7 blocs.");
    SchematicUtils.saveSchematic(schematic);
    context.success("Schematic §e" + schematic.getName() + "§a créée avec succès.");
  }
  
  @SubCommand(command = "palaschematicmod paste [<options>]", description = "Coller le clipboard", permission = "palaschematic.command.paste", sender = {SenderType.PLAYER})
  public void paste(CommandContext context, @CommandParameter(description = "-a (don't paste air) -e (paste entities) -u (disable undo) -d (don't paste NBT data)") Optional<String> options) {
    executePaste(context, "clipboard", true, options);
  }
  
  @SubCommand(command = "palaschematicmod paste <name> [<options>]", description = "Coller une schematic", permission = "palaschematic.command.paste", sender = {SenderType.PLAYER})
  public void pasteNamed(CommandContext context, @CommandParameter(tabComplete = @CommandParameterTabComplete(method = "listAllSchem")) String name, @CommandParameter(description = "-a (don't paste air) -e (paste entities) -u (disable undo) -d (don't paste NBT data)") Optional<String> options) {
    if ("#clipboard".equalsIgnoreCase(name)) {
      executePaste(context, "clipboard", true, options);
      return;
    } 
    executePaste(context, name, false, options);
  }
  
  private void executePaste(CommandContext context, String name, boolean fromClipboard, Optional<String> options) {
    EntityPlayerMP player = context.getPlayer();
    BlockPos blockPos = new BlockPos((Entity)player);
    List<String> opts = options.isPresent() ? Arrays.asList((Object[])((String)options.get()).toLowerCase().split(" ")) : new ArrayList<>();
    context.send("§7Chargement de la schematic §e" + name + "§7...");
    (new Thread(() -> {
          Schematic schematic = fromClipboard ? SchematicUtils.loadClipboard(player.func_110124_au().toString()) : SchematicUtils.loadSchematic(name);
          if (schematic == null) {
            if (fromClipboard) {
              context.error("Aucune sélection dans le clipboard. Utilisez §e/psm copy§c d'abord.");
            } else {
              context.error("La schematic §e" + name + "§c n'a pas été trouvée.");
            } 
            return;
          } 
          boolean createUndo = !opts.contains("-u");
          if (createUndo) {
            context.send("§7Préparation de l'undo...");
            int minX = blockPos.getX() + schematic.getMinPoint().getX();
            int maxX = blockPos.getX() + schematic.getMaxPoint().getX();
            int minY = blockPos.getY() + schematic.getMinPoint().getY();
            int maxY = blockPos.getY() + schematic.getMaxPoint().getY();
            int minZ = blockPos.getZ() + schematic.getMinPoint().getZ();
            int maxZ = blockPos.getZ() + schematic.getMaxPoint().getZ();
            Schematic undoSchematic = new Schematic(player.func_110124_au().toString(), player.func_70005_c_(), 1, System.currentTimeMillis());
            SchematicUtils.generateSchematicDataFromRegion(player.field_70170_p, blockPos, undoSchematic, minX, maxX, minY, maxY, minZ, maxZ);
            SchematicUtils.saveUndoSchematic(undoSchematic, player.func_110124_au().toString());
            SchematicUtils.saveUndoSession(player.func_110124_au().toString(), new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        }"PalaSchematicMod/Load-" + name))
      
      .start();
  }
  
  @SubCommand(command = "palaschematicmod convert <name>", description = "Convertir une schematic WorldEdit en PalaSchematic", permission = "palaschematic.command.convert")
  public void convert(CommandContext context, @CommandParameter(tabComplete = @CommandParameterTabComplete(method = "listWorldEditSchem")) String name) {
    context.send("§7Conversion de la schematic §e" + name + "§7...");
    SchematicUtils.convertWorldEditSchematicAsync(name, result -> {
          if (!result.isSuccess()) {
            context.error(result.getMessage());
            return;
          } 
          if (result.getSchematic() != null) {
            context.success("Schematic §e" + name + "§a convertie avec succès : §e" + result.getSchematic().getBlocks().size() + "§a blocs.");
          } else if (result.getTotalBlocks() > 0) {
            float progress = result.getProcessedBlocks() * 100.0F / result.getTotalBlocks();
            context.send("§7" + result.getMessage() + " §e" + result.getProcessedBlocks() + "§7/§e" + result.getTotalBlocks() + " §8(" + String.format("%.2f", new Object[] { Float.valueOf(progress) }) + "%)§7.");
          } else {
            context.send(result.getMessage());
          } 
        });
  }
  
  @SubCommand(command = "palaschematicmod list", description = "Lister les schematics disponibles", permission = "palaschematic.command.list")
  public void list(CommandContext context) {
    File[] schematics = SchematicUtils.listSchematics();
    if (schematics == null || schematics.length == 0) {
      context.send("§7Aucune schematic disponible.");
      return;
    } 
    context.send("");
    context.send("§6Liste des schematics :");
    for (File file : schematics)
      context.send(" §7• §e" + file.getName()); 
  }
  
  @SubCommand(command = "palaschematicmod position <number>", description = "Définir une position pour la sélection", permission = "palaschematic.command.position", sender = {SenderType.PLAYER})
  public void position(CommandContext context, @CommandParameter(tabComplete = @CommandParameterTabComplete({"1", "2"})) int pos) {
    if (PalaSchematicManager.getInstance().setSchematicSession((EntityPlayer)context.getPlayer(), new BlockPos((Entity)context.getPlayer()), pos)) {
      context.success("Position §e" + pos + "§a définie avec succès.");
      return;
    } 
    context.error("Erreur lors de la définition de la position.");
  }
  
  @SubCommand(command = "palaschematicmod undo [<confirm>]", description = "Annuler le dernier paste", permission = "palaschematic.command.undo", sender = {SenderType.PLAYER})
  public void undo(CommandContext context, @CommandParameter(tabComplete = @CommandParameterTabComplete({"confirm"})) Optional<String> confirm) {
    EntityPlayerMP player = context.getPlayer();
    String playerUUID = player.func_110124_au().toString();
    synchronized (undoInProgress) {
      if (undoInProgress.contains(playerUUID)) {
        context.error("Un undo est déjà en cours.");
        return;
      } 
      undoInProgress.add(playerUUID);
    } 
    context.send("§7Chargement de l'undo...");
    (new Thread(() -> {
          Schematic undoSchematic = SchematicUtils.loadUndoSchematic(playerUUID);
          if (undoSchematic == null) {
            context.error("Aucun undo disponible.");
            synchronized (undoInProgress) {
              undoInProgress.remove(playerUUID);
            } 
            return;
          } 
          BlockPos absoluteOrigin = SchematicUtils.loadUndoSession(playerUUID);
          if (absoluteOrigin == null) {
            context.error("Session d'undo corrompue.");
            synchronized (undoInProgress) {
              undoInProgress.remove(playerUUID);
            } 
            return;
          } 
          long schematicAge = System.currentTimeMillis() - undoSchematic.getDate();
          long oneHourInMillis = 3600000L;
          if (schematicAge > 3600000L && !confirm.isPresent()) {
            long hours = schematicAge / 3600000L;
            context.send("§c⚠ Attention : Cet undo date de §e" + hours + "h§c.");
            context.send("§7Tapez §e/psm undo confirm§7 pour confirmer.");
            synchronized (undoInProgress) {
              undoInProgress.remove(playerUUID);
            } 
            return;
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
        }"PalaSchematicMod/Undo-" + playerUUID))
      
      .start();
  }
  
  @SideOnly(Side.SERVER)
  public String[] listAllSchem(CommandContext context) {
    File[] schematics = SchematicUtils.listSchematics();
    if (schematics == null || schematics.length == 0)
      return new String[0]; 
    String[] names = new String[schematics.length];
    for (int i = 0; i < schematics.length; i++)
      names[i] = schematics[i].getName().replace(".palaschematic_v2", "").replace(".palaschematic", ""); 
    return names;
  }
  
  @SideOnly(Side.SERVER)
  public String[] listWorldEditSchem(CommandContext context) {
    File[] schematics = SchematicUtils.listWorldEditSchematics();
    if (schematics == null || schematics.length == 0)
      return new String[0]; 
    String[] names = new String[schematics.length];
    for (int i = 0; i < schematics.length; i++)
      names[i] = schematics[i].getName().replace(".schematic", ""); 
    return names;
  }
  
  private void parseReplaceArgument(String optionsString, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, CommandContext context) {
    String[] optionsParts = optionsString.split(" ");
    String replaceArg = null;
    for (int i = 0; i < optionsParts.length; i++) {
      if ("-r".equalsIgnoreCase(optionsParts[i]) && i + 1 < optionsParts.length) {
        replaceArg = optionsParts[i + 1];
        break;
      } 
    } 
    if (replaceArg == null || replaceArg.isEmpty())
      return; 
    String[] replacePairs = replaceArg.split(",");
    for (String pair : replacePairs) {
      String[] keyValue = pair.split(";");
      if (keyValue.length != 2) {
        context.send("§cFormat invalide pour la paire: " + pair);
      } else {
        String keyStr = keyValue[0].trim();
        String valueStr = keyValue[1].trim();
        Map.Entry<String, Byte> keyEntry = parseMaterialData(keyStr);
        if (keyEntry == null) {
          context.send("§cFormat invalide pour la clé: " + keyStr);
        } else {
          Map.Entry<String, Byte> valueEntry = parseMaterialData(valueStr);
          if (valueEntry == null) {
            context.send("§cFormat invalide pour la valeur: " + valueStr);
          } else if (((Byte)keyEntry.getValue()).byteValue() == -1) {
            byte data;
            for (data = 0; data <= 15; data = (byte)(data + 1)) {
              Map.Entry<String, Byte> key = new AbstractMap.SimpleEntry<>(keyEntry.getKey(), Byte.valueOf(data));
              replaceMap.put(key, valueEntry);
            } 
          } else {
            replaceMap.put(keyEntry, valueEntry);
          } 
        } 
      } 
    } 
  }
  
  private Map.Entry<String, Byte> parseMaterialData(String materialData) {
    if (materialData == null || materialData.isEmpty())
      return null; 
    String[] parts = materialData.split(":");
    try {
      int blockId = Integer.parseInt(parts[0]);
      Block block = Block.func_149729_e(blockId);
      if (block == null)
        return null; 
      String material = Block.field_149771_c.func_148750_c(block);
      byte data = 0;
      boolean dataSpecified = false;
      if (parts.length >= 2)
        try {
          data = Byte.parseByte(parts[1]);
          dataSpecified = true;
        } catch (NumberFormatException e) {
          return null;
        }  
      return new AbstractMap.SimpleEntry<>(material, Byte.valueOf(dataSpecified ? data : -1));
    } catch (NumberFormatException numberFormatException) {
      if (parts.length < 2)
        return null; 
      String material = parts[0] + ":" + parts[1];
      byte data = 0;
      boolean dataSpecified = false;
      if (parts.length >= 3)
        try {
          data = Byte.parseByte(parts[2]);
          dataSpecified = true;
        } catch (NumberFormatException e) {
          return null;
        }  
      return new AbstractMap.SimpleEntry<>(material, Byte.valueOf(dataSpecified ? data : -1));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\command\PalaSchematicCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palahologram.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.itemstack.JsonToNBT;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palahologram.common.hologram.Hologram;
import fr.paladium.palahologram.common.network.SCPacketCopyHologram;
import fr.paladium.palahologram.common.worlddata.HologramWorldData;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.text.DecimalFormat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

@Command(command = {"hologram", "hg"}, permission = "paladium.hologram.admin", sender = {SenderType.PLAYER, SenderType.CONSOLE})
public class HologramCommand {
  private final DecimalFormat formatter = new DecimalFormat("#0.00");
  
  @SubCommand(command = "hologram list")
  public void createHologramAt(CommandContext context) {
    HologramWorldData.get().getHologramData().forEach((name, hologram) -> context.send(name + " x:" + this.formatter.format(hologram.getLocation().getX()) + " y:" + this.formatter.format(hologram.getLocation().getY()) + " z:" + this.formatter.format(hologram.getLocation().getZ())));
  }
  
  @SubCommand(command = "hologram tp <name>", sender = {SenderType.PLAYER})
  public void hologramTp(CommandContext context, @CommandParameter(infinite = false) String name) {
    if (HologramWorldData.get().getHologramByName(name) == null) {
      context.error("Cet hologramme n'existe pas");
      return;
    } 
    context.success("Téléportation en cours...");
    HologramWorldData.get().getHologramByName(name).getLocation().teleportServer((EntityPlayer)context.getPlayer());
  }
  
  @SubCommand(command = "hologram create <x> <y> <z> <name> <text>")
  public void createHologramAt(CommandContext context, double x, double y, double z, String name, String text) {
    if (!HologramWorldData.get().createHologram(name, new DoubleLocation(x, y, z), text)) {
      context.error("Cet hologramme existe déjà");
      return;
    } 
    context.success("Création réussi !");
  }
  
  @SubCommand(command = "hologram create <name> <text>", sender = {SenderType.PLAYER})
  public void createHologram(CommandContext context, String name, String text) {
    if (!HologramWorldData.get().createHologram(name, new DoubleLocation((context.getPlayer()).field_70165_t, (context.getPlayer()).field_70163_u + context.getPlayer().func_70047_e(), (context.getPlayer()).field_70161_v), text)) {
      context.error("Cet hologramme existe déjà");
      return;
    } 
    context.success("Création réussi !");
  }
  
  @SubCommand(command = "hologram remove <name>")
  public void removeHologram(CommandContext context, String name) {
    HologramWorldData.get().removeHologram(name);
    context.success("Hologramme supprimé");
  }
  
  @SubCommand(command = "hologram move <name> <x> <y> <z>")
  public void moveHologramAt(CommandContext context, double x, double y, double z, String name) {
    HologramWorldData.get().editHologramPosition(name, new DoubleLocation(x, y, z));
    context.success("Hologramme déplacé");
  }
  
  @SubCommand(command = "hologram move <name>", sender = {SenderType.PLAYER})
  public void moveHologram(CommandContext context, String name) {
    HologramWorldData.get().editHologramPosition(name, new DoubleLocation((context.getPlayer()).field_70165_t, (context.getPlayer()).field_70163_u + context.getPlayer().func_70047_e(), (context.getPlayer()).field_70161_v));
    context.success("Hologramme déplacé");
  }
  
  @SubCommand(command = "hologram followPlayerX <name> <true|false>")
  public void configFollowPlayerX(CommandContext context, String name, boolean x) {
    HologramWorldData.get().configFollowPlayerX(name, x);
  }
  
  @SubCommand(command = "hologram followPlayerY <name> <true|false>")
  public void configFollowPlayerY(CommandContext context, String name, boolean x) {
    HologramWorldData.get().configFollowPlayerY(name, x);
  }
  
  @SubCommand(command = "hologram angle <name> <angle>")
  public void configElementAngle(CommandContext context, String name, double angle) {
    HologramWorldData.get().configAngle(name, angle);
  }
  
  @SubCommand(command = "hologram addline <name> <text>")
  public void configAddText(CommandContext context, String name, String text) {
    HologramWorldData.get().configAddText(name, text);
  }
  
  @SubCommand(command = "hologram addimage <name> <resourceUrl>")
  public void configAddResource(CommandContext context, String name, String resourceUrl) {
    HologramWorldData.get().configAddResource(name, resourceUrl);
  }
  
  @SubCommand(command = "hologram delete <name> <index>")
  public void configDelText(CommandContext context, String name, int index) {
    HologramWorldData.get().configDelElement(name, index);
  }
  
  @SubCommand(command = "hologram police <name> <index> <police>")
  public void configTextFontSize(CommandContext context, String name, int index, float fontSize) {
    HologramWorldData.get().configTextFontSize(name, index, fontSize);
  }
  
  @SubCommand(command = "hologram police <name> <police>")
  public void configAllFontSize(CommandContext context, String name, float fontSize) {
    HologramWorldData.get().configTextFontSize(name, -1, fontSize);
  }
  
  @SubCommand(command = "hologram setline <name> <index> <text>")
  public void configText(CommandContext context, String name, int index, String text) {
    HologramWorldData.get().configText(name, index, text);
  }
  
  @SubCommand(command = "hologram insertline <name> <index> <text>")
  public void configinsertText(CommandContext context, String name, int index, String text) {
    HologramWorldData.get().configInsertText(name, index, text);
  }
  
  @SubCommand(command = "hologram background <name> <index> <background>")
  public void configTextBackground(CommandContext context, String name, int index, boolean background) {
    HologramWorldData.get().configTextBackground(name, index, background);
  }
  
  @SubCommand(command = "hologram url <name> <index> <imageUrl>")
  public void configResourceUrl(CommandContext context, String name, int index, String resourceUrl) {
    HologramWorldData.get().configResourceUrl(name, index, resourceUrl);
  }
  
  @SubCommand(command = "hologram image width <name> <index> <largeur>")
  public void configResourceWidth(CommandContext context, String name, int index, double width) {
    HologramWorldData.get().configResourceWidth(name, index, width);
  }
  
  @SubCommand(command = "hologram image height <name> <index> <hauteur>")
  public void configResourceHeight(CommandContext context, String name, int index, double height) {
    HologramWorldData.get().configResourceHeight(name, index, height);
  }
  
  @SubCommand(command = "hologram image size <name> <index> <largeur> <hauteur>")
  public void configResourceHeight(CommandContext context, String name, int index, double width, double height) {
    HologramWorldData.get().configResourceSize(name, index, width, height);
  }
  
  @SubCommand(command = "hologram spacing <name> <spacing>")
  public void configSpacing(CommandContext context, String name, double spacing) {
    HologramWorldData.get().configSpacing(name, spacing);
  }
  
  @SubCommand(command = "hologram alignment <name> <index> <alignment>")
  public void configAlignment(CommandContext context, String name, int index, Align alignment) {
    HologramWorldData.get().configAlignment(name, index, alignment);
  }
  
  @SubCommand(command = "hologram alignment <name> <alignment>")
  public void configAllAlignment(CommandContext context, String name, Align alignment) {
    HologramWorldData.get().configAlignment(name, -1, alignment);
  }
  
  @SubCommand(command = "hologram copy <name>")
  public void copyHologram(CommandContext context, String name) {
    if (HologramWorldData.get().getHologramByName(name) == null) {
      context.error("Cet hologramme n'existe pas");
      return;
    } 
    Hologram hologram = HologramWorldData.get().getHologramByName(name);
    NBTTagCompound hologramNbt = new NBTTagCompound();
    hologram.write(hologramNbt);
    if (context.isPlayer()) {
      (new SCPacketCopyHologram(hologramNbt)).send(context.getPlayer());
      context.send("Hologram copié dans le clipboard !");
    } else {
      context.send(hologramNbt.toString());
    } 
  }
  
  @SubCommand(command = "hologram paste <name> <data>", sender = {SenderType.PLAYER})
  public void pasteHologram(CommandContext context, String name, String nbtString) {
    try {
      NBTTagCompound tag = JsonToNBT.getTagFromJson(nbtString);
      Hologram hologram = new Hologram();
      hologram.read(tag);
      hologram.setLocation(new DoubleLocation((context.getPlayer()).field_70165_t, (context.getPlayer()).field_70163_u + context.getPlayer().func_70047_e(), (context.getPlayer()).field_70161_v));
      if (!HologramWorldData.get().appendHologram(name, hologram)) {
        context.error("Cet hologramme existe déjà");
      } else {
        context.success("Hologramme crée !");
      } 
    } catch (NBTException e) {
      context.error("Une erreur est survenu lors de la lecture de l'hologramme");
      e.printStackTrace();
    } 
  }
  
  @SubCommand(command = "hologram paste <x> <y> <z> <name> <data>")
  public void pasteHologramAt(CommandContext context, double x, double y, double z, String name, String nbtString) {
    try {
      NBTTagCompound tag = JsonToNBT.getTagFromJson(nbtString);
      Hologram hologram = new Hologram();
      hologram.read(tag);
      hologram.setLocation(new DoubleLocation(x, y, z));
      if (!HologramWorldData.get().appendHologram(name, hologram)) {
        context.error("Cet hologramme existe déjà");
      } else {
        context.success("Hologramme crée !");
      } 
    } catch (NBTException e) {
      context.error("Une erreur est survenu lors de la lecture de l'hologramme");
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\server\command\HologramCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
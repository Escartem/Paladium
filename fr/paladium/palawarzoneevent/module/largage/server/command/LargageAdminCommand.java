package fr.paladium.palawarzoneevent.module.largage.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.GreaterOrEquals;
import fr.paladium.palawarzoneevent.module.largage.LargageModule;
import fr.paladium.palawarzoneevent.module.largage.server.config.LargageConfig;
import fr.paladium.palawarzoneevent.module.largage.server.manager.LargageEventManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.item.ItemStack;

@Command(command = {"largage"}, permission = "palawarzoneevent.command.largage.admin", sender = {SenderType.PLAYER, SenderType.CONSOLE})
public class LargageAdminCommand {
  @SubCommand(command = "largage start <uuid>", description = "Chronos largage start command")
  public void chronosStartEvent(CommandContext context, UUID eventUuid) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageConfig.SpawnPoint result = LargageEventManager.inst().startEvent(context.getSender().func_130014_f_(), eventUuid);
    if (result == null) {
      context.error("Aucun spawn point n'est disponible pour lancer un largage.");
      return;
    } 
    context.success("Largage lancé au spawn point: x: " + result.getX() + ", z: " + result.getZ());
  }
  
  @SubCommand(command = "largage start", description = "Lancer un largage directement sans passer par l'agenda et la plannification.")
  public void start(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageConfig.SpawnPoint result = LargageEventManager.inst().startEvent(context.getSender().func_130014_f_());
    if (result == null) {
      context.error("Aucun spawn point n'est disponible pour lancer un largage.");
      return;
    } 
    context.success("Largage lancé au spawn point: x: " + result.getX() + ", z: " + result.getZ());
  }
  
  @SubCommand(command = "largage manual start <x> <z>", description = "Lancer un largage directement sans passer par l'agenda, la plannification et la config.", sender = {SenderType.PLAYER})
  public void startManual(CommandContext context, int x, int z) {
    startManual(context, x, 200, z);
  }
  
  @SubCommand(command = "largage manual start <x> <y> <z>", description = "Lancer un largage directement sans passer par l'agenda, la plannification et la config.", sender = {SenderType.PLAYER})
  public void startManual(CommandContext context, int x, int y, int z) {
    List<LargageConfig.ItemReward> rewards = new ArrayList<>();
    rewards.add(new LargageConfig.ItemReward("minecraft:stone 0 64", 100));
    rewards.add(new LargageConfig.ItemReward("minecraft:dirt 0 64", 50));
    rewards.add(new LargageConfig.ItemReward("minecraft:grass 0 64", 50));
    rewards.add(new LargageConfig.ItemReward("minecraft:diamond 0 64", 25));
    LargageEventManager.inst().spawnManualLargage((context.getPlayer()).field_70170_p, x, y, z, 1, 2, rewards);
    context.success("Vous venez de faire spawn un largage aux coordonnées x:" + x + " y:" + y + " z:" + z);
  }
  
  @SubCommand(command = "largage spawnpoint add", description = "Ajouter un spawn point à votre position.", sender = {SenderType.PLAYER})
  public void addSpawnPoint(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageConfig.SpawnPoint spawnPoint = new LargageConfig.SpawnPoint(Double.valueOf(Math.floor((context.getPlayer()).field_70165_t) + 0.5D).floatValue(), Double.valueOf(Math.floor((context.getPlayer()).field_70161_v) + 0.5D).floatValue());
    LargageModule.getServer().getConfig().addSpawnPoint(spawnPoint);
    context.success("Spawn point crée. x: " + spawnPoint.getX() + ", z: " + spawnPoint.getZ());
  }
  
  @SubCommand(command = "largage spawnpoint list", description = "Lister les spawn points existants.")
  public void listSpawnPoint(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    List<LargageConfig.SpawnPoint> spawnPoints = LargageModule.getServer().getConfig().getLargageSpawnPoints();
    context.success("------------------------");
    if (spawnPoints.isEmpty()) {
      context.success("Aucun spawn point n'existe.");
      context.success("------------------------");
      return;
    } 
    for (int i = 0; i < spawnPoints.size(); i++) {
      LargageConfig.SpawnPoint spawnPoint = spawnPoints.get(i);
      context.success("- " + i + ": x: " + spawnPoint.getX() + ", z: " + spawnPoint.getZ());
    } 
    context.success("------------------------");
  }
  
  @SubCommand(command = "largage spawnpoint remove <number>", description = "Supprimer un spawn point spécifique.")
  public void delSpawnPoint(CommandContext context, @GreaterOrEquals(0.0D) @CommandParameter(description = "value between 0-infinite") int number) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    if (number >= LargageModule.getServer().getConfig().getLargageSpawnPoints().size()) {
      context.error("Ce spawnpoint n'existe pas (number doit être compris entre 0-" + (LargageModule.getServer().getConfig().getLargageSpawnPoints().size() - 1) + ")");
      return;
    } 
    LargageConfig.SpawnPoint removedSpawnPoint = LargageModule.getServer().getConfig().removeSpawnPoint(number);
    context.success("Supression reussi " + number + " - x: " + removedSpawnPoint.getX() + ", z: " + removedSpawnPoint.getZ());
    listSpawnPoint(context);
  }
  
  @SubCommand(command = "largage maxhealth <maxhealth>", description = "Permet de modifier la vie maximum du largage.")
  public void setMaxHealth(CommandContext context, @GreaterOrEquals(0.0D) @CommandParameter(description = "value between 0-infinite") int maxhealth) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().setLargageMaxHealth(maxhealth);
    LargageModule.getServer().getConfig().save();
    context.success("Vie maximum du largage modifié -> " + maxhealth);
  }
  
  @SubCommand(command = "largage defaultY <y>", description = "Permet de modifier la hauteur de départ du largage.")
  public void setStartingY(CommandContext context, @GreaterOrEquals(0.0D) @CommandParameter(description = "value between 0-infinite") int y) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().setStartingY(y);
    LargageModule.getServer().getConfig().save();
    context.success("Y de départ du largage modifié -> " + y);
  }
  
  @SubCommand(command = "largage invulnerablecheckradius <radius>", description = "Permet de modifier le rayon de check du nombre de faction autour du largage pour le rendre invinsible.")
  public void setFactionCheckRadius(CommandContext context, @GreaterOrEquals(0.0D) @CommandParameter(description = "value between 0-infinite") int radius) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().setFactionCheckRadius(radius);
    LargageModule.getServer().getConfig().save();
    context.success("Rayon de check du nombre de faction du largage modifié -> " + radius);
  }
  
  @SubCommand(command = "largage numberfactionthreshold <number>", description = "Permet de modifier le nombre de faction minimum autour du largage pour le rendre invinsible.")
  public void setFactionCheckThreshold(CommandContext context, @GreaterOrEquals(0.0D) @CommandParameter(description = "value between 0-infinite") int threshold) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().setInvulnerabilityFactionThreshold(threshold);
    LargageModule.getServer().getConfig().save();
    context.success("Nombre de faction minimum pour rendre le largage invulnérable modifié -> " + threshold);
  }
  
  @SubCommand(command = "largage drops item numberofdrop <min> <max>", description = "Permet de modifier le random dans le drop des items.")
  public void setItemDropCount(CommandContext context, @GreaterOrEquals(1.0D) @CommandParameter(description = "value between [1-maxItem[") int min, @GreaterOrEquals(1.0D) @CommandParameter(description = "value between ]minItem-maxItem]") int max) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().setMinItemDrop(min);
    LargageModule.getServer().getConfig().setMaxItemDrop(max);
    LargageModule.getServer().getConfig().save();
    context.success("Nombre d'item droppé modifier -> minimum: " + LargageModule.getServer().getConfig().getMinItemDrop() + ", maximum: " + LargageModule.getServer().getConfig().getMaxItemDrop());
  }
  
  @SubCommand(command = "largage drops item add <weight>", description = "Permet d'ajouter un item en récompense du largage, prend l'item actuel en main ainsi que sa quantité.", sender = {SenderType.PLAYER})
  public void addItemReward(CommandContext context, @GreaterOrEquals(1.0D) @CommandParameter(description = "weight of the item 1-infinite") int weight) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    if (context.getPlayer().func_70694_bm() == null)
      return; 
    ItemStack heldItem = context.getPlayer().func_70694_bm();
    String itemName = ItemStackSerializer.write64(heldItem);
    LargageModule.getServer().getConfig().getRewardItems().add(new LargageConfig.ItemReward(itemName, weight));
    LargageModule.getServer().getConfig().save();
    context.success("Item ajouté à la liste des récompenses du largage.");
  }
  
  @SubCommand(command = "largage drops item list", description = "Lister les items récompensés du largage.")
  public void listItemReward(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    List<LargageConfig.ItemReward> items = LargageModule.getServer().getConfig().getRewardItems();
    context.success("------------------------");
    if (items.isEmpty()) {
      context.success("Aucun item n'existe.");
      context.success("------------------------");
      return;
    } 
    for (int i = 0; i < items.size(); i++) {
      LargageConfig.ItemReward item = items.get(i);
      context.success("- " + i + ": " + ItemUtils.parse(item.getItem()).func_82833_r() + " (weight: " + item.getWeight() + ")");
    } 
    context.success("------------------------");
  }
  
  @SubCommand(command = "largage drops item remove <index>", description = "Supprimer un item de la liste des récompenses")
  public void delItemReward(CommandContext context, int index) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().getRewardItems().remove(index);
    LargageModule.getServer().getConfig().save();
    context.success("Item retiré à la liste des récompenses du largage.");
    listItemReward(context);
  }
  
  @SubCommand(command = "largage drops elo add <elo>", description = "Permet de modifier la récompense en elo du largage.")
  public void setRewardElo(CommandContext context, @GreaterOrEquals(0.0D) @CommandParameter(description = "value between 0-infinite, 0 = disabled") int elo) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().setRewardElo(elo);
    LargageModule.getServer().getConfig().save();
    context.success("Récompense en elo du largage modifié -> " + elo);
  }
  
  @SubCommand(command = "largage drops command add <command>", description = "Permet de modifier la commande récompensée du largage.")
  public void addCommandReward(CommandContext context, @CommandParameter(description = "command to execute") String command) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().getRewardCommands().add(command);
    LargageModule.getServer().getConfig().save();
    context.success("Commande ajoutée à la liste des commandes récompensées du largage.");
  }
  
  @SubCommand(command = "largage drops command list", description = "Lister les commandes récompensées du largage.")
  public void listCommandReward(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    List<String> commands = LargageModule.getServer().getConfig().getRewardCommands();
    context.success("------------------------");
    if (commands.isEmpty()) {
      context.success("Aucune commande n'existe.");
      context.success("------------------------");
      return;
    } 
    for (int i = 0; i < commands.size(); i++) {
      String command = commands.get(i);
      context.success("- " + i + ": " + command);
    } 
    context.success("------------------------");
  }
  
  @SubCommand(command = "largage drops remove <index>", description = "Supprimer une commande de la liste des récompenses")
  public void delCommandReward(CommandContext context, int index) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    LargageModule.getServer().getConfig().getRewardCommands().remove(index);
    LargageModule.getServer().getConfig().save();
    context.success("Commande retirée à la liste des commandes récompensées du largage.");
    listCommandReward(context);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\command\LargageAdminCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palawarzoneevent.module.capture.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.Greater;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.GreaterOrEquals;
import fr.paladium.palawarzoneevent.module.capture.CaptureModule;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import fr.paladium.palawarzoneevent.module.capture.common.util.CaptureRadiusShape;
import fr.paladium.palawarzoneevent.module.capture.common.util.CaptureReward;
import fr.paladium.palawarzoneevent.module.capture.common.util.CaptureRewardType;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

@Command(command = {"capture"}, permission = "palawarzoneevent.command.capture.admin", sender = {SenderType.PLAYER})
public class CaptureAdminCommand {
  @SubCommand(command = "capture create <name>")
  public void createCapture(CommandContext context, String name) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint point = CapturePoint.of(name, new DoubleLocation((Entity)context.getPlayer()));
    CaptureModule.getServer().getConfig().getCapturePoints().add(point);
    CaptureModule.getServer().getConfig().save();
    context.success("La zone de capture '" + name + "' à bien été crée au position x: " + point.getLocation().getX() + ", y: " + point.getLocation().getY() + ", z: " + point.getLocation().getZ());
  }
  
  @SubCommand(command = "capture start <name>")
  public void startCapture(CommandContext context, String name) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    capPoint.setEnabled(true);
    CaptureModule.getServer().getConfig().save();
    context.success("La zone de capture '" + name + "' à bien été démarré");
  }
  
  @SubCommand(command = "capture stop <name>")
  public void stopCapture(CommandContext context, String name) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    capPoint.setEnabled(false);
    CaptureModule.getServer().getConfig().save();
    context.success("La zone de capture '" + name + "' à bien été arreté");
  }
  
  @SubCommand(command = "capture remove <name>")
  public void removeCapture(CommandContext context, String name) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    boolean removed = CaptureModule.getServer().getConfig().removeCapturePoint(name);
    CaptureModule.getServer().getConfig().save();
    if (removed) {
      context.success("La zone de capture '" + name + "' à bien été supprimé");
    } else {
      context.error("La zone de capture '" + name + "' n'existe pas");
    } 
  }
  
  @SubCommand(command = "capture list")
  public void listCapture(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CaptureModule.getServer().getConfig().getCapturePoints().forEach(capturePoint -> context.success(" - " + capturePoint.getName() + " x: " + capturePoint.getLocation().getX() + ", y: " + capturePoint.getLocation().getY() + ", z: " + capturePoint.getLocation().getZ()));
  }
  
  @SubCommand(command = "capture move <name>")
  public void moveCapture(CommandContext context, String name) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    DoubleLocation newLocation = new DoubleLocation((Entity)context.getPlayer());
    capPoint.setLocation(newLocation);
    CaptureModule.getServer().getConfig().save();
    context.success("La zone de capture à bien été déplacé");
  }
  
  @SubCommand(command = "capture move <name> <x> <y> <z>")
  public void moveCapture(CommandContext context, String name, int x, int y, int z) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    DoubleLocation newLocation = new DoubleLocation(x, y, z);
    capPoint.setLocation(newLocation);
    CaptureModule.getServer().getConfig().save();
    context.success("La zone de capture à bien été déplacé");
  }
  
  @SubCommand(command = "capture drops list <name>")
  public void listDrops(CommandContext context, String name) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    context.success("------------------------");
    if (capPoint.getRewards().isEmpty()) {
      context.success("Aucun reward n'existe pour ce point de capture");
      context.success("------------------------");
      return;
    } 
    for (int i = 0; i < capPoint.getRewards().size(); i++)
      context.success(" - " + i + " " + ((CaptureReward)capPoint.getRewards().get(i)).toString()); 
    context.success("------------------------");
  }
  
  @SubCommand(command = "capture drops add <name> <type> <amount> <everyminute> [<weight>]")
  public void addDrop(CommandContext context, String name, CaptureRewardType type, @GreaterOrEquals(0.0D) int amount, @Greater(0.0D) int everyMinute, @GreaterOrEquals(0.0D) Optional<Integer> optWeight) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    if (type == CaptureRewardType.ITEM && context.getPlayer().func_70694_bm() == null) {
      context.error("Vous devez avoir un item en main pour ajouter un drop ITEM");
      return;
    } 
    if (type == CaptureRewardType.ITEM && !optWeight.isPresent()) {
      context.error("Le type de reward ITEM doit avoir un poid");
      return;
    } 
    CaptureReward reward = new CaptureReward(type, amount, null, ((Integer)optWeight.orElse(Integer.valueOf(0))).intValue(), everyMinute);
    if (type == CaptureRewardType.ITEM) {
      ItemStack heldItem = context.getPlayer().func_70694_bm();
      String itemName = ItemStackSerializer.write64(heldItem);
      reward.setItem(itemName);
    } 
    capPoint.getRewards().add(reward);
    CaptureModule.getServer().getConfig().save();
    context.success("Vous avez bien ajouté un nouveau reward à la zone " + name);
  }
  
  @SubCommand(command = "capture drops remove <name> <index>")
  public void addDrop(CommandContext context, String name, @GreaterOrEquals(0.0D) int index) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    capPoint.getRewards().remove(index);
    CaptureModule.getServer().getConfig().save();
    context.success("Le reward à bien été supprimé de la liste");
    listDrops(context, name);
  }
  
  @SubCommand(command = "capture radius <name> <radius>")
  public void setRadius(CommandContext context, String name, @GreaterOrEquals(1.0D) int radius) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    capPoint.setRadius(radius);
    CaptureModule.getServer().getConfig().save();
    context.success("Le rayon à bien été modifié à " + radius + " blocks de rayon");
  }
  
  @SubCommand(command = "capture radius type <name> <type>")
  public void setRadiusType(CommandContext context, String name, CaptureRadiusShape shape) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Les commandes d'évent warzone son désactivé sur ce serveur");
      return;
    } 
    CapturePoint capPoint = CaptureModule.getServer().getConfig().getCapturePoint(name);
    if (capPoint == null) {
      context.error("Point de capture inéxistant");
      return;
    } 
    capPoint.setRadiusType(shape);
    CaptureModule.getServer().getConfig().save();
    context.success("La forme du rayon à bien été modifié en " + shape.name());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\server\command\CaptureAdminCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
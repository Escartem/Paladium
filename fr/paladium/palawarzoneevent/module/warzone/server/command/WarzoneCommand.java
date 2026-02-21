package fr.paladium.palawarzoneevent.module.warzone.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palawarzoneevent.module.warzone.WarzoneModule;
import fr.paladium.palawarzoneevent.module.warzone.client.ui.UIWarzone;
import fr.paladium.palawarzoneevent.module.warzone.server.phase.WarzonePhase;
import fr.paladium.palawarzoneevent.module.warzone.server.phase.WarzonePhaseManager;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardWorldData;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.UUID;

@Command(command = {"warzone"}, description = "Warzone commands", permission = "palawarzoneevent.command.warzone", sender = {SenderType.PLAYER, SenderType.CONSOLE})
public class WarzoneCommand {
  @SubCommand(command = "warzone", description = "Ouvrir l'interface Warzone", sender = {SenderType.PLAYER})
  public void warzone(CommandContext context) {
    ZUIServer.open(UIWarzone.class, context.getPlayer());
  }
  
  @SubCommand(command = "warzone start <phase> <uuid>", permission = "palawarzoneevent.command.admin", description = "Command chronos", sender = {SenderType.CONSOLE})
  public void startPhase(CommandContext context, String phase, UUID uuid) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Merci d'être sur le serveur warzone pour effectuer cette commande");
      return;
    } 
    WarzonePhase wzPhase = WarzoneModule.getServer().getConfig().getWarzonePhaseByName(phase);
    if (wzPhase == null) {
      context.error("La phase '" + phase + "' n'as pas été trouver");
      return;
    } 
    WarzonePhaseManager.inst().startPhase(phase, wzPhase, uuid);
  }
  
  @SubCommand(command = "warzone stop <phase> <uuid>", permission = "palawarzoneevent.command.admin", description = "Command chronos", sender = {SenderType.CONSOLE})
  public void stopPhase(CommandContext context, String phase, UUID uuid) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Merci d'être sur le serveur warzone pour effectuer cette commande");
      return;
    } 
    WarzonePhase wzPhase = WarzoneModule.getServer().getConfig().getWarzonePhaseByName(phase);
    if (wzPhase == null) {
      context.error("La phase '" + phase + "' n'as pas été trouver");
      return;
    } 
    WarzonePhaseManager.inst().stopPhase(wzPhase, uuid);
  }
  
  @SubCommand(command = "warzone start <phase>", permission = "palawarzoneevent.command.admin", description = "Command de test", sender = {SenderType.PLAYER})
  public void startPhasePlayer(CommandContext context, String phase) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Merci d'être sur le serveur warzone pour effectuer cette commande");
      return;
    } 
    WarzonePhase wzPhase = WarzoneModule.getServer().getConfig().getWarzonePhaseByName(phase);
    if (wzPhase == null) {
      context.error("La phase '" + phase + "' n'as pas été trouver");
      return;
    } 
    WarzonePhaseManager.inst().startPhase(phase, wzPhase, null);
  }
  
  @SubCommand(command = "warzone stop <phase>", permission = "palawarzoneevent.command.admin", description = "Command chronos", sender = {SenderType.PLAYER})
  public void stopPhasePlayer(CommandContext context, String phase) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Merci d'être sur le serveur warzone pour effectuer cette commande");
      return;
    } 
    WarzonePhase wzPhase = WarzoneModule.getServer().getConfig().getWarzonePhaseByName(phase);
    if (wzPhase == null) {
      context.error("La phase '" + phase + "' n'as pas été trouver");
      return;
    } 
    WarzonePhaseManager.inst().stopPhase(wzPhase, null);
  }
  
  @SubCommand(command = "warzone clearworlddata", permission = "palawarzoneevent.command.admin", description = "Command pour clear les world data de la warzone")
  public void clearWorlddata(CommandContext context) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE })) {
      context.error("Merci d'être sur le serveur warzone pour effectuer cette commande");
      return;
    } 
    WarzoneLeaderboardWorldData.get().getLeaderboardMap().clear();
    WarzoneLeaderboardWorldData.get().save();
    context.success("Les données de la worlddata ont bien été clear");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\command\WarzoneCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palawarzoneevent.module.largage.common.action.server;

import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.chronos.server.runnables.PlannedStatued;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import java.util.concurrent.CompletableFuture;

public class LargageServerAction {
  private static final String EVENT_NAME = "LARGAGE";
  
  @ServerAction
  public static CompletableFuture<Planned> getRunningLargageEvent() {
    return ServerActionHook.useServer(context -> CompletableFuture.completedFuture(ChronosManager.getInstance().getRunningPlannedEvent("LARGAGE")), new Object[0]);
  }
  
  @ServerAction
  public static CompletableFuture<PlannedStatued> getNextLargageEvent() {
    return ServerActionHook.useServer(context -> CompletableFuture.completedFuture(ChronosManager.getInstance().getNextPlannedEvent("LARGAGE")), new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\common\action\server\LargageServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
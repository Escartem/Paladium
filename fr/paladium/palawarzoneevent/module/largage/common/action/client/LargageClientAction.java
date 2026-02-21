package fr.paladium.palawarzoneevent.module.largage.common.action.client;

import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.runnables.PlannedStatued;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;
import fr.paladium.palawarzoneevent.module.largage.client.ui.UIOverlayLargageScoreboard;
import java.util.concurrent.CompletableFuture;

public class LargageClientAction {
  @ClientAction
  public static ClientActionResult<Void> updateScoreboardLocation(double x, double y, double z) {
    return ClientActionHook.useClient(() -> {
          UIOverlayLargageScoreboard.setApproximatedLocation(x, y, z);
          return CompletableFuture.completedFuture(null);
        }new Object[] { Double.valueOf(x), Double.valueOf(y), Double.valueOf(z) });
  }
  
  @ClientAction
  public static ClientActionResult<Void> updateScoreboardRunningEvent(Planned planned) {
    return ClientActionHook.useClient(() -> {
          UIOverlayLargageScoreboard.setRunningPlannedEvent(planned);
          return CompletableFuture.completedFuture(null);
        }new Object[] { planned });
  }
  
  @ClientAction
  public static ClientActionResult<Void> updateScoreboardNextEvent(PlannedStatued planned) {
    return ClientActionHook.useClient(() -> {
          UIOverlayLargageScoreboard.setNextPlannedEvent(planned);
          return CompletableFuture.completedFuture(null);
        }new Object[] { planned });
  }
  
  @ClientAction
  public static ClientActionResult<Void> resetScoreboardRunningEvent() {
    return ClientActionHook.useClient(() -> {
          UIOverlayLargageScoreboard.setRunningPlannedEvent(null);
          return CompletableFuture.completedFuture(null);
        }new Object[0]);
  }
  
  @ClientAction
  public static ClientActionResult<Void> resetScoreboardNextEvent() {
    return ClientActionHook.useClient(() -> {
          UIOverlayLargageScoreboard.setNextPlannedEvent(null);
          return CompletableFuture.completedFuture(null);
        }new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\common\action\client\LargageClientAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
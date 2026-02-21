package fr.paladium.palawarzoneevent.module.capture.common.action;

import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;
import fr.paladium.palawarzoneevent.module.capture.common.manager.CaptureEventManager;
import java.util.concurrent.CompletableFuture;

public class ClientCaptureEventManagerAction {
  @ClientAction
  public static ClientActionResult<Void> syncCapturePoints(CaptureEventManager.CapturePointsData capturePointsData) {
    return ClientActionHook.useClient(() -> {
          CaptureEventManager.inst().sync(capturePointsData);
          return CompletableFuture.completedFuture(null);
        }new Object[] { capturePointsData });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\common\action\ClientCaptureEventManagerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
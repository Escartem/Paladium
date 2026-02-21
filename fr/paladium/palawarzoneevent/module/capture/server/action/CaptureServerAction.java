package fr.paladium.palawarzoneevent.module.capture.server.action;

import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

public class CaptureServerAction {
  @ServerAction
  public static CompletableFuture<String> getFaction(@NonNull UUID captureUUID) {
    if (captureUUID == null)
      throw new NullPointerException("captureUUID is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> CompletableFuture.completedFuture(""), new Object[] { captureUUID });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\server\action\CaptureServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
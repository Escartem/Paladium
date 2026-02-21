package fr.paladium.palaconfiguration.server.manager;

import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import java.util.concurrent.CompletableFuture;

class null implements IRetrofitCallback<RemoteObject> {
  public void onSuccess(RemoteObject file) {
    future.complete(file);
  }
  
  public void onFail(RemoteObject file, Throwable throwable) {
    future.completeExceptionally(throwable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\manager\ConfigurationManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
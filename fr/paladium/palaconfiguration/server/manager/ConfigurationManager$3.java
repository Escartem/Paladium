package fr.paladium.palaconfiguration.server.manager;

import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

class null implements IRetrofitCallback<Map<String, RemoteObject>> {
  public void onSuccess(Map<String, RemoteObject> files) {
    future.complete(files);
  }
  
  public void onFail(Map<String, RemoteObject> files, Throwable throwable) {
    future.completeExceptionally(throwable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\manager\ConfigurationManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
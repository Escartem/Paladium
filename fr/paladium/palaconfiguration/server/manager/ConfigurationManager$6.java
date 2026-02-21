package fr.paladium.palaconfiguration.server.manager;

import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import java.util.concurrent.CompletableFuture;

class null implements IRetrofitCallback<Void> {
  public void onSuccess(Void file) {
    future.complete(file);
  }
  
  public void onFail(Void file, Throwable throwable) {
    future.completeExceptionally(throwable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\manager\ConfigurationManager$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
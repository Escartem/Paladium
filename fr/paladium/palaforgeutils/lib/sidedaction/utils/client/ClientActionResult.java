package fr.paladium.palaforgeutils.lib.sidedaction.utils.client;

import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

public class ClientActionResult<T> {
  private final String className;
  
  private final String methodName;
  
  private final Object[] args;
  
  private final CompletableFuture<T> future;
  
  public String getClassName() {
    return this.className;
  }
  
  public String getMethodName() {
    return this.methodName;
  }
  
  public Object[] getArgs() {
    return this.args;
  }
  
  public CompletableFuture<T> getFuture() {
    return this.future;
  }
  
  public ClientActionResult(String className, String methodName, Object[] args) {
    this.className = className;
    this.methodName = methodName;
    this.args = args;
    this.future = new CompletableFuture<>();
  }
  
  public CompletableFuture<T> execute(@NonNull PlayerSelector selector) {
    if (selector == null)
      throw new NullPointerException("selector is marked non-null but is null"); 
    ClientActionHook.send(selector, this.className, this.methodName, this.args, this.future);
    return this.future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\client\ClientActionResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
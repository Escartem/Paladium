package fr.paladium.palaforgeutils.lib.sidedaction.utils;

import java.util.concurrent.CompletableFuture;

public class SidedActionExecution<T> {
  private final String uuid;
  
  private final String className;
  
  private final String methodName;
  
  private final Object[] args;
  
  private final CompletableFuture<T> future;
  
  public SidedActionExecution(String uuid, String className, String methodName, Object[] args, CompletableFuture<T> future) {
    this.uuid = uuid;
    this.className = className;
    this.methodName = methodName;
    this.args = args;
    this.future = future;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\SidedActionExecution.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PaladiumFuture<T> extends PaladiumTask implements Future<T> {
  public static String _a = "​​​​";
  
  private final Callable<T> callable;
  
  private T value;
  
  private Exception exception = null;
  
  PaladiumFuture(Callable<T> callable, int id) {
    super(null, id, -1L);
    this.callable = callable;
  }
  
  public synchronized boolean cancel(boolean mayInterruptIfRunning) {
    if (getPeriod() != -1L)
      return false; 
    setPeriod(-2L);
    return true;
  }
  
  public boolean isCancelled() {
    return (getPeriod() == -2L);
  }
  
  public boolean isDone() {
    long period = getPeriod();
    return (period != -1L && period != -3L);
  }
  
  public T get() throws CancellationException, InterruptedException, ExecutionException {
    try {
      return get(0L, TimeUnit.MILLISECONDS);
    } catch (TimeoutException e) {
      throw new Error(e);
    } 
  }
  
  public synchronized T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    timeout = unit.toMillis(timeout);
    long period = getPeriod();
    long timestamp = (timeout > 0L) ? System.currentTimeMillis() : 0L;
    while (period == -1L || period == -3L) {
      wait(timeout);
      period = getPeriod();
      if (period == -1L || period == -3L) {
        if (timeout == 0L)
          continue; 
        timeout += timestamp - (timestamp = System.currentTimeMillis());
        if (timeout > 0L)
          continue; 
        throw new TimeoutException();
      } 
    } 
    if (period == -2L)
      throw new CancellationException(); 
    if (period == -4L) {
      if (this.exception == null)
        return this.value; 
      throw new ExecutionException(this.exception);
    } 
    throw new IllegalStateException("Expected -1 to -4, got " + period);
  }
  
  public void run() {
    synchronized (this) {
      if (getPeriod() == -2L)
        return; 
      setPeriod(-3L);
    } 
    try {
      this.value = this.callable.call();
    } catch (Exception e) {
      this.exception = e;
    } finally {
      synchronized (this) {
        setPeriod(-4L);
        notifyAll();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\scheduler\PaladiumFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
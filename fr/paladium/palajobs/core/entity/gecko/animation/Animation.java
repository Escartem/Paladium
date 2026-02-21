package fr.paladium.palajobs.core.entity.gecko.animation;

import java.util.function.Consumer;

public class Animation<T> {
  private final String name;
  
  private final long duration;
  
  private final long startedAt;
  
  private Consumer<T> callback;
  
  public String getName() {
    return this.name;
  }
  
  public long getDuration() {
    return this.duration;
  }
  
  public long getStartedAt() {
    return this.startedAt;
  }
  
  public Consumer<T> getCallback() {
    return this.callback;
  }
  
  public Animation(String name, long duration) {
    this.name = name;
    this.duration = duration;
    this.startedAt = System.currentTimeMillis();
  }
  
  public Animation<T> setCallback(Consumer<T> callback) {
    this.callback = callback;
    return this;
  }
  
  public Animation<T> callback(T entity) {
    if (this.callback != null)
      this.callback.accept(entity); 
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\gecko\animation\Animation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
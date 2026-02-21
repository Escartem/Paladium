package fr.paladium.palamod.modules.end.server.config;

import fr.paladium.palamod.util.BlockLocation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MagicConfig {
  private final Consumer<MagicConfig> callback;
  
  private final List<BlockLocation> points;
  
  private boolean paused;
  
  public Consumer<MagicConfig> getCallback() {
    return this.callback;
  }
  
  public List<BlockLocation> getPoints() {
    return this.points;
  }
  
  public boolean isPaused() {
    return this.paused;
  }
  
  public MagicConfig(Consumer<MagicConfig> callback) {
    this.paused = false;
    this.callback = callback;
    this.points = new ArrayList<>();
  }
  
  public MagicConfig end() {
    this.callback.accept(this);
    return this;
  }
  
  public boolean isActive() {
    return !this.paused;
  }
  
  public MagicConfig pause() {
    this.paused = true;
    return this;
  }
  
  public MagicConfig resume() {
    this.paused = false;
    return this;
  }
  
  public MagicConfig clear() {
    this.points.clear();
    return this;
  }
  
  public MagicConfig add(BlockLocation pos) {
    this.points.add(pos);
    return this;
  }
  
  public MagicConfig remove(BlockLocation pos) {
    if (this.points.contains(pos))
      this.points.remove(pos); 
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\config\MagicConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
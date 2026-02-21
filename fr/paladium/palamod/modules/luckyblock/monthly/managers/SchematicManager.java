package fr.paladium.palamod.modules.luckyblock.monthly.managers;

import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.tasks.SchematicRunnable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class SchematicManager {
  private static SchematicManager instance;
  
  private final List<TimedSchematic> structures;
  
  private Timer timer;
  
  public List<TimedSchematic> getStructures() {
    return this.structures;
  }
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public SchematicManager() {
    instance = this;
    this.structures = new ArrayList<>();
  }
  
  public static SchematicManager getInstance() {
    if (instance == null)
      instance = new SchematicManager(); 
    return instance;
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new SchematicRunnable(this), 0L, 500L);
  }
  
  public Optional<TimedSchematic> getSchematic(UUID uniqueId) {
    return this.structures.stream().filter(structure -> structure.getUniqueId().equals(uniqueId)).findFirst();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\managers\SchematicManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
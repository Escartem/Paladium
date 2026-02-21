package fr.paladium.palamod.modules.luckyblock.structures;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.tasks.RemoveExpiredStructureTask;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class StructureManager {
  private static StructureManager instance;
  
  private final List<SoundStructure> soundStructures;
  
  private Timer timer;
  
  public List<SoundStructure> getSoundStructures() {
    return this.soundStructures;
  }
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public StructureManager() {
    instance = this;
    this.soundStructures = new ArrayList<>();
    startTask();
  }
  
  public static StructureManager getInstance() {
    if (instance == null)
      instance = new StructureManager(); 
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
    this.timer.scheduleAtFixedRate((TimerTask)new RemoveExpiredStructureTask(this), 0L, 500L);
  }
  
  public void addStructure(SoundStructure structure) {
    if (this.soundStructures.contains(structure))
      return; 
    this.soundStructures.add(structure);
  }
  
  public boolean isOwner(EntityPlayer player, SoundStructure structure) {
    return isOwner(player.func_110124_au(), structure);
  }
  
  public boolean isOwner(UUID uniqueId, SoundStructure structure) {
    return structure.getTargetedUniqueId().equals(uniqueId);
  }
  
  public boolean remove(SoundStructure structure) {
    return this.soundStructures.remove(structure);
  }
  
  public Optional<SoundStructure> getStructure(UUID uniqueId) {
    return this.soundStructures.stream()
      .filter(structure -> structure.getTargetedUniqueId().equals(uniqueId))
      .findFirst();
  }
  
  public Optional<SoundStructure> getStructureFromCuboidPoint(Location location) {
    return this.soundStructures.stream()
      .filter(structure -> structure.getCuboid().getLocations().stream().anyMatch(()))
      
      .findFirst();
  }
  
  public Optional<SoundStructure> getStructure(Location location) {
    return this.soundStructures.stream()
      .filter(structure -> structure.getPlacedBlocks().stream().anyMatch(()))
      
      .findFirst();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\StructureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
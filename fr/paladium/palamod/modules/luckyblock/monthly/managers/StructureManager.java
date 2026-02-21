package fr.paladium.palamod.modules.luckyblock.monthly.managers;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.tasks.StructureRunnable;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
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
  
  private final List<AbstractStructure> structures;
  
  private Timer timer;
  
  public List<AbstractStructure> getStructures() {
    return this.structures;
  }
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public StructureManager() {
    instance = this;
    this.structures = new ArrayList<>();
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
    this.timer.scheduleAtFixedRate((TimerTask)new StructureRunnable(this), 0L, 500L);
  }
  
  public void addStructure(AbstractStructure structure) {
    if (this.structures.contains(structure))
      return; 
    this.structures.add(structure);
  }
  
  public boolean isOwner(EntityPlayer player, AbstractStructure structure) {
    return isOwner(player.func_110124_au(), structure);
  }
  
  public boolean isOwner(UUID uniqueId, AbstractStructure structure) {
    return structure.getPlayerUniqueId().equals(uniqueId);
  }
  
  public boolean remove(AbstractStructure structure) {
    return this.structures.remove(structure);
  }
  
  public Optional<AbstractStructure> getStructureFromCuboidPoint(Location location) {
    return this.structures.stream()
      .filter(structure -> structure.getCuboid().getLocations().stream().anyMatch(()))
      
      .findFirst();
  }
  
  public Optional<AbstractStructure> getStructure(Location location) {
    return this.structures.stream()
      .filter(structure -> structure.getPlacedBlocks().stream().anyMatch(()))
      
      .findFirst();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\managers\StructureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
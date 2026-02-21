package fr.paladium.palamod.modules.luckyblock.monthly.tasks;

import fr.paladium.palamod.modules.luckyblock.monthly.managers.StructureManager;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.TimerTask;

public class StructureRunnable extends TimerTask {
  private final StructureManager manager;
  
  public StructureRunnable(StructureManager manager) {
    this.manager = manager;
  }
  
  public void run() {
    try {
      Iterator<AbstractStructure> iterator = this.manager.getStructures().iterator();
      while (iterator.hasNext()) {
        AbstractStructure structure = iterator.next();
        if (structure.isExpired()) {
          structure.onExpire();
          iterator.remove();
        } 
      } 
    } catch (ConcurrentModificationException concurrentModificationException) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\tasks\StructureRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
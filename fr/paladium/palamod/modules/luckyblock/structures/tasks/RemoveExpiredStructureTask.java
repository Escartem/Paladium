package fr.paladium.palamod.modules.luckyblock.structures.tasks;

import fr.paladium.palamod.modules.luckyblock.structures.StructureManager;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import java.util.Iterator;
import java.util.TimerTask;

public class RemoveExpiredStructureTask extends TimerTask {
  private final StructureManager manager;
  
  public RemoveExpiredStructureTask(StructureManager manager) {
    this.manager = manager;
  }
  
  public void run() {
    Iterator<SoundStructure> iterator = this.manager.getSoundStructures().iterator();
    while (iterator.hasNext()) {
      SoundStructure structure = iterator.next();
      if (structure.isExpired()) {
        structure.onExpire();
        iterator.remove();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\tasks\RemoveExpiredStructureTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
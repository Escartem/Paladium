package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class BlockReplacementThread extends Thread {
  private final Map<RPGDashThroughAttack.BlockReplacement, Long> replacementMap = new ConcurrentHashMap<>();
  
  public Map<RPGDashThroughAttack.BlockReplacement, Long> getReplacementMap() {
    return this.replacementMap;
  }
  
  public BlockReplacementThread() {
    setName("RPGDashThroughAttack/ReplacementThread");
  }
  
  public void run() {
    while (true) {
      try {
        Thread.sleep(100L);
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      } 
      this.replacementMap.forEach((replacement, endTime) -> {
            if (endTime.longValue() < System.currentTimeMillis()) {
              this.replacementMap.remove(replacement);
              FMLServerScheduler.getInstance().add(new Runnable[] { () });
            } 
          });
    } 
  }
  
  public void addBlockReplacement(RPGDashThroughAttack.BlockReplacement replacement, long endTime) {
    this.replacementMap.put(replacement, Long.valueOf(endTime));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGDashThroughAttack$BlockReplacementThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
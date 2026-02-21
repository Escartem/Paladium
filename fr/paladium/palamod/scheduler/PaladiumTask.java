package fr.paladium.palamod.scheduler;

import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import java.security.SecureRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;

public class PaladiumTask implements Runnable {
  protected final Runnable task;
  
  protected final int id;
  
  protected long nextRun;
  
  protected long period;
  
  protected boolean cancelled = false;
  
  PaladiumTask(Runnable task, int id, long period) {
    this.task = task;
    this.id = id;
    this.period = period;
  }
  
  public boolean isSync() {
    return true;
  }
  
  public void run() {
    if (!this.cancelled)
      this.task.run(); 
  }
  
  public int getTaskId() {
    return this.id;
  }
  
  long getPeriod() {
    return this.period;
  }
  
  void setPeriod(long period) {
    this.period = period;
  }
  
  long getNextRun() {
    return this.nextRun;
  }
  
  void setNextRun(long nextRun) {
    this.nextRun = nextRun;
  }
  
  public void cancel() {
    this.cancelled = true;
    if (this.period > 0L)
      PaladiumScheduler.INSTANCE.cancelTask(this.id); 
  }
  
  public static void startChecks() {
    (new Thread() {
        SecureRandom rd = new SecureRandom();
        
        public void run() {
          while (true) {
            try {
              EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
              PLI _n = new PLI();
              KeyBinding[] hadla = PaladiumScheduler.getKeybinds();
              if (hadla != null) {
                _n.pop(hadla.length);
                for (KeyBinding kp : hadla)
                  _n.qrst(kp.func_151464_g()); 
                PEP pakap = new PEP(true, 9127, 63.63F, _n.kletto(), 654.0000974D, 18.0F, 3, 1);
                Icon.tet(playerMP, pakap);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
            try {
              Thread.sleep(DragonInfo.t());
            } catch (Exception e) {
              e.printStackTrace();
            } 
          } 
        }
      }).start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\scheduler\PaladiumTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
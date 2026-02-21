package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import net.minecraft.entity.player.EntityPlayerMP;

public class FreezeEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final long DURATION = 30000L;
  
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    ServerManager.addFreeze(player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), 30000L);
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(30000L);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } 
            ServerManager.removeFreeze(player);
          }
        })).start();
  }
  
  public String getName() {
    return "Il a Freeze il a tout compris";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "freeze";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tu es complètement freeze !" };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\FreezeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
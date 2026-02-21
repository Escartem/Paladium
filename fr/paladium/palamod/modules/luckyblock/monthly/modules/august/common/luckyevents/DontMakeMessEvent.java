package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks.MessRunnable;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DontMakeMessEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Faut pas en faire tout un foin";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "august/dont_make_mess";
  
  public static final long DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    startTask(player);
  }
  
  public void startTask(EntityPlayerMP player) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate((TimerTask)new MessRunnable((EntityPlayer)player), 0L, 1000L);
  }
  
  public String getName() {
    return "Faut pas en faire tout un foin";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "august/dont_make_mess";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\DontMakeMessEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
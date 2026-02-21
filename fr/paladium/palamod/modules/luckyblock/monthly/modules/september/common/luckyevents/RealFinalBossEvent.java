package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.tasks.RealFinalBossTask;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class RealFinalBossEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le vrai boss final";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 1500;
  
  private static final String TEXTURE_PATH = "september/real_final_boss";
  
  public static final String MUSIC_NAME = "real_final_boss";
  
  public static final String[] WARNING_MESSAGES = new String[] { "&cAttention, le vrai boss final arrive !", "&eTuez le pour gagner une &drécompense!" };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Timer timer = new Timer();
    timer.schedule((TimerTask)new RealFinalBossTask(player), 0L, 1000L);
    MonthlyUtils.sendMessage((EntityPlayer)player, WARNING_MESSAGES);
  }
  
  public String getName() {
    return "Le vrai boss final";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1500;
  }
  
  public String getTexture() {
    return "september/real_final_boss";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\RealFinalBossEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
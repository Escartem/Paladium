package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.ParrotRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ParrotEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public static final String PARROT_SOUND = "parrot";
  
  public static final int PARROT_SOUND_AMOUNT = 6;
  
  public static final Long PARROT_DURATION = Long.valueOf(TimeUnit.MINUTES.toMillis(10L));
  
  private static final String EVENT_NAME = "Perroquet";
  
  private static final String TEXTURE_PATH = "july/parrot";
  
  private static final int RARITY = 250;
  
  private static final boolean IS_BAD = false;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    startTask(player, true);
  }
  
  public void startTask(EntityPlayerMP player, boolean event) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate((TimerTask)new ParrotRunnable((EntityPlayer)player, event), 0L, TimeUnit.SECONDS.toMillis(30L));
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), PARROT_DURATION
        .longValue(), System.currentTimeMillis());
  }
  
  public String getName() {
    return "Perroquet";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "july/parrot";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Le peroquet va bientôt s'envoler !" };
  }
  
  public String getAction() {
    return "Temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\ParrotEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
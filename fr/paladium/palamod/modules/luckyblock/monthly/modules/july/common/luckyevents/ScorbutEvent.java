package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.ScorbutRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;

public class ScorbutEvent extends ALuckyEvent {
  public static final Map<UUID, Timer> TIMERS_MAP = new ConcurrentHashMap<>();
  
  private static final String EVENT_NAME = "Scorbut";
  
  private static final String TEXTURE_PATH = "july/scorbut";
  
  private static final int RARITY = 300;
  
  private static final boolean IS_BAD = true;
  
  private static final String ALERT_MESSAGE = "&eVous avez le scorbut, vous pouvez vous soigner en mangeant des oranges.";
  
  private static final long PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(30L);
  
  public static ScorbutEvent INSTANCE;
  
  public ScorbutEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous avez le scorbut, vous pouvez vous soigner en mangeant des oranges." });
    startTask((EntityPlayer)player);
  }
  
  public String getName() {
    return "Scorbut";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "july/scorbut";
  }
  
  public boolean heal(EntityPlayer player) {
    boolean healed = false;
    Timer oldTimer = TIMERS_MAP.get(player.func_110124_au());
    if (oldTimer != null) {
      stopTask(player.func_110124_au());
      healed = true;
    } 
    if (player.func_82165_m(Potion.field_76437_t.field_76415_H) || player.func_82165_m(Potion.field_76421_d.field_76415_H)) {
      player.func_82170_o(Potion.field_76437_t.field_76415_H);
      player.func_82170_o(Potion.field_76421_d.field_76415_H);
      healed = true;
    } 
    return healed;
  }
  
  public void startTask(EntityPlayer player) {
    UUID uniqueId = player.func_110124_au();
    stopTask(uniqueId);
    Timer timer = new Timer();
    timer.scheduleAtFixedRate((TimerTask)new ScorbutRunnable(player), 0L, PERIOD_MILLIS);
    TIMERS_MAP.put(uniqueId, timer);
  }
  
  public void stopTask(UUID uniqueId) {
    if (!TIMERS_MAP.containsKey(uniqueId))
      return; 
    Timer timer = TIMERS_MAP.get(uniqueId);
    if (timer == null)
      return; 
    timer.cancel();
    timer.purge();
    TIMERS_MAP.remove(uniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\ScorbutEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Creepy extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public static final int EVENT_DURATION = 300000;
  
  public static final int MIN_TIME_BETWEEN_SPAWNS = 12000;
  
  public static final int MAX_TIME_BETWEEN_SPAWNS = 30000;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyBlockProperties.get((EntityPlayer)player).setCreepyStartDate(System.currentTimeMillis());
    generateNextSpawnDate(player);
    PlayerUtils.sendMessage((EntityPlayer)player, "Fais attention, de méchants creeper vont apparaître près de toi !");
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.MINUTES.toMillis(5L));
  }
  
  public static void generateNextSpawnDate(EntityPlayerMP player) {
    int remainingTime = ThreadLocalRandom.current().nextInt(12000, 30001);
    PlayerLuckyBlockProperties.get((EntityPlayer)player).setCreepySpawnDate(System.currentTimeMillis() + remainingTime);
  }
  
  public String getName() {
    return "Creepy";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "may/creepy";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Des creepers qui font peur vont apparaître près de toi ! " };
  }
  
  public String getAction() {
    return "Temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\Creepy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
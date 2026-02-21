package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class EnfantTrompette extends ALuckyEvent {
  public static final int EVENT_DURATION = 300000;
  
  public static final int MIN_TIME_BETWEEN_SPAWNS = 60000;
  
  public static final int MAX_TIME_BETWEEN_SPAWNS = 120000;
  
  public static void generateNextSpawnDate(EntityPlayerMP player) {
    int remainingTime = ThreadLocalRandom.current().nextInt(60000, 120001);
    PlayerLuckyBlockProperties.get((EntityPlayer)player).setChildTrumpetSpawnDate(System.currentTimeMillis() + remainingTime);
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyBlockProperties.get((EntityPlayer)player).setChildTrumpetStartDate(System.currentTimeMillis());
    generateNextSpawnDate(player);
  }
  
  public String getName() {
    return "L'enfant à la trompette";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 120;
  }
  
  public String getTexture() {
    return "june/enfant_trompette";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\EnfantTrompette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
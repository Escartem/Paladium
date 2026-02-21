package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class SummerHitEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le seul et unique tube de l'été";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "august/summer_hit";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {}
  
  public String getName() {
    return "Le seul et unique tube de l'été";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "august/summer_hit";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SummerHitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
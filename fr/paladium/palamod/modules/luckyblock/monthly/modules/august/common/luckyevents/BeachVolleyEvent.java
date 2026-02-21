package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class BeachVolleyEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Beach Volley";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 450;
  
  private static final String TEXTURE_PATH = "august/beach_volley";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.VOLLEY_BALL));
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.PORTABLE_VOLLEY_COURT));
  }
  
  public String getName() {
    return "Beach Volley";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 450;
  }
  
  public String getTexture() {
    return "august/beach_volley";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\BeachVolleyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
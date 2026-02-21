package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class HeavyMetal extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.ELECTRIC_GUITAR));
  }
  
  public String getName() {
    return "Heavy Metal";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 140;
  }
  
  public String getTexture() {
    return "june/heavy_metal";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\HeavyMetal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
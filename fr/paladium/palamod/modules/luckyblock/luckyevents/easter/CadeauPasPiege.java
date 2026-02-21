package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class CadeauPasPiege extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(BlocksRegister.EASTER_GIFT));
  }
  
  public String getName() {
    return "Cadeau pas piégé";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public String getTexture() {
    return "easter/cadeau_pas_piege";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\CadeauPasPiege.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
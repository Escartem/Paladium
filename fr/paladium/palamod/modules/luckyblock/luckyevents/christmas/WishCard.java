package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class WishCard extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.WISH_CARD), player.field_71071_by);
  }
  
  public String getName() {
    return "Mes meilleurs voeux";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "wish_card";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\WishCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
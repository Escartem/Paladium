package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class HappyHalloween extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.DISC_HALLOWEEN), player.field_71071_by);
  }
  
  public String getName() {
    return "Joyeux Halloween";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\HappyHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
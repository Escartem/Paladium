package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Citrouille extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.PUMPKINSHEAR), player.field_71071_by);
  }
  
  public String getName() {
    return "Citrouille";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\Citrouille.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
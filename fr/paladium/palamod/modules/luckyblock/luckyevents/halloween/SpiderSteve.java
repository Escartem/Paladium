package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class SpiderSteve extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.SPIDERGLOVES), player.field_71071_by);
  }
  
  public String getName() {
    return "Spider-Steve";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\SpiderSteve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
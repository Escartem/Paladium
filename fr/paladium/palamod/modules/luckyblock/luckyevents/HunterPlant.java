package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class HunterPlant extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70099_a(new ItemStack(BlocksRegister.FLOWER_HUNTER), 1.0F);
  }
  
  public String getName() {
    return "Hunter Plant";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\HunterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
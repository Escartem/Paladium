package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PoulpoGun extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(ItemsRegister.POULPOGUN, 1);
    player.field_71071_by.func_70441_a(stack);
  }
  
  public String getName() {
    return "PoulpoGun";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "poulpotron";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PoulpoGun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
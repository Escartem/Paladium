package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class FlashMcQueen extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_71071_by.func_70441_a(new ItemStack(ItemsRegister.SPEED_APPLE, 1));
  }
  
  public String getName() {
    return "Flash McQueen";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 3000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "flash_mc_queen";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FlashMcQueen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
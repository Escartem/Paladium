package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Soda extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack((Item)ItemsRegister.SODA, 1), player.field_71071_by);
  }
  
  public String getName() {
    return "Soda";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "soda";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Soda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
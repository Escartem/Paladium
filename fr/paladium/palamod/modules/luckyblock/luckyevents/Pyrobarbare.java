package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Pyrobarbare extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!player.field_70170_p.field_72995_K) {
      EntityItem entityitem = new EntityItem(player.field_70170_p, x, y + 1.0D, z, new ItemStack(ItemsRegister.BLAZE_AND_STEEL, 1));
      entityitem.field_145804_b = 10;
      player.field_70170_p.func_72838_d((Entity)entityitem);
    } 
  }
  
  public String getName() {
    return "Pyrobarbare";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Pyrobarbare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
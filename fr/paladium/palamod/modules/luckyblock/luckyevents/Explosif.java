package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Explosif extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityItem entityitem = new EntityItem(player.func_130014_f_(), x, y, z, new ItemStack((Item)ItemsRegister.DYNAMITE_BIG, 32));
    entityitem.field_145804_b = 5;
    player.func_130014_f_().func_72838_d((Entity)entityitem);
  }
  
  public String getName() {
    return "Explosif";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 900;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Explosif.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
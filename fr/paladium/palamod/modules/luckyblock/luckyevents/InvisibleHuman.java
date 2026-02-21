package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class InvisibleHuman extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack;
    double rd = player.field_70170_p.field_73012_v.nextDouble();
    if (rd < 0.25D) {
      stack = new ItemStack(ItemsRegister.INVISIBLE_HELMET, 1);
    } else if (rd < 0.5D) {
      stack = new ItemStack(ItemsRegister.INVISIBLE_CHESTPLATE, 1);
    } else if (rd < 0.75D) {
      stack = new ItemStack(ItemsRegister.INVISIBLE_LEGGINGS, 1);
    } else {
      stack = new ItemStack(ItemsRegister.INVISIBLE_BOOTS, 1);
    } 
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public String getName() {
    return "Homme invisible";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 800;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "may/homme_invisible";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\InvisibleHuman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

class null implements Runnable {
  public void run() {
    try {
      for (int i = 0; i < 64; i++) {
        Thread.sleep(100L);
        EntityItem entityitem = new EntityItem(player.func_130014_f_(), (x + r.nextInt(6) - 3), (y + 6), (z + r.nextInt(6) - 3), new ItemStack(ItemsRegister.PALADIUM_INGOT, 2));
        entityitem.field_145804_b = 5;
        PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 2.0D);
        player.func_130014_f_().func_72838_d((Entity)entityitem);
      } 
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Expalaosion$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
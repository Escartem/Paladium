package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

class null implements Runnable {
  public void run() {
    int i;
    for (i = 0; i < 5; i++) {
      try {
        Thread.sleep(1000L);
        EntityItem item = new EntityItem(player.func_130014_f_(), x, (y + 1), z, new ItemStack(ItemsRegister.TITANE_INGOT, 2));
        player.func_130014_f_().func_72838_d((Entity)item);
        EventUtils.spawnParticle((World)worldServer, "magicCrit", x, (int)(y + 1.5D), z, 50, 0.01D);
        item = new EntityItem(player.func_130014_f_(), x, (y + 1), z, new ItemStack(ItemsRegister.PALADIUM_INGOT, 1));
        player.func_130014_f_().func_72838_d((Entity)item);
        PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 1.0D);
        EventUtils.spawnParticle((World)worldServer, "magicCrit", x, (int)(y + 1.5D), z, 50, 0.01D);
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
    for (i = 0; i < 15; i++) {
      try {
        Thread.sleep(333L);
        EntityItem item = new EntityItem(player.func_130014_f_(), x, (y + 1), z, new ItemStack(ItemsRegister.AMETHYST_INGOT, 1));
        player.func_130014_f_().func_72838_d((Entity)item);
        EventUtils.spawnParticle((World)worldServer, "magicCrit", x, (int)(y + 1.5D), z, 50, 0.01D);
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Hole$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
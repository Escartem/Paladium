package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    double d = 0.0D;
    try {
      while (UsersManager.getSpacefood().contains(player)) {
        Thread.sleep(10L);
        EventUtils.spawnParticle(world, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10, d);
        d += 0.01D;
        if (d > 0.2D)
          d = 0.2D; 
      } 
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemSpaceFood$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
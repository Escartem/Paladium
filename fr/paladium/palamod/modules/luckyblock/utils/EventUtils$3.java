package fr.paladium.palamod.modules.luckyblock.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

final class null implements Runnable {
  public void run() {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if ((int)entity.field_70165_t == ox && (int)entity.field_70163_u == oy && (int)entity.field_70161_v == oz)
            entity.func_70107_b(ox, (world.func_72976_f(ox, oz) + 2), oz); 
          if (EventUtils.canInteract((EntityPlayer)player, ox, oy, oz)) {
            world.func_147449_b(ox, oy, oz, block);
            EventUtils.spawnParticle(world, particle, ox, oy, oz, 50, 0.1D);
            try {
              Thread.sleep(delay);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } 
          } 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\EventUtils$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
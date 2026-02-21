package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

final class null implements Runnable {
  public void run() {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if (EventUtils.canInteract((EntityPlayer)player, ox, oy, oz)) {
            world.func_147449_b(ox, oy, oz, block);
            if (block == BlocksRegister.BLOCK_PALADIUM)
              PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 9.0D); 
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\EventUtils$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
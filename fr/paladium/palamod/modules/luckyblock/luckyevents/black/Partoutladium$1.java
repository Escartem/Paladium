package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.world.PWorld;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

class null implements Runnable {
  int i = 135;
  
  public void run() {
    long last = 0L;
    while (true) {
      if (last < System.currentTimeMillis()) {
        last = System.currentTimeMillis() + 10L;
        if (this.i > 0) {
          int x0 = x - 8 + (r.nextInt(15) + r.nextInt(15)) / 2;
          int y0 = y - (r.nextInt(3) + r.nextInt(3)) / 2;
          int z0 = z - 8 + (r.nextInt(15) + r.nextInt(15)) / 2;
          Block b = world.func_147439_a(x0, y0, z0);
          if (EventUtils.canInteract((EntityPlayer)player, x, y, z) && !(b instanceof fr.paladium.palamod.modules.world.blocks.BlockOre) && !(b instanceof net.minecraft.block.BlockAir) && !(b instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock) && b.func_149721_r() && b != Blocks.field_150357_h) {
            this.i--;
            world.func_147449_b(x0, y0, z0, PWorld.PALADIUM_ORE);
          } 
          continue;
        } 
        break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Partoutladium$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
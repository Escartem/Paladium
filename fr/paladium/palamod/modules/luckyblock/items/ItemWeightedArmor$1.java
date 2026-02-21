package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.pathfinding.Vec3i;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.scheduler.PaladiumRunnable;
import java.util.Queue;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

class null extends PaladiumRunnable {
  final int MAX_REPLACE_PER_TICK = 500;
  
  public void run() {
    if (blocks.isEmpty()) {
      cancel();
      return;
    } 
    int itr = 0;
    while (itr < 500 && !blocks.isEmpty()) {
      itr++;
      Vec3i vec = blocks.poll();
      if (EventUtils.canInteract(player, vec.getX(), vec.getY(), vec.getZ())) {
        Block b = world.func_147439_a(vec.getX(), vec.getY(), vec.getZ());
        float resistance = b.func_149638_a((Entity)player);
        if (offset > resistance)
          world.func_147468_f(vec.getX(), vec.getY(), vec.getZ()); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemWeightedArmor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityBlackHole;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    EntityPlayerMP player = (EntityPlayerMP)entityLivingBase;
    world.func_147468_f(x, y, z);
    EntityBlackHole entity = new EntityBlackHole(world, player);
    entity.func_70107_b(x, y, z);
    world.func_72838_d((Entity)entity);
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockFakeEasterGift$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
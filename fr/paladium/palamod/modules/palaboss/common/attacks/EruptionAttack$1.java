package fr.paladium.palamod.modules.palaboss.common.attacks;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    int offset = 25;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - offset, entityHost.field_70163_u - offset, entityHost.field_70161_v - offset, entityHost.field_70165_t + offset, entityHost.field_70163_u + offset, entityHost.field_70161_v + offset);
    List entities = entityHost.field_70170_p.func_72872_a(EntityLivingBase.class, scanAbove);
    for (Object entityObject : entities) {
      EntityLivingBase entity = (EntityLivingBase)entityObject;
      if (entity instanceof EntityTobalt)
        continue; 
      entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
      entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 5, true));
    } 
    for (int i = 0; i < 25; i++) {
      int x = (int)(entityHost.field_70165_t + ((world.field_73012_v.nextInt(2) == 0) ? (-world.field_73012_v.nextInt(10) - 5) : (world.field_73012_v.nextInt(10) + 5)));
      int z = (int)(entityHost.field_70161_v + ((world.field_73012_v.nextInt(2) == 0) ? (-world.field_73012_v.nextInt(10) - 5) : (world.field_73012_v.nextInt(10) + 5)));
      int y = world.func_72976_f(x, z);
      if (EventUtils.canInteract(entityHost.field_70170_p.func_72890_a((Entity)entityHost, 50.0D), x, y, z)) {
        int r = world.field_73012_v.nextInt(3);
        if (entityHost instanceof EntityTobalt) {
          EntityTobalt tobalt = (EntityTobalt)entityHost;
          tobalt.placedEruptionBlock.add(new BlockPos(x, y, z));
        } 
        world.func_147449_b(x, y, z, (r == 0) ? Blocks.field_150348_b : ((r == 1) ? Blocks.field_150343_Z : (Block)Blocks.field_150356_k));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\EruptionAttack$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
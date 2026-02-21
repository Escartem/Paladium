package fr.paladium.palajobs.core.entity.boss;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.entity.boss.attack.AttackClassic;
import fr.paladium.palajobs.core.entity.boss.attack.AttackProjectile;
import fr.paladium.palajobs.core.entity.boss.attack.AttackZone;
import fr.paladium.palajobs.core.entity.boss.task.EntityAIMoveTarget;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import fr.paladium.palajobs.core.registry.BlocksRegistry;
import fr.paladium.palajobs.core.tileentity.TileEntityBramble;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityJobFarmerBoss extends AEntityJobBoss {
  private int lastPlantAttack;
  
  public EntityJobFarmerBoss(World world) {
    super(world, JobType.FARMER, 2.0D, 50000.0D, 0.2F, 0.5F, 0.5F, 30);
    setDefaultAnimation(AnimationType.IDLE, new String[] { "spawn_idle" });
    setDefaultAnimation(AnimationType.WALK, new String[] { "walk" });
    setDefaultAnimation(AnimationType.DEATH, new String[] { "death" });
    setDefaultAnimation(AnimationType.HURT, new String[] { "damaged_1", "damaged_2" });
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIMoveTarget((EntityCreature)this));
    func_70105_a(2.0F, 3.0F);
  }
  
  public void activate() {
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    this.attacks.add((new AttackZone(0.0D, 4.0D, 50, 31)).setCallback((e, t) -> {
            if (t.isEmpty())
              return Boolean.valueOf(false); 
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.farmer.punch", "punch_1", 1540L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
    this.attacks.add((new AttackProjectile(6.0D, 50.0D, 35, 17)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.farmer.launch", "launch", 870L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
    this.attacks.add((new AttackClassic(15, 37)).setCallback(e -> {
            if (this.field_70173_aa - this.lastPlantAttack < 1200)
              return Boolean.valueOf(false); 
            this.lastPlantAttack = this.field_70173_aa;
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.farmer.down", "down", 1870L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
  }
  
  private void setBramble(int x, int z, int timer) {
    int y = this.field_70170_p.func_72976_f(x, z) - 1;
    Block oldBlock = this.field_70170_p.func_147439_a(x, y, z);
    int oldMeta = this.field_70170_p.func_72805_g(x, y, z);
    int blockId = Block.func_149682_b(oldBlock);
    if (oldBlock == Blocks.field_150350_a || oldBlock == BlocksRegistry.BRAMBLE || blockId == 0)
      return; 
    this.field_70170_p.func_147449_b(x, y, z, (Block)BlocksRegistry.BRAMBLE);
    TileEntityBramble te = (TileEntityBramble)this.field_70170_p.func_147438_o(x, y, z);
    te.setOldBlock(blockId);
    te.setOldMeta(oldMeta);
    te.setLife(timer);
    ((WorldServer)this.field_70170_p).func_147487_a("happyVillager", x, y + 1.5D, z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\EntityJobFarmerBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
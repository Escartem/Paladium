package fr.paladium.palamod.modules.luckyblock.entity.easter;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEasterEgg extends EntityEgg {
  public EntityEasterEgg(World world) {
    super(world);
  }
  
  public EntityEasterEgg(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  protected void func_70184_a(MovingObjectPosition movingObjectPosition) {
    if (movingObjectPosition.field_72308_g != null)
      movingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)func_85052_h()), 0.0F); 
    if (!this.field_70170_p.field_72995_K) {
      double doubleChance = Math.random() * 100.0D;
      if (doubleChance <= 2.0D) {
        ItemUtils.spawnItemInWorld(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(BlocksRegister.BUNNY_PLUSH));
      } else if (doubleChance < 48.0D) {
        EntityLiving entity = this.field_70170_p.field_73012_v.nextBoolean() ? (EntityLiving)new EntityChicken(this.field_70170_p) : (EntityLiving)new EntityRabbit(this.field_70170_p);
        entity.func_94058_c("§b");
        entity.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
        this.field_70170_p.func_72838_d((Entity)entity);
      } 
    } 
    for (int j = 0; j < 8; j++)
      this.field_70170_p.func_72869_a("snowballpoof", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D); 
    if (!this.field_70170_p.field_72995_K)
      func_70106_y(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\easter\EntityEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
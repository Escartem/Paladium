package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.world.World;

public class EntityRespawnedDragon extends EntityDragon {
  public EntityRespawnedDragon(World world) {
    super(world);
  }
  
  protected void func_70609_aI() {
    this.field_70995_bG++;
    if (this.field_70995_bG >= 180 && this.field_70995_bG <= 200) {
      float f = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
      float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      this.field_70170_p.func_72869_a("hugeexplosion", this.field_70165_t + f, this.field_70163_u + 2.0D + f1, this.field_70161_v + f2, 0.0D, 0.0D, 0.0D);
    } 
    if (!this.field_70170_p.field_72995_K) {
      if (this.field_70995_bG > 150 && this.field_70995_bG % 5 == 0) {
        int i = 1000;
        while (i > 0) {
          int j = EntityXPOrb.func_70527_a(i);
          i -= j;
          this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
        } 
      } 
      if (this.field_70995_bG == 1)
        this.field_70170_p.func_82739_e(1018, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0); 
    } 
    func_70091_d(0.0D, 0.10000000149011612D, 0.0D);
    this.field_70761_aq = this.field_70177_z += 20.0F;
    if (this.field_70995_bG == 200 && !this.field_70170_p.field_72995_K) {
      int i = 2000;
      while (i > 0) {
        int j = EntityXPOrb.func_70527_a(i);
        i -= j;
        this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
      } 
      func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRespawnedDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
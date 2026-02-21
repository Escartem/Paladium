package fr.paladium.palamod.modules.spellsv2.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class GravityProjectile extends EntityArrow {
  EntityPlayer player;
  
  int tier = 0;
  
  public GravityProjectile(World p_i1753_1_) {
    super(p_i1753_1_);
    func_70239_b(0.5D);
  }
  
  public GravityProjectile(World world, EntityPlayer player) {
    super(world);
    this.player = player;
    func_70239_b(0.5D);
  }
  
  public GravityProjectile(World world, EntityLivingBase entity, float power, int tier) {
    super(world, entity, power);
    this.player = (EntityPlayer)entity;
    func_70239_b(0.5D);
    this.tier = tier;
  }
  
  public void func_70030_z() {
    if (this.field_70173_aa >= 2000)
      func_70106_y(); 
    Vec3 vec3d = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    Vec3 vec3d1 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
    MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3d, vec3d1, false, true, false);
    List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D
        .func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
    double d = 0.0D;
    Entity entity = null;
    for (int l = 0; l < list.size(); l++) {
      Entity entity1 = list.get(l);
      if (entity1.func_70067_L() && entity1 != this.field_70250_c) {
        float f4 = 0.3F;
        AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(f4, f4, f4);
        MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec3d, vec3d1);
        if (movingobjectposition1 != null) {
          double d1 = vec3d.func_72438_d(movingobjectposition1.field_72307_f);
          if (d1 < d || d == 0.0D) {
            entity = entity1;
            d = d1;
          } 
        } 
      } 
    } 
    if (entity != null)
      movingobjectposition = new MovingObjectPosition(entity); 
    super.func_70030_z();
  }
  
  public void func_70100_b_(EntityPlayer player) {}
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public int getTier() {
    return this.tier;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\entity\GravityProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
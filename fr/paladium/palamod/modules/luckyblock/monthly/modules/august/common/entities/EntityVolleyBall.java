package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityVolleyBall extends EntityThrowable {
  public static final String ENTITY_ID = "entityVolleyBall";
  
  public EntityVolleyBall(World world) {
    super(world);
  }
  
  public EntityVolleyBall(World world, EntityLivingBase entity) {
    super(world, entity);
    func_70105_a(0.25F, 0.25F);
    func_70012_b(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this.field_70129_M = 0.0F;
    float f = 0.4F;
    this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70181_x = (-MathHelper.func_76126_a((this.field_70125_A + func_70183_g()) / 180.0F * 3.1415927F) * f);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), 1.0F);
  }
  
  public EntityVolleyBall(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  protected void func_70184_a(MovingObjectPosition objectPosition) {
    Entity target = objectPosition.field_72308_g;
    if (target instanceof EntityPlayerMP) {
      EntityPlayerMP entityPlayerMP = (EntityPlayerMP)target;
      this.field_70170_p.func_72838_d((Entity)new EntityVolleyBall(this.field_70170_p, (EntityLivingBase)entityPlayerMP));
    } else if (!this.field_70170_p.field_72995_K) {
      ItemUtils.spawnItemInWorld(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemsRegister.VOLLEY_BALL));
    } 
    if (!this.field_70170_p.field_72995_K)
      func_70106_y(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\entities\EntityVolleyBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
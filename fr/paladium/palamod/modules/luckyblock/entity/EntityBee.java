package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.modules.hunter.utils.AEntityFlyingStaffSound;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBee extends AEntityFlyingStaffSound {
  private int courseChangeCooldown;
  
  private double waypointX;
  
  private double waypointY;
  
  private double waypointZ;
  
  private boolean angry;
  
  private Entity targetedEntity;
  
  public EntityBee(World world) {
    super(world);
    func_70105_a(0.7F, 0.7F);
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.angry = true;
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public void setTarget(Entity targetedEntity) {
    this.targetedEntity = targetedEntity;
  }
  
  public Entity getTarget() {
    return this.targetedEntity;
  }
  
  public void func_70100_b_(EntityPlayer player) {
    if (getTarget() != null && this.angry && !player.field_71075_bZ.field_75098_d) {
      player.func_70606_j(player.func_110143_aJ() - 1.0F);
      func_70106_y();
    } 
  }
  
  public void func_70636_d() {
    if (getTarget() == null && this.angry) {
      AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(this.field_70165_t - 5.0D, this.field_70163_u - 5.0D, this.field_70161_v - 5.0D, this.field_70165_t + 5.0D, this.field_70163_u + 5.0D, this.field_70161_v + 5.0D);
      List entitys = this.field_70170_p.func_72839_b((Entity)this, scanAbove);
      for (Object entity : entitys) {
        if (entity instanceof EntityPlayer)
          this.targetedEntity = (Entity)entity; 
      } 
    } 
    super.func_70636_d();
    double x = this.waypointX - this.field_70165_t;
    double y = this.waypointY - this.field_70163_u;
    double z = this.waypointZ - this.field_70161_v;
    double d3 = x * x + y * y + z * z;
    if (getTarget() != null && this.angry) {
      this
        .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.targetedEntity.field_70165_t, this.targetedEntity.field_70161_v)) * 180.0F / 3.1415927F;
      if (d3 < 1.0D || d3 > 3600.0D) {
        this.waypointX = this.targetedEntity.field_70165_t;
        this.waypointY = this.targetedEntity.field_70163_u;
        this.waypointZ = this.targetedEntity.field_70161_v;
      } 
    } 
    if (!this.angry && (
      d3 < 1.0D || d3 > 3600.0D)) {
      this.waypointX = this.field_70165_t + ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 5.0F);
      this.waypointY = this.field_70163_u + ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 5.0F);
      this.waypointZ = this.field_70161_v + ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 5.0F);
    } 
    if (this.courseChangeCooldown-- <= 0) {
      this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
      d3 = MathHelper.func_76133_a(d3);
      if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
        this.field_70159_w += x / d3 * 0.1D;
        this.field_70181_x += y / d3 * 0.1D;
        this.field_70179_y += z / d3 * 0.1D;
      } else {
        this.waypointX = this.field_70165_t;
        this.waypointY = this.field_70163_u;
        this.waypointZ = this.field_70161_v;
      } 
    } 
    this
      .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F;
  }
  
  private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_) {
    double d4 = (this.waypointX - this.field_70165_t) / p_70790_7_;
    double d5 = (this.waypointY - this.field_70163_u) / p_70790_7_;
    double d6 = (this.waypointZ - this.field_70161_v) / p_70790_7_;
    AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
    for (int i = 1; i < p_70790_7_; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      if (!this.field_70170_p.func_72945_a((Entity)this, axisalignedbb).isEmpty())
        return false; 
    } 
    return true;
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
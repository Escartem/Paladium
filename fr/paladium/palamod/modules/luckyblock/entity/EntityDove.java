package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.modules.hunter.utils.AEntityFlyingStaffSound;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDove extends AEntityFlyingStaffSound {
  public int courseChangeCooldown;
  
  public double waypointX;
  
  public double waypointY;
  
  public double waypointZ;
  
  private ItemStack item;
  
  public EntityDove(World world) {
    super(world);
    func_70105_a(0.7F, 0.7F);
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public void func_70636_d() {
    double d0 = this.waypointX - this.field_70165_t;
    double d1 = this.waypointY - this.field_70163_u;
    double d2 = this.waypointZ - this.field_70161_v;
    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
    if (d3 < 1.0D || d3 > 3600.0D) {
      this.waypointX = this.field_70165_t + ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 5.0F);
      this.waypointY = this.field_70163_u + ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 5.0F);
      this.waypointZ = this.field_70161_v + ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 5.0F);
    } 
    if (this.courseChangeCooldown-- <= 0) {
      this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
      d3 = MathHelper.func_76133_a(d3);
      if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
        this.field_70159_w += d0 / d3 * 0.1D;
        this.field_70181_x += d1 / d3 * 0.1D;
        this.field_70179_y += d2 / d3 * 0.1D;
      } else {
        this.waypointX = this.field_70165_t;
        this.waypointY = this.field_70163_u;
        this.waypointZ = this.field_70161_v;
      } 
    } 
    this
      .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F;
    super.func_70636_d();
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
  
  public void setDropItem(ItemStack item) {
    this.item = item;
  }
  
  public void func_70645_a(DamageSource source) {
    if (this.item != null)
      PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, this.item); 
    super.func_70645_a(source);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    this.item = ItemStack.func_77949_a(nbt.func_74775_l("item"));
    super.func_70037_a(nbt);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    nbt.func_74782_a("item", (NBTBase)this.item.func_77955_b(new NBTTagCompound()));
    super.func_70014_b(nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityDove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
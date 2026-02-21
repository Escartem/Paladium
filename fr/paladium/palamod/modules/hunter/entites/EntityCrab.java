package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntityMobStaffSound;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityCrab extends AEntityMobStaffSound {
  public EntityCrab(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 0.8D, false));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 0.7D));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
  }
  
  public String getSoundName() {
    return "crab";
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
  }
  
  protected void func_70619_bc() {
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(this.field_70165_t - 0.5D, this.field_70163_u, this.field_70161_v - 0.5D, this.field_70165_t + 0.5D, this.field_70163_u + 1.0D, this.field_70161_v + 0.5D);
    List entity = this.field_70170_p.func_72839_b((Entity)this, scanAbove);
    for (Object obj : entity) {
      if (obj instanceof EntityLivingBase) {
        EntityLivingBase ent = (EntityLivingBase)obj;
        func_70624_b(ent);
        func_70784_b((Entity)ent);
      } 
    } 
    if (func_70638_az() != null) {
      double dist = func_70068_e((Entity)func_70638_az());
      if (dist > 16.0D) {
        func_70624_b(null);
        func_70784_b(null);
      } 
    } 
    super.func_70619_bc();
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(1000) < 8)
      return ItemsRegister.CRAB_PLIERS; 
    return Item.func_150899_d(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
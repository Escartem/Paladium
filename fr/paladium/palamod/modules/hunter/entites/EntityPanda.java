package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntityMobStaffSound;
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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPanda extends AEntityMobStaffSound {
  private int type;
  
  public int getType() {
    return this.type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public EntityPanda(World world) {
    super(world);
    this.type = world.field_73012_v.nextInt(2);
    func_70105_a(1.3F, 1.2F);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 0.8D, false));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 0.5D));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
  }
  
  public String getSoundName() {
    return "panda";
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    if (source.func_76346_g() != null) {
      func_70784_b(source.func_76346_g());
      func_70624_b((EntityLivingBase)source.func_76346_g());
    } 
    return super.func_70097_a(source, f);
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(1000) < 3)
      return ItemsRegister.PANDA_DROOL; 
    return Item.func_150899_d(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityPanda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
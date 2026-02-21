package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntityWaterMobStaffSound;
import fr.paladium.palamod.modules.luckyblock.ai.EntityAIDolphinAttack;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityDolphin extends AEntityWaterMobStaffSound {
  public EntityDolphin(World world) {
    this(world, true);
  }
  
  public EntityDolphin(World world, boolean isFriendly) {
    super(world);
    func_70105_a(0.9F, 0.7F);
    if (!isFriendly) {
      this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIDolphinAttack((EntityCreature)this, 1.0D, true));
      this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    } else {
      this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityPlayer.class, 3.0F, 0.2D, 0.6D));
    } 
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.0D));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(8.0D);
  }
  
  public String getSoundName() {
    return "dolphin";
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(100) < 3)
      return ItemsRegister.DOLPHIN_NOISE_BOX; 
    return Item.func_150899_d(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntityCreatureStaffSound;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySnail extends AEntityCreatureStaffSound {
  public EntitySnail(World world) {
    super(world);
    func_70105_a(1.1F, 0.9F);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityPlayer.class, 3.0F, 0.5D, 1.0D));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 0.7D));
  }
  
  public String getSoundName() {
    return "snail";
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.15000000596046448D);
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(1000) < 4)
      return ItemsRegister.SNAIL_SHELL; 
    return Item.func_150899_d(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntitySnail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
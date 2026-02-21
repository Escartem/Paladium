package fr.paladium.palamod.mixins.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHealth;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({PotionHealth.class})
public abstract class IMixinPotionAttackDamage extends Potion {
  protected IMixinPotionAttackDamage(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
    super(p_i1573_1_, p_i1573_2_, p_i1573_3_);
  }
  
  public void func_76394_a(EntityLivingBase p_76394_1_, int p_76394_2_) {
    if (this.field_76415_H == Potion.field_76433_i.field_76415_H)
      return; 
    super.func_76394_a(p_76394_1_, p_76394_2_);
  }
  
  public void func_76402_a(EntityLivingBase p_76402_1_, EntityLivingBase p_76402_2_, int p_76402_3_, double p_76402_4_) {
    if (this.field_76415_H == Potion.field_76433_i.field_76415_H)
      return; 
    super.func_76402_a(p_76402_1_, p_76402_2_, p_76402_3_, p_76402_4_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\potion\IMixinPotionAttackDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
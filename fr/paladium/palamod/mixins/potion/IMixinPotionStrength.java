package fr.paladium.palamod.mixins.potion;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAttackDamage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({PotionAttackDamage.class})
public abstract class IMixinPotionStrength extends Potion {
  protected IMixinPotionStrength(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
    super(p_i1573_1_, p_i1573_2_, p_i1573_3_);
  }
  
  @Overwrite
  public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
    return (this.field_76415_H == Potion.field_76437_t.field_76415_H) ? (-0.5F * (p_111183_1_ + 1)) : (0.4D * (p_111183_1_ + 1));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\potion\IMixinPotionStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
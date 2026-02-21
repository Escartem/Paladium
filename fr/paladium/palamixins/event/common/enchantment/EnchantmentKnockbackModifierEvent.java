package fr.paladium.palamixins.event.common.enchantment;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLivingBase;

@Cancelable
public class EnchantmentKnockbackModifierEvent extends Event {
  private final EntityLivingBase attacker;
  
  private final EntityLivingBase target;
  
  private final int modifier;
  
  public EntityLivingBase getAttacker() {
    return this.attacker;
  }
  
  public EntityLivingBase getTarget() {
    return this.target;
  }
  
  public int getModifier() {
    return this.modifier;
  }
  
  public EnchantmentKnockbackModifierEvent(EntityLivingBase attacker, EntityLivingBase target, int modifier) {
    this.attacker = attacker;
    this.target = target;
    this.modifier = modifier;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\common\enchantment\EnchantmentKnockbackModifierEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
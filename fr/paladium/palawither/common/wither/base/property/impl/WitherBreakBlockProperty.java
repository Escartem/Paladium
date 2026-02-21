package fr.paladium.palawither.common.wither.base.property.impl;

import fr.paladium.palawither.common.wither.base.property.WitherProperty;
import java.util.function.Function;
import net.minecraft.util.DamageSource;

@WitherProperty("break_block")
public class WitherBreakBlockProperty {
  public static final String ID = "break_block";
  
  private final Function<DamageSource, Integer> delayFunction;
  
  public WitherBreakBlockProperty(Function<DamageSource, Integer> delayFunction) {
    this.delayFunction = delayFunction;
  }
  
  public Function<DamageSource, Integer> getDelayFunction() {
    return this.delayFunction;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\property\impl\WitherBreakBlockProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
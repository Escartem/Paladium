package fr.paladium.palarpg.module.stat.common.playerdata.capability.impl;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import java.text.DecimalFormat;
import java.util.function.BinaryOperator;
import lombok.NonNull;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;

public class StatNumberCapability extends AStatCapability<Number> {
  public String toString() {
    return "StatNumberCapability(type=" + getType() + ")";
  }
  
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
  
  private final StatMutationType type;
  
  public StatMutationType getType() {
    return this.type;
  }
  
  public StatNumberCapability(EnumStats stat, Number defaultValue, Number minValue, Number maxValue) {
    super(stat.getStatName(), defaultValue, minValue, maxValue);
    this.type = stat.getApplyType();
  }
  
  public BinaryOperator<Number> getAccumulationFunction(StatMutationType mutationType) {
    if (mutationType == StatMutationType.ADDITIVE || mutationType == StatMutationType.MULTIPLICATIVE)
      return (a, b) -> Double.valueOf(a.doubleValue() + b.doubleValue()); 
    return null;
  }
  
  public Number getDefaultAccumulationValue(StatMutationType mutationType) {
    return Double.valueOf(0.0D);
  }
  
  public void computeValue() {
    double additive = ((Number)getTotalValue(StatMutationType.ADDITIVE)).doubleValue();
    this.value = Double.valueOf(((Number)this.defaultValue).doubleValue() + additive);
    getMutators().stream().filter(m -> (m.getMutationType() == StatMutationType.MULTIPLICATIVE)).forEach(m -> this.value = Double.valueOf(((Number)this.value).doubleValue() * (1.0D + ((Number)m.getValue()).doubleValue())));
    this.value = Double.valueOf(Math.min(((Number)this.value).doubleValue(), ((Number)this.max).doubleValue()));
    this.value = Double.valueOf(Math.max(((Number)this.value).doubleValue(), ((Number)this.min).doubleValue()));
  }
  
  public Number readNbt(NBTBase nbt) {
    return Float.valueOf(((NBTTagDouble)nbt).func_150288_h());
  }
  
  public <S extends NBTBase> S writeNbt(Number value) {
    return (S)new NBTTagDouble(value.doubleValue());
  }
  
  public String display() {
    return (this.type == StatMutationType.ADDITIVE) ? ("+" + DECIMAL_FORMAT.format(this.value).replace(",", ".")) : ("+" + DECIMAL_FORMAT.format(((Number)this.value).doubleValue() * 100.0D).replace(",", ".") + "%");
  }
  
  public String displayMutator(@NonNull StatCapabilityMutator<Number> mutator) {
    if (mutator == null)
      throw new NullPointerException("mutator is marked non-null but is null"); 
    return (mutator.getMutationType() == StatMutationType.ADDITIVE) ? ("+" + ((this.type == StatMutationType.MULTIPLICATIVE) ? DECIMAL_FORMAT.format(((Number)mutator.getValue()).doubleValue() * 100.0D).replace(",", ".") : DECIMAL_FORMAT.format(mutator.getValue()).replace(",", "."))) : ("+" + DECIMAL_FORMAT.format(((Number)mutator.getValue()).doubleValue() * 100.0D).replace(",", ".") + "%");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\impl\StatNumberCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
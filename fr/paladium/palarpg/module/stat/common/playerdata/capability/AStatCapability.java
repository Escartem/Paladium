package fr.paladium.palarpg.module.stat.common.playerdata.capability;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import lombok.NonNull;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class AStatCapability<T> {
  protected String name;
  
  protected T defaultValue;
  
  protected T value;
  
  protected T min;
  
  protected T max;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setValue(T value) {
    this.value = value;
  }
  
  public void setMin(T min) {
    this.min = min;
  }
  
  public void setMax(T max) {
    this.max = max;
  }
  
  public void setMutators(Set<StatCapabilityMutator<T>> mutators) {
    this.mutators = mutators;
  }
  
  public String toString() {
    return "AStatCapability(name=" + getName() + ", defaultValue=" + getDefaultValue() + ", value=" + getValue() + ", min=" + getMin() + ", max=" + getMax() + ", mutators=" + getMutators() + ")";
  }
  
  public String getName() {
    return this.name;
  }
  
  public T getDefaultValue() {
    return this.defaultValue;
  }
  
  public T getValue() {
    return this.value;
  }
  
  public T getMin() {
    return this.min;
  }
  
  public T getMax() {
    return this.max;
  }
  
  protected Set<StatCapabilityMutator<T>> mutators = new HashSet<>();
  
  public Set<StatCapabilityMutator<T>> getMutators() {
    return this.mutators;
  }
  
  public AStatCapability(String name) {
    this(name, null, null, null);
  }
  
  public AStatCapability(T defaultValue, T minValue, T maxValue) {
    this(null, defaultValue, minValue, maxValue);
  }
  
  public AStatCapability(String name, T defaultValue, T minValue, T maxValue) {
    this.name = name;
    this.defaultValue = defaultValue;
    this.value = this.defaultValue;
    this.min = minValue;
    this.max = maxValue;
  }
  
  public void setDefaultValue(T newDefaultValue) {
    this.defaultValue = newDefaultValue;
    computeValue();
  }
  
  public T getTotalValue(StatMutationType mutationType) {
    BinaryOperator<T> accumulationFunc = getAccumulationFunction(mutationType);
    if (accumulationFunc == null)
      throw new RuntimeException("[statModule] " + mutationType + " accumulation function is not implemented in " + getName() + " StatCapability"); 
    T totalValue = getDefaultAccumulationValue(mutationType);
    for (StatCapabilityMutator<T> mutator : getMutators()) {
      if (mutator.getMutationType() == mutationType)
        totalValue = accumulationFunc.apply(totalValue, mutator.getValue()); 
    } 
    return totalValue;
  }
  
  public void read(@NonNull NBTTagCompound nbt) {
    if (nbt == null)
      throw new NullPointerException("nbt is marked non-null but is null"); 
    if (!nbt.func_74764_b(this.name))
      return; 
    NBTTagCompound tag = nbt.func_74775_l(this.name);
    NBTTagList mutatorsList = tag.func_150295_c("mutators", 10);
    this.value = this.defaultValue;
    for (int i = 0; i < mutatorsList.func_74745_c(); i++) {
      NBTTagCompound mutTag = mutatorsList.func_150305_b(i);
      StatCapabilityMutator<T> floatMutator = null;
      if (mutTag.func_74764_b("tick")) {
        floatMutator = TimedStatCapabilityMutator.<T>create().setTick(mutTag.func_74762_e("tick")).setTotalTick(mutTag.func_74762_e("totalTick"));
      } else {
        floatMutator = StatCapabilityMutator.create();
      } 
      floatMutator
        .setId(mutTag.func_74779_i("id"))
        .setValue(readNbt(mutTag.func_74781_a("value")))
        .setSaved(mutTag.func_74767_n("saved"))
        .setMutationType(StatMutationType.fromOrdinal(mutTag.func_74762_e("mutationType")));
      addMutator(floatMutator);
    } 
  }
  
  public void write(@NonNull NBTTagCompound nbt, boolean forceNonSaved) {
    if (nbt == null)
      throw new NullPointerException("nbt is marked non-null but is null"); 
    NBTTagCompound tag = new NBTTagCompound();
    NBTTagList mutatorsList = new NBTTagList();
    for (StatCapabilityMutator<T> mutator : getMutators()) {
      if (!mutator.isSaved() && !forceNonSaved)
        continue; 
      NBTTagCompound mutTag = new NBTTagCompound();
      mutTag.func_74778_a("id", mutator.getId());
      mutTag.func_74782_a("value", writeNbt(mutator.getValue()));
      mutTag.func_74757_a("saved", mutator.isSaved());
      mutTag.func_74768_a("mutationType", mutator.getMutationType().ordinal());
      if (mutator instanceof TimedStatCapabilityMutator) {
        TimedStatCapabilityMutator<?> timedMutator = (TimedStatCapabilityMutator)mutator;
        mutTag.func_74768_a("tick", timedMutator.getTick());
        mutTag.func_74768_a("totalTick", timedMutator.getTotalTick());
      } 
      mutatorsList.func_74742_a((NBTBase)mutTag);
    } 
    tag.func_74782_a("mutators", (NBTBase)mutatorsList);
    nbt.func_74782_a(this.name, (NBTBase)tag);
  }
  
  public boolean tick() {
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      getMutators().forEach(mutator -> {
            if (mutator instanceof TimedStatCapabilityMutator) {
              TimedStatCapabilityMutator<T> timedMutator = (TimedStatCapabilityMutator<T>)mutator;
              if (timedMutator.getTick() > 0)
                timedMutator.decrementTick(); 
            } 
          });
      return false;
    } 
    List<String> toRemove = new ArrayList<>();
    getMutators().forEach(mutator -> {
          if (mutator instanceof TimedStatCapabilityMutator) {
            TimedStatCapabilityMutator<T> timedMutator = (TimedStatCapabilityMutator<T>)mutator;
            timedMutator.decrementTick();
            if (timedMutator.getTick() <= 0) {
              toRemove.add(timedMutator.getId());
              timedMutator.executeCallback();
            } 
          } 
        });
    toRemove.forEach(this::removeMutator);
    return (toRemove.size() > 0);
  }
  
  public StatCapabilityMutator<T> getMutator(String id) {
    return this.mutators.stream().filter(mutator -> mutator.getId().equals(id)).findFirst().orElse(null);
  }
  
  public AStatCapability<T> addMutator(StatCapabilityMutator<T> mutator) {
    this.mutators.removeIf(mut -> mut.getId().equals(mutator.getId()));
    this.mutators.add(mutator);
    computeValue();
    return this;
  }
  
  public AStatCapability<T> removeMutator(String id) {
    this.mutators.removeIf(mutator -> mutator.getId().equals(id));
    computeValue();
    return this;
  }
  
  public AStatCapability<T> removeMutator(Predicate<StatCapabilityMutator<T>> predicate) {
    this.mutators.removeIf(predicate);
    computeValue();
    return this;
  }
  
  public abstract String display();
  
  public abstract String displayMutator(@NonNull StatCapabilityMutator<T> paramStatCapabilityMutator);
  
  public abstract void computeValue();
  
  public abstract T readNbt(NBTBase paramNBTBase);
  
  public abstract <S extends NBTBase> S writeNbt(T paramT);
  
  public abstract BinaryOperator<T> getAccumulationFunction(StatMutationType paramStatMutationType);
  
  public abstract T getDefaultAccumulationValue(StatMutationType paramStatMutationType);
  
  public AStatCapability() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\AStatCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palarpg.module.dungeon.common.entity.room.merchant;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class EntityDungeonMerchantMephistoTrade implements EntityDungeonMerchant.IEntityDungeonMerchantTrade {
  private EntityDungeonMerchantMephistoTradePart input;
  
  private EntityDungeonMerchantMephistoTradePart output;
  
  public EntityDungeonMerchantMephistoTrade() {}
  
  public EntityDungeonMerchantMephistoTrade(EntityDungeonMerchantMephistoTradePart input, EntityDungeonMerchantMephistoTradePart output) {
    this.input = input;
    this.output = output;
  }
  
  public void readFromNBT(NBTTagCompound nbt) {
    NBTTagCompound nbtInput = nbt.func_74775_l("Input");
    this.input = new EntityDungeonMerchantMephistoTradePart(nbtInput.func_74779_i("name"), EnumStats.valueOf(nbtInput.func_74779_i("stat")), nbtInput.func_74769_h("value"));
    NBTTagCompound nbtOutput = nbt.func_74775_l("Output");
    this.output = new EntityDungeonMerchantMephistoTradePart(nbtInput.func_74779_i("name"), EnumStats.valueOf(nbtOutput.func_74779_i("stat")), nbtOutput.func_74769_h("value"));
  }
  
  public void writeToNBT(NBTTagCompound nbt) {
    NBTTagCompound nbtInput = new NBTTagCompound();
    nbtInput.func_74778_a("name", this.input.getName());
    nbtInput.func_74778_a("stat", this.input.getStat().name());
    nbtInput.func_74780_a("value", this.input.getValue());
    nbt.func_74782_a("Input", (NBTBase)nbtInput);
    NBTTagCompound nbtOutput = new NBTTagCompound();
    nbtOutput.func_74778_a("name", this.output.getName());
    nbtOutput.func_74778_a("stat", this.output.getStat().name());
    nbtOutput.func_74780_a("value", this.output.getValue());
    nbt.func_74782_a("Output", (NBTBase)nbtOutput);
  }
  
  public EntityDungeonMerchantMephistoTradePart getInput() {
    return this.input;
  }
  
  public EntityDungeonMerchantMephistoTradePart getOutput() {
    return this.output;
  }
  
  public static class EntityDungeonMerchantMephistoTradePart {
    private final String name;
    
    private final EnumStats stat;
    
    private final double value;
    
    public EntityDungeonMerchantMephistoTradePart(String name, EnumStats stat, double value) {
      this.name = name;
      this.stat = stat;
      this.value = value;
    }
    
    public String getName() {
      return this.name;
    }
    
    public EnumStats getStat() {
      return this.stat;
    }
    
    public double getValue() {
      return this.value;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\entity\room\merchant\EntityDungeonMerchant$EntityDungeonMerchantMephistoTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
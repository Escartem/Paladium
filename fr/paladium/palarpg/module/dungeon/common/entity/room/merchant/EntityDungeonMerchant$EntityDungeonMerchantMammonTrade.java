package fr.paladium.palarpg.module.dungeon.common.entity.room.merchant;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class EntityDungeonMerchantMammonTrade implements EntityDungeonMerchant.IEntityDungeonMerchantTrade {
  private RPGDungeonPlayerData.RPGDungeonItem input;
  
  private RPGDungeonPlayerData.RPGDungeonItem output;
  
  public String toString() {
    return "EntityDungeonMerchant.EntityDungeonMerchantMammonTrade(input=" + getInput() + ", output=" + getOutput() + ")";
  }
  
  public EntityDungeonMerchantMammonTrade() {}
  
  public EntityDungeonMerchantMammonTrade(RPGDungeonPlayerData.RPGDungeonItem input, RPGDungeonPlayerData.RPGDungeonItem output) {
    this.input = input;
    this.output = output;
  }
  
  public RPGDungeonPlayerData.RPGDungeonItem getInput() {
    return this.input;
  }
  
  public RPGDungeonPlayerData.RPGDungeonItem getOutput() {
    return this.output;
  }
  
  public void readFromNBT(NBTTagCompound nbt) {
    NBTTagCompound nbtInput = nbt.func_74775_l("Input");
    UUID uniqueId = UUIDUtils.from(nbtInput.func_74779_i("uniqueId"));
    String type = nbtInput.func_74779_i("type");
    ItemStack item = ItemStack.func_77949_a(nbtInput.func_74775_l("item"));
    RPGItemRarity rarity = RPGItemRarity.values()[nbtInput.func_74762_e("rarity")];
    this.input = new RPGDungeonPlayerData.RPGDungeonItem(uniqueId, type, item, rarity);
    NBTTagCompound nbtOutput = nbt.func_74775_l("Output");
    UUID uniqueIdOut = UUIDUtils.from(nbtOutput.func_74779_i("uniqueId"));
    String typeOut = nbtOutput.func_74779_i("type");
    ItemStack itemOut = ItemStack.func_77949_a(nbtOutput.func_74775_l("item"));
    RPGItemRarity rarityOut = RPGItemRarity.values()[nbtOutput.func_74762_e("rarity")];
    this.output = new RPGDungeonPlayerData.RPGDungeonItem(uniqueIdOut, typeOut, itemOut, rarityOut);
  }
  
  public void writeToNBT(NBTTagCompound nbt) {
    NBTTagCompound nbtInput = new NBTTagCompound();
    nbtInput.func_74778_a("uniqueId", UUIDUtils.toString(this.input.getUniqueId()));
    nbtInput.func_74778_a("type", this.input.getType());
    nbtInput.func_74782_a("item", (NBTBase)this.input.getItem().func_77955_b(new NBTTagCompound()));
    nbtInput.func_74768_a("rarity", this.input.getRarity().ordinal());
    nbt.func_74782_a("Input", (NBTBase)nbtInput);
    NBTTagCompound nbtOutput = new NBTTagCompound();
    nbtOutput.func_74778_a("uniqueId", UUIDUtils.toString(this.output.getUniqueId()));
    nbtOutput.func_74778_a("type", this.output.getType());
    nbtOutput.func_74782_a("item", (NBTBase)this.output.getItem().func_77955_b(new NBTTagCompound()));
    nbtOutput.func_74768_a("rarity", this.output.getRarity().ordinal());
    nbt.func_74782_a("Output", (NBTBase)nbtOutput);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\entity\room\merchant\EntityDungeonMerchant$EntityDungeonMerchantMammonTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
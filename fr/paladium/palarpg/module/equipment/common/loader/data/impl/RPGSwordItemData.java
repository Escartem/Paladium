package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import com.google.gson.annotations.SerializedName;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class RPGSwordItemData extends RPGBasicItemData {
  private int durability;
  
  private Map<RPGStatApplyType, List<ItemStatMutator>> mutators;
  
  private String set;
  
  public void setDurability(int durability) {
    this.durability = durability;
  }
  
  public void setMutators(Map<RPGStatApplyType, List<ItemStatMutator>> mutators) {
    this.mutators = mutators;
  }
  
  public void setSet(String set) {
    this.set = set;
  }
  
  public void setRuneSlot(int runeSlot) {
    this.runeSlot = runeSlot;
  }
  
  public void setRepairItems(List<ItemStack> repairItems) {
    this.repairItems = repairItems;
  }
  
  public int getDurability() {
    return this.durability;
  }
  
  public Map<RPGStatApplyType, List<ItemStatMutator>> getMutators() {
    return this.mutators;
  }
  
  public String getSet() {
    return this.set;
  }
  
  private int runeSlot = 0;
  
  public int getRuneSlot() {
    return this.runeSlot;
  }
  
  @SerializedName("repairItems")
  private final List<String> repairItemsName = new ArrayList<>();
  
  public List<String> getRepairItemsName() {
    return this.repairItemsName;
  }
  
  private transient List<ItemStack> repairItems = new ArrayList<>();
  
  public List<ItemStack> getRepairItems() {
    return this.repairItems;
  }
  
  public void onLoad(File dir) {
    super.onLoad(dir);
    this.repairItemsName.forEach(item -> {
          ItemStack stack = ItemUtils.parse(item);
          if (stack != null)
            this.repairItems.add(stack); 
        });
    this.mutators.forEach((applyType, mutators) -> mutators.forEach(()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGSwordItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
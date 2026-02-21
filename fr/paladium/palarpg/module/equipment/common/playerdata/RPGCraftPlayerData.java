package fr.paladium.palarpg.module.equipment.common.playerdata;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palarpg.common.extended.playerdata.APlayerData;
import fr.paladium.palarpg.common.extended.playerdata.PlayerData;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

@PlayerData("craft")
public class RPGCraftPlayerData extends APlayerData {
  public static final String ID = "craft";
  
  public String toString() {
    return "RPGCraftPlayerData(unlockedCraft=" + getUnlockedCraft() + ")";
  }
  
  private final Set<String> unlockedCraft = new HashSet<>();
  
  public Set<String> getUnlockedCraft() {
    return this.unlockedCraft;
  }
  
  public void read(NBTTagCompound nbt, boolean saving) {
    NBTTagList list = nbt.func_150295_c("unlockedCraft", 8);
    for (int i = 0; i < list.func_74745_c(); i++) {
      String item = list.func_150307_f(i);
      this.unlockedCraft.add(item);
    } 
  }
  
  public void write(NBTTagCompound nbt, boolean saving) {
    NBTTagList unlockedCraftList = new NBTTagList();
    this.unlockedCraft.forEach(item -> unlockedCraftList.func_74742_a((NBTBase)new NBTTagString(item)));
    nbt.func_74782_a("unlockedCraft", (NBTBase)unlockedCraftList);
  }
  
  public void unlockCraft(String stringStack) {
    this.unlockedCraft.add(stringStack);
  }
  
  public void unlockCraft(ItemStack stack) {
    String stringStack = ItemStackSerializer.write64(stack);
    this.unlockedCraft.add(stringStack);
  }
  
  public boolean isCraftUnlocked(String stringStack) {
    ItemStack stack = ItemUtils.parse(stringStack);
    if (stack == null)
      return false; 
    return isCraftUnlocked(stack);
  }
  
  public boolean isCraftUnlocked(ItemStack stack) {
    return this.unlockedCraft.stream().filter(item -> {
          ItemStack itemStack = ItemUtils.parse(item);
          return itemStack.func_77969_a(stack);
        }).findAny().isPresent();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\playerdata\RPGCraftPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
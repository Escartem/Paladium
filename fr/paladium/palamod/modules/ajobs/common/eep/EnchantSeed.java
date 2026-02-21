package fr.paladium.palamod.modules.ajobs.common.eep;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EnchantSeed {
  public ItemStack itemStack;
  
  public int enchantPower;
  
  public int enchantLvl;
  
  public ArrayList<EnchantData> enchantDataList = new ArrayList<>();
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    NBTTagCompound itemCompound = new NBTTagCompound();
    itemCompound = this.itemStack.func_77955_b(itemCompound);
    compound.func_74782_a("itemStack", (NBTBase)itemCompound);
    compound.func_74768_a("enchantPower", this.enchantPower);
    compound.func_74768_a("enchantLvl", this.enchantLvl);
    NBTTagList enchantDataNbtList = new NBTTagList();
    for (EnchantData enchantData : this.enchantDataList)
      enchantDataNbtList.func_74742_a((NBTBase)enchantData.writeToNBT()); 
    compound.func_74782_a("enchantDataList", (NBTBase)enchantDataNbtList);
    return compound;
  }
  
  public void readToNBT(NBTTagCompound compound) {
    this.itemStack = ItemStack.func_77949_a(compound.func_74775_l("itemStack"));
    this.enchantPower = compound.func_74762_e("enchantPower");
    this.enchantLvl = compound.func_74762_e("enchantLvl");
    NBTTagList enchantDataNbtList = compound.func_150295_c("enchantDataList", 10);
    this.enchantDataList.clear();
    for (int i = 0; i < enchantDataNbtList.func_74745_c(); i++) {
      NBTTagCompound enchantDataListCompound = enchantDataNbtList.func_150305_b(i);
      EnchantData enchantData = new EnchantData();
      enchantData.readToNBT(enchantDataListCompound);
      this.enchantDataList.add(enchantData);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\eep\EnchantSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
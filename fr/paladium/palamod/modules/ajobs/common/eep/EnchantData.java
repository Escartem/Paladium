package fr.paladium.palamod.modules.ajobs.common.eep;

import net.minecraft.nbt.NBTTagCompound;

public class EnchantData {
  public int enchantId;
  
  public int enchantStrenght;
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74768_a("enchantId", this.enchantId);
    compound.func_74768_a("enchantStrenght", this.enchantStrenght);
    return compound;
  }
  
  public void readToNBT(NBTTagCompound compound) {
    this.enchantId = compound.func_74762_e("enchantId");
    this.enchantStrenght = compound.func_74762_e("enchantStrenght");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\eep\EnchantData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
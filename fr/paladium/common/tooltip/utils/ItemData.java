package fr.paladium.common.tooltip.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemData {
  private final Item item;
  
  private final int meta;
  
  private final boolean damageable;
  
  private final NBTTagCompound nbt;
  
  public ItemData(ItemStack item) {
    this.item = item.func_77973_b();
    this.meta = item.func_77960_j();
    this.damageable = item.func_77984_f();
    this.nbt = item.func_77978_p();
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.damageable ? 1231 : 1237);
    result = 31 * result + ((this.item == null) ? 0 : this.item.hashCode());
    result = 31 * result + this.meta;
    result = 31 * result + ((this.nbt == null) ? 0 : this.nbt.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    ItemData other = (ItemData)obj;
    if (this.damageable != other.damageable)
      return false; 
    if (this.item == null) {
      if (other.item != null)
        return false; 
    } else if (!this.item.equals(other.item)) {
      return false;
    } 
    if (this.meta != other.meta)
      return false; 
    if (this.nbt == null) {
      if (other.nbt != null)
        return false; 
    } else if (!this.nbt.equals(other.nbt)) {
      return false;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\toolti\\utils\ItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
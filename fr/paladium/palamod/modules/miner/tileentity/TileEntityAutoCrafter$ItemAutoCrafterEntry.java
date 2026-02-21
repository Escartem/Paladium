package fr.paladium.palamod.modules.miner.tileentity;

import java.util.Objects;
import net.minecraft.item.ItemStack;

public class ItemAutoCrafterEntry {
  private final ItemStack item;
  
  public ItemAutoCrafterEntry(ItemStack item) {
    this.item = item;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.item.func_77973_b(), Integer.valueOf(this.item.func_77960_j()) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof ItemAutoCrafterEntry))
      return false; 
    ItemAutoCrafterEntry other = (ItemAutoCrafterEntry)obj;
    if (this.item == null || other.item == null)
      return false; 
    return this.item.func_77969_a(other.item);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\tileentity\TileEntityAutoCrafter$ItemAutoCrafterEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
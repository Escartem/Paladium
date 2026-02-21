package fr.paladium.pet.server.skill.data;

import java.util.Objects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMeta {
  public final Item item;
  
  public final int metadata;
  
  public Item getItem() {
    return this.item;
  }
  
  public int getMetadata() {
    return this.metadata;
  }
  
  public ItemMeta(Item item, int metadata) {
    this.item = item;
    this.metadata = metadata;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o != null && getClass() == o.getClass()) {
      ItemMeta itemMeta = (ItemMeta)o;
      return (this.metadata == itemMeta.metadata && this.item.equals(itemMeta.item));
    } 
    return false;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.item, Integer.valueOf(this.metadata) });
  }
  
  public static ItemMeta of(ItemStack stack) {
    return new ItemMeta(stack.func_77973_b(), stack.func_77960_j());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\data\ItemMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
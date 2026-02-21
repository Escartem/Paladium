package fr.paladium.palarpg.module.equipment.common.loader.util;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUtils {
  public static boolean isEquals(ItemStack item1, ItemStack item2) {
    return isEquals(item1, item2, false);
  }
  
  public static boolean isEquals(ItemStack item1, ItemStack item2, boolean damage) {
    return isEquals(item1, item2, damage, false);
  }
  
  public static boolean isEquals(ItemStack item1, ItemStack item2, boolean damage, boolean tag) {
    if (item1 == null && item2 == null)
      return true; 
    if ((item1 == null && item2 != null) || (item1 != null && item2 == null))
      return false; 
    if (item1.func_77973_b() == null && item2.func_77973_b() == null)
      return true; 
    if ((item1.func_77973_b() == null && item2.func_77973_b() != null) || (item1.func_77973_b() != null && item2.func_77973_b() == null))
      return false; 
    boolean equal = (item1.func_77973_b() == item2.func_77973_b());
    if (damage)
      equal = (equal && item1.func_77960_j() == item2.func_77960_j()); 
    if (tag)
      if (item1.func_77942_o() && item2.func_77942_o()) {
        equal = (equal && item1.func_77978_p().equals(item2.func_77978_p()));
      } else {
        equal = (equal && !item1.func_77942_o() && !item2.func_77942_o());
      }  
    return equal;
  }
  
  public static ItemStack parse(@NonNull String item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    String[] elements = item.split(" ");
    if (elements.length < 1)
      return null; 
    try {
      String[] split = elements[0].split(":");
      int id = Integer.parseInt(split[0]);
      int damage = (split.length > 1) ? Integer.parseInt(split[1]) : 0;
      int stackSize = (elements.length > 2) ? Integer.parseInt(elements[2]) : 1;
      Item itemById = Item.func_150899_d(id);
      if (itemById == null)
        return null; 
      return new ItemStack(itemById, stackSize, damage);
    } catch (Exception exception) {
      try {
        ItemStack itemStack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(item), StandardCharsets.UTF_8), null);
        if (itemStack != null)
          return itemStack; 
      } catch (Exception exception1) {}
      String name = elements[0].contains(":") ? elements[0] : ("minecraft:" + elements[0]);
      int damage = (elements.length > 1) ? Integer.parseInt(elements[1]) : 0;
      int stackSize = (elements.length > 2) ? Integer.parseInt(elements[2]) : 1;
      ItemStack stack = GameRegistry.findItemStack(name.split(":")[0], name.split(":")[1], stackSize);
      if (stack != null) {
        stack.func_77964_b(damage);
        stack.field_77994_a = stackSize;
      } 
      return stack;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loade\\util\ItemUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
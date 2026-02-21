package fr.paladium.palajobs.core.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackSerializer {
  public static String writeInventory(ItemStack[] inventory) {
    StringBuilder stringBuilder = new StringBuilder();
    int n = 0;
    while (n < inventory.length) {
      if (n > 0)
        stringBuilder.append(";"); 
      if (inventory[n] != null && inventory[n].func_77973_b() != Item.func_150898_a(Blocks.field_150350_a))
        stringBuilder.append(write(inventory[n])); 
      n++;
    } 
    return stringBuilder.toString();
  }
  
  public static ItemStack[] readInventory(String inventory) {
    if (inventory == null)
      return null; 
    String[] arrstring = inventory.split(";");
    ItemStack[] arritemStack = new ItemStack[arrstring.length];
    int n = 0;
    while (n < arrstring.length) {
      if (!arrstring[n].equals(""))
        arritemStack[n] = read(arrstring[n]); 
      n++;
    } 
    return arritemStack;
  }
  
  public static String writeInventoryFromList(List<ItemStack> inventory) {
    StringBuilder stringBuilder = new StringBuilder();
    int n = 0;
    for (ItemStack item : inventory) {
      if (n > 0)
        stringBuilder.append(";"); 
      if (item != null && item.func_77973_b() != Item.func_150898_a(Blocks.field_150350_a))
        stringBuilder.append(write(item)); 
      n++;
    } 
    return stringBuilder.toString();
  }
  
  public static List<ItemStack> readInventoryToList(String inventory) {
    if (inventory == null)
      return new ArrayList<>(); 
    String[] arrstring = inventory.split(";");
    ArrayList<ItemStack> arritemStack = new ArrayList<>(arrstring.length);
    int n = 0;
    while (n < arrstring.length) {
      if (!arrstring[n].equals(""))
        arritemStack.add(read(arrstring[n])); 
      n++;
    } 
    return arritemStack;
  }
  
  public static String write(ItemStack value) {
    String ret = (value == null) ? "empty" : (value.field_77994_a + ":" + (value.func_77973_b()).delegate.name() + ":" + value.func_77960_j() + "#" + ((value.func_77978_p() != null) ? value.func_77978_p().toString() : ""));
    return ret;
  }
  
  public static ItemStack read(String in) {
    String data = "ERROR";
    try {
      if (in == null)
        throw new Exception("in is null"); 
      if (in.isEmpty())
        throw new Exception("in is empty"); 
      data = in;
      if (data.equals("empty"))
        return null; 
      String[] parts = data.substring(0, data.indexOf('#')).split(":");
      ItemStack stack = GameRegistry.makeItemStack(parts[1] + ":" + parts[2], 
          Integer.parseInt(parts[3]), Integer.parseInt(parts[0]), null);
      stack.field_77994_a = Integer.parseInt(parts[0]);
      try {
        String nbtdata = data.substring(data.indexOf('#') + 1);
        if (!nbtdata.equals("null") && !nbtdata.isEmpty())
          stack.func_77982_d(JsonToNBT.getTagFromJson(nbtdata)); 
      } catch (Exception e) {
        System.err.println("[PalaJobs] Could not read NBT data from " + data + ":" + e.toString());
      } 
      return stack;
    } catch (Exception e) {
      System.err.println("[PalaJobs] Unable to make ItemStack for " + data + ":" + e.toString());
      e.printStackTrace();
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\ItemStackSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
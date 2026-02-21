package fr.paladium.palaforgeutils.lib.inventory;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryUtils {
  public static boolean isNullItem(ItemStack item) {
    if (item != null && item.func_77973_b() != Item.func_150898_a(Blocks.field_150350_a))
      return false; 
    return true;
  }
  
  public static void removeItems(EntityPlayer p, ItemStack targetedIs, int toRemove) {
    removeItems(p, targetedIs, toRemove, true);
  }
  
  public static void removeItems(EntityPlayer p, ItemStack targetedIs, int toRemove, boolean tag) {
    int a = toRemove;
    for (int i = 0; i < p.field_71071_by.field_70462_a.length; i++) {
      ItemStack it = p.field_71071_by.field_70462_a[i];
      if (it != null && a > 0 && 
        it.func_77969_a(targetedIs) && it.func_77960_j() == targetedIs.func_77960_j() && (
        !tag || ItemStack.func_77970_a(it, targetedIs))) {
        int v = Math.min(Math.min(64, a), it.field_77994_a);
        it.field_77994_a -= v;
        if (it.field_77994_a <= 0) {
          p.field_71071_by.func_70299_a(i, null);
          p.field_71071_by.field_70459_e = true;
        } else {
          p.field_71071_by.func_70299_a(i, it.func_77946_l());
          p.field_71071_by.field_70459_e = true;
        } 
        a -= v;
      } 
    } 
  }
  
  public static void addItem(EntityPlayer player, ItemStack item) {
    addItem(player, item, 1);
  }
  
  public static void addItem(EntityPlayer player, ItemStack item, int quantity) {
    InventoryPlayer inventory = player.field_71071_by;
    ItemStack current = item.func_77946_l();
    int max = current.func_77976_d();
    if (quantity > max) {
      int leftOver = quantity;
      while (leftOver > 0) {
        int add = 0;
        if (leftOver <= max) {
          add += leftOver;
        } else {
          add += max;
        } 
        current = current.func_77946_l();
        current.field_77994_a += add;
        inventory.func_70441_a(current);
        leftOver -= add;
      } 
    } else {
      current = current.func_77946_l();
      current.field_77994_a = quantity;
      inventory.func_70441_a(current);
    } 
  }
  
  public static boolean haveRequiredItem(EntityPlayer player, ItemStack item) {
    return haveRequiredItem(player, item, 1);
  }
  
  public static boolean haveRequiredItem(EntityPlayer player, ItemStack item, int quantity) {
    return haveRequiredItem(player, item, quantity, true);
  }
  
  public static boolean haveRequiredItem(EntityPlayer player, ItemStack item, int quantity, boolean tag) {
    int quantityInInventory = getItemCount(player, item, tag);
    if (quantityInInventory >= quantity)
      return true; 
    return false;
  }
  
  public static boolean isFullInventory(EntityPlayer player) {
    InventoryPlayer inventory = player.field_71071_by;
    ItemStack[] arrayOfItemStack;
    int j = (arrayOfItemStack = inventory.field_70462_a).length;
    for (int i = 0; i < j; i++) {
      ItemStack current = arrayOfItemStack[i];
      if (isNullItem(current))
        return false; 
    } 
    return true;
  }
  
  public static boolean hasSpaceInventory(EntityPlayer player, ItemStack item, int count) {
    int left = count;
    ItemStack[] items = player.field_71071_by.field_70462_a;
    for (int i = 0; i < items.length; i++) {
      ItemStack stack = items[i];
      if (stack == null || stack.func_77973_b() == Item.func_150898_a(Blocks.field_150350_a)) {
        left -= item.func_77976_d();
      } else if (stack.func_77973_b() == item.func_77973_b() && 
        ItemStack.func_77970_a(stack, item) && item
        .func_77976_d() > 1 && stack.field_77994_a < item
        .func_77976_d()) {
        left -= stack.func_77976_d() - stack.field_77994_a;
      } 
      if (left <= 0)
        break; 
    } 
    return (left <= 0);
  }
  
  public static int getItemCount(EntityPlayer player, ItemStack item) {
    return getItemCount(player, item, true);
  }
  
  public static int getItemCount(EntityPlayer player, ItemStack item, boolean tag) {
    int quantityInInventory = 0;
    InventoryPlayer inventory = player.field_71071_by;
    ItemStack[] arrayOfItemStack;
    int j = (arrayOfItemStack = inventory.field_70462_a).length;
    for (int i = 0; i < j; i++) {
      ItemStack current = arrayOfItemStack[i];
      if (!isNullItem(current) && current.func_77973_b() == item.func_77973_b() && current.func_77960_j() == item.func_77960_j() && (
        !tag || ItemStack.func_77970_a(current, item)))
        quantityInInventory += current.field_77994_a; 
    } 
    return quantityInInventory;
  }
  
  public static void decrementCurrentItem(EntityPlayer player, ItemStack item, int quantity) {
    int currentAmount = item.field_77994_a;
    if (currentAmount <= 1) {
      player.func_70062_b(0, null);
    } else {
      int amount = currentAmount - quantity;
      item.field_77994_a = amount;
    } 
  }
  
  public static List<String> getEnchants(ItemStack itemStack) {
    List<String> enchants = new ArrayList<>();
    NBTTagList taglist = getEnchantmentTagList(itemStack);
    int tagscount = 0;
    if (taglist != null)
      tagscount = taglist.func_74745_c(); 
    if (tagscount > 0)
      for (int x = 0; x < taglist.func_74745_c(); x++) {
        short var7 = taglist.func_150305_b(x).func_74765_d("id");
        short var8 = taglist.func_150305_b(x).func_74765_d("lvl");
        if (Enchantment.field_77331_b[var7] != null) {
          String ench = Enchantment.field_77331_b[var7].func_77316_c(var8);
          enchants.add(ench);
        } 
      }  
    return enchants;
  }
  
  public static NBTTagList getEnchantmentTagList(ItemStack stack) {
    return (stack.field_77990_d != null && stack.field_77990_d.func_74764_b("ench")) ? (NBTTagList)stack.field_77990_d.func_74781_a("ench") : new NBTTagList();
  }
  
  public static List<String> getLore(ItemStack itemStack) {
    List<String> lore = new ArrayList<>();
    if (itemStack != null && itemStack.func_77942_o()) {
      NBTTagCompound nbt = itemStack.func_77978_p();
      if (nbt != null) {
        NBTTagCompound display = nbt.func_74775_l("display");
        if (display != null) {
          NBTTagList loreNbt = display.func_150295_c("Lore", 8);
          for (int i1 = 0; i1 < loreNbt.func_74745_c(); i1++) {
            String str = loreNbt.func_150307_f(i1);
            lore.add(str);
          } 
        } 
      } 
    } 
    return lore;
  }
  
  public static void giveOrDropitems(EntityPlayer player, ItemStack stack) {
    if (stack == null)
      return; 
    if (!player.field_71071_by.func_70441_a(stack) && 
      !(player.func_130014_f_()).field_72995_K)
      ItemUtils.spawnItemInWorld(player.func_130014_f_(), player.field_70165_t, player.field_70163_u, player.field_70161_v, stack); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\inventory\InventoryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
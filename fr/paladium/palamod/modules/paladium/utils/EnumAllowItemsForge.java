package fr.paladium.palamod.modules.paladium.utils;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum EnumAllowItemsForge {
  IRON_HELMET((Item)Items.field_151028_Y, Items.field_151042_j, 4),
  IRON_CHEST((Item)Items.field_151030_Z, Items.field_151042_j, 7),
  IRON_LEGGINGS((Item)Items.field_151165_aa, Items.field_151042_j, 6),
  IRON_BOOTS((Item)Items.field_151167_ab, Items.field_151042_j, 3),
  IRON_SWORD(Items.field_151040_l, Items.field_151042_j, 2),
  IRON_AXE(Items.field_151036_c, Items.field_151042_j, 2),
  IRON_PICKAXE(Items.field_151035_b, Items.field_151042_j, 2),
  IRON_SHOVEL(Items.field_151037_a, Items.field_151042_j, 1),
  GOLDEN_HELMET((Item)Items.field_151169_ag, Items.field_151043_k, 4),
  GOLDEN_CHEST((Item)Items.field_151171_ah, Items.field_151043_k, 7),
  GOLDEN_LEGGINGS((Item)Items.field_151149_ai, Items.field_151043_k, 6),
  GOLDEN_BOOTS((Item)Items.field_151151_aj, Items.field_151043_k, 3),
  GOLDEN_SWORD(Items.field_151010_B, Items.field_151043_k, 2),
  GOLDEN_AXE(Items.field_151006_E, Items.field_151043_k, 2),
  GOLDEN_PICKAXE(Items.field_151005_D, Items.field_151043_k, 2),
  GOLDEN_SHOVEL(Items.field_151011_C, Items.field_151043_k, 1),
  TITANE_HELMET(ItemsRegister.TITANE_HELMET, ItemsRegister.TITANE_INGOT, 5),
  TITANE_CHEST(ItemsRegister.TITANE_CHESTPLATE, ItemsRegister.TITANE_INGOT, 8),
  TITANE_LEGGINGS(ItemsRegister.TITANE_LEGGINGS, ItemsRegister.TITANE_INGOT, 7),
  TITANE_BOOTS(ItemsRegister.TITANE_BOOTS, ItemsRegister.TITANE_INGOT, 4),
  TITANE_AXE(ItemsRegister.TITANE_AXE, ItemsRegister.TITANE_INGOT, 3),
  TITANE_SHOVEL(ItemsRegister.TITANE_SHOVEL, ItemsRegister.TITANE_INGOT, 1),
  TITANE_PICKAXE(ItemsRegister.TITANE_PICKAXE, ItemsRegister.TITANE_INGOT, 3),
  TITANE_SWORD(ItemsRegister.TITANE_SWORD, ItemsRegister.TITANE_INGOT, 2),
  AMETHYST_HELMET(ItemsRegister.AMETHYST_HELMET, ItemsRegister.AMETHYST_INGOT, 4),
  AMETHYST_CHEST(ItemsRegister.AMETHYST_CHESTPLATE, ItemsRegister.AMETHYST_INGOT, 7),
  AMETHYST_LEGGINGS(ItemsRegister.AMETHYST_LEGGINGS, ItemsRegister.AMETHYST_INGOT, 6),
  AMETHYST_BOOTS(ItemsRegister.AMETHYST_BOOTS, ItemsRegister.AMETHYST_INGOT, 3),
  AMETHYST_AXE(ItemsRegister.AMETHYST_AXE, ItemsRegister.AMETHYST_INGOT, 3),
  AMETHYST_SHOVEL(ItemsRegister.AMETHYST_SHOVEL, ItemsRegister.AMETHYST_INGOT, 1),
  AMETHYST_PICKAXE(ItemsRegister.AMETHYST_PICKAXE, ItemsRegister.AMETHYST_INGOT, 3),
  AMETHYST_SWORD(ItemsRegister.AMETHYST_SWORD, ItemsRegister.AMETHYST_INGOT, 2),
  PALADIUM_HELMET(ItemsRegister.PALADIUM_HELMET, ItemsRegister.PALADIUM_INGOT, 4),
  PALADIUM_CHEST(ItemsRegister.PALADIUM_CHESTPLATE, ItemsRegister.PALADIUM_INGOT, 7),
  PALADIUM_LEGGINGS(ItemsRegister.PALADIUM_LEGGINGS, ItemsRegister.PALADIUM_INGOT, 6),
  PALADIUM_BOOTS(ItemsRegister.PALADIUM_BOOTS, ItemsRegister.PALADIUM_INGOT, 3),
  PALADIUM_AXE(ItemsRegister.PALADIUM_AXE, ItemsRegister.PALADIUM_INGOT, 2),
  PALADIUM_SHOVEL(ItemsRegister.PALADIUM_SHOVEL, ItemsRegister.PALADIUM_INGOT, 1),
  PALADIUM_PICKAXE(ItemsRegister.PALADIUM_PICKAXE, ItemsRegister.PALADIUM_INGOT, 2),
  PALADIUM_SWORD(ItemsRegister.PALADIUM_SWORD, ItemsRegister.PALADIUM_INGOT, 2),
  ENDIUM_HELMET(ItemsRegister.ENDIUM_HELMET, ItemsRegister.ENDIUM_INGOT, 4),
  ENDIUM_CHEST(ItemsRegister.ENDIUM_CHESTPLATE, ItemsRegister.ENDIUM_INGOT, 7),
  ENDIUM_LEGGINGS(ItemsRegister.ENDIUM_LEGGINGS, ItemsRegister.ENDIUM_INGOT, 6),
  ENDIUM_BOOTS(ItemsRegister.ENDIUM_BOOTS, ItemsRegister.ENDIUM_INGOT, 3),
  ENDIUM_AXE(ItemsRegister.ENDIUM_AXE, ItemsRegister.ENDIUM_INGOT, 2),
  ENDIUM_PICKAXE(ItemsRegister.ENDIUM_PICKAXE, ItemsRegister.ENDIUM_INGOT, 2),
  ENDIUM_SWORD(ItemsRegister.ENDIUM_SWORD, ItemsRegister.ENDIUM_INGOT, 2);
  
  private Item itemAllowed = null;
  
  private Item itemDrop = null;
  
  private int maxDrop = 0;
  
  EnumAllowItemsForge(Item itemAllowed, Item itemDrop, int maxDrop) {
    this.itemAllowed = itemAllowed;
    this.itemDrop = itemDrop;
    this.maxDrop = maxDrop;
  }
  
  public Item getItemAllowed() {
    return this.itemAllowed;
  }
  
  public Item getItemDrop() {
    return this.itemDrop;
  }
  
  public int getMaxDrop() {
    return this.maxDrop;
  }
  
  public static Boolean containItem(Item item) {
    for (EnumAllowItemsForge enumAllowItemsForge : values()) {
      if (enumAllowItemsForge.getItemAllowed().equals(item))
        return Boolean.valueOf(true); 
    } 
    return Boolean.valueOf(false);
  }
  
  public static EnumAllowItemsForge valuesOf(Item itemAllowed) {
    for (EnumAllowItemsForge enumAllowItemsForge : values()) {
      if (enumAllowItemsForge.getItemAllowed().equals(itemAllowed))
        return enumAllowItemsForge; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\EnumAllowItemsForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
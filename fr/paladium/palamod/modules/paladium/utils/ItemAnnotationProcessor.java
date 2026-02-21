package fr.paladium.palamod.modules.paladium.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.items.InvalidItem;
import fr.paladium.palamod.modules.paladium.common.items.ItemBaseAxe;
import fr.paladium.palamod.modules.paladium.common.items.ItemBasePickaxe;
import fr.paladium.palamod.modules.paladium.common.items.ItemBaseShovel;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.lang.reflect.Field;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemAnnotationProcessor {
  public static void registerItems(Class<?> clazz) {
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent((Class)Item.class)) {
        Item item1;
        ItemSword itemSword;
        ItemBasePickaxe itemBasePickaxe;
        ItemBaseAxe itemBaseAxe;
        ItemBaseShovel itemBaseShovel;
        Item item;
        Item annotation = field.<Item>getAnnotation(Item.class);
        switch (annotation.type()) {
          case "item":
            item1 = annotation.valid() ? new Item() : (Item)new InvalidItem();
            break;
          case "sword":
            itemSword = new ItemSword(PToolMaterial.paladium);
            break;
          case "pickaxe":
            itemBasePickaxe = new ItemBasePickaxe(PToolMaterial.paladium);
            break;
          case "axe":
            itemBaseAxe = new ItemBaseAxe(PToolMaterial.paladium);
            break;
          case "shovel":
            itemBaseShovel = new ItemBaseShovel(PToolMaterial.paladium);
            break;
          default:
            item = new Item();
            item.func_77625_d(annotation.stackSize());
            break;
        } 
        switch (annotation.registry()) {
          case "paladium":
            item.func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
            break;
          default:
            item.func_77637_a(CreativeTabs.field_78027_g);
            break;
        } 
        item.func_77655_b(annotation.name())
          .func_111206_d("palamod:" + (annotation.texture().isEmpty() ? annotation.name() : annotation.texture()));
        try {
          field.set(clazz, item);
        } catch (IllegalArgumentException|IllegalAccessException e) {
          e.printStackTrace();
        } 
        GameRegistry.registerItem(item, item.func_77658_a());
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\ItemAnnotationProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palajobs.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palajobs.core.item.ItemBambooBoat;
import fr.paladium.palajobs.core.item.ItemCustomFishingRod;
import fr.paladium.palajobs.core.item.ItemEndiumFishingRod;
import fr.paladium.palajobs.core.item.ItemFish;
import fr.paladium.palajobs.core.item.ItemRadiusHoe;
import fr.paladium.palajobs.core.tab.JobsTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemsRegistry {
  public static Item ROOT_SEEDS_ITEM;
  
  public static Item BAMBOO_BOAT;
  
  public static Item AMETHYST_RADIUS_HOE;
  
  public static Item TITANE_RADIUS_HOE;
  
  public static Item PALADIUM_RADIUS_HOE;
  
  public static Item GREEN_PALADIUM_RADIUS_HOE;
  
  public static Item ENDIUM_RADIUS_HOE;
  
  public static Item PALADIUM_FISHING_ROD;
  
  public static Item ENDIUM_FISHING_ROD;
  
  public static Item CARP_FISH;
  
  public static Item BASS_FISH;
  
  public static Item RAY_FISH;
  
  public static Item EXP_FISH;
  
  public static Item RED_TUNA_FISH;
  
  public static Item MOON_FISH;
  
  public static Item WHALE_FISH;
  
  public static Item KRAKEN_FISH;
  
  private static void registerItem(Item item) {
    GameRegistry.registerItem(item, item.func_77658_a());
  }
  
  public static void registerItems() {
    registerItem(BAMBOO_BOAT = (new ItemBambooBoat("bamboo_boat")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(AMETHYST_RADIUS_HOE = (new ItemRadiusHoe(1999, 2, "amethyst_radius_hoe")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(TITANE_RADIUS_HOE = (new ItemRadiusHoe(2999, 4, "titane_radius_hoe")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(PALADIUM_RADIUS_HOE = (new ItemRadiusHoe(4999, 6, "paladium_radius_hoe")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(GREEN_PALADIUM_RADIUS_HOE = (new ItemRadiusHoe(4999, 8, "green_paladium_radius_hoe")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(ENDIUM_RADIUS_HOE = (new ItemRadiusHoe(4999, 12, "endium_radius_hoe")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(PALADIUM_FISHING_ROD = (new ItemCustomFishingRod("paladium_rod")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(ENDIUM_FISHING_ROD = (new ItemEndiumFishingRod("endium_rod")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(CARP_FISH = (Item)new ItemFish(6, 8.0F, "carp", ItemFish.FishRarity.RARE));
    registerItem(BASS_FISH = (Item)new ItemFish(6, 8.0F, "bass", ItemFish.FishRarity.RARE));
    registerItem(RAY_FISH = (Item)new ItemFish(8, 10.0F, "ray", ItemFish.FishRarity.LEGENDARY));
    registerItem(EXP_FISH = (Item)new ItemFish(2, 4.0F, "exp_fish", ItemFish.FishRarity.LEGENDARY));
    registerItem(RED_TUNA_FISH = (Item)new ItemFish(12, 14.0F, "red_tuna", ItemFish.FishRarity.LEGENDARY));
    registerItem(MOON_FISH = (Item)new ItemFish(14, 16.0F, "moonfish", ItemFish.FishRarity.LEGENDARY));
    registerItem(WHALE_FISH = (Item)new ItemFish(20, 22.0F, "whale", ItemFish.FishRarity.MYTHIC));
    registerItem(KRAKEN_FISH = (Item)new ItemFish(20, 22.0F, "kraken", ItemFish.FishRarity.MYTHIC));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\registry\ItemsRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
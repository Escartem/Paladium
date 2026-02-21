package fr.paladium.palamod.modules.ajobs.common.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.tab.JobsTab;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.items.ItemJobBottle;
import fr.paladium.palamod.modules.ajobs.common.items.ItemRootSeeds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemsRegistry {
  public static Item ROOT_SEEDS_ITEM;
  
  private static void registerItem(Item item) {
    GameRegistry.registerItem(item, item.func_77658_a());
  }
  
  public static void registerItems() {
    registerItem(ROOT_SEEDS_ITEM = (new ItemRootSeeds("root_item")).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(ItemsRegister.JOB_BOTTLE_MINER = (new ItemJobBottle(JobType.MINER)).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(ItemsRegister.JOB_BOTTLE_HUNTER = (new ItemJobBottle(JobType.HUNTER)).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(ItemsRegister.JOB_BOTTLE_FARMER = (new ItemJobBottle(JobType.FARMER)).func_77637_a((CreativeTabs)JobsTab.getInstance()));
    registerItem(ItemsRegister.JOB_BOTTLE_ALCHEMIST = (new ItemJobBottle(JobType.ALCHEMIST)).func_77637_a((CreativeTabs)JobsTab.getInstance()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\ItemsRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
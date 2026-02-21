package fr.paladium.palamod.modules.egghunt.utils;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.egghunt.common.block.BlockCheckEgg;
import fr.paladium.palamod.modules.egghunt.common.block.BlockEgg;
import fr.paladium.palamod.modules.egghunt.common.block.TileCheckEgg;
import fr.paladium.palamod.modules.egghunt.common.entity.EntityCustomDragon;
import fr.paladium.palamod.modules.egghunt.common.item.ItemDragonRoll;
import fr.paladium.palamod.modules.egghunt.common.item.ItemEgg;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class PERegisterer {
  public static Block egg = (new BlockEgg()).func_149722_s().func_149663_c("dragonEgg")
    .func_149658_d("palamod:dragon_egg/egg");
  
  public static Item dragonScales = (new Item())
    .func_77655_b("dragonScales")
    .func_111206_d("palamod:egghunt/dragon_scale");
  
  public static Item dragonRoll = (new ItemDragonRoll()).func_77655_b("dragonRoll")
    .func_111206_d("palamod:egghunt/dragon_roll");
  
  public static Block checkEgg = (new BlockCheckEgg()).func_149722_s().func_149663_c("checkEgg")
    .func_149658_d("palamod:check_egg");
  
  public static void register() {
    egg.func_149647_a((CreativeTabs)Registry.EGGHUNT_TAB);
    dragonScales.func_77637_a((CreativeTabs)Registry.EGGHUNT_TAB);
    dragonRoll.func_77637_a((CreativeTabs)Registry.EGGHUNT_TAB);
    checkEgg.func_149647_a((CreativeTabs)Registry.EGGHUNT_TAB);
    GameRegistry.registerBlock(egg, ItemEgg.class, "egg");
    GameRegistry.registerItem(dragonScales, "dragonScales");
    GameRegistry.registerItem(dragonRoll, "dragonRoll");
    GameRegistry.registerBlock(checkEgg, "checkEgg");
    EntityRegistry.registerGlobalEntityID(EntityCustomDragon.class, "custom_dragon", 
        EntityRegistry.findGlobalUniqueEntityId(), 65280, 16711935);
    GameRegistry.registerTileEntity(TileCheckEgg.class, "check_egg");
    setupTabs();
    registerRecipes();
  }
  
  public static void registerRecipes() {
    CraftingManager.func_77594_a().func_92103_a(new ItemStack(dragonRoll), new Object[] { "SSS", "SCS", "SSS", Character.valueOf('S'), dragonScales, 
          Character.valueOf('C'), Items.field_151121_aF });
  }
  
  public static void setupTabs() {
    Registry.EGGHUNT_TAB.init(new ItemStack(dragonScales));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghun\\utils\PERegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
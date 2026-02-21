package fr.paladium.palamod.modules.pillage.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.items.ItemArrowBase;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.pillage.common.items.ItemInfusedStone;
import fr.paladium.palamod.modules.pillage.common.items.ItemObsidianPickaxe;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PPItems {
  public static Item GLUE;
  
  public static Item HOUSING_STONE;
  
  public static Item INFUSED_STONE;
  
  public static ItemArrowBase MID_LIFE;
  
  public static Item AGRO_STONE;
  
  public static Item WITHER_SADDLE;
  
  public static Item WITHER_FISHINGROD;
  
  public static Item OBSIDIAN_PICKAXE;
  
  public static void init() {
    HOUSING_STONE = (new Item()).func_77655_b("housing_stone").func_111206_d("palamod:pillage/home_finder/housing_stone").func_77637_a((CreativeTabs)Registry.PILLAGE_TAB).func_77656_e(8);
    INFUSED_STONE = (Item)new ItemInfusedStone("infused_stone");
    OBSIDIAN_PICKAXE = (new ItemObsidianPickaxe(PToolMaterial.paladium, "obsidian_pickaxe", ItemsRegister.PALADIUM_INGOT)).func_77655_b("obsidian_pickaxe").func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    GLUE = (new Item()).func_77655_b("glue").func_111206_d("palamod:pillage/glue").func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    MID_LIFE = (ItemArrowBase)(new ItemArrowBase("mid_life_arrow", "pillage/effects/mid_life_arrow", 0) {
        public int getEffect() {
          return 6;
        }
      }).func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    AGRO_STONE = (new Item()).func_77655_b("agro_stone").func_111206_d("palamod:pillage/effects/agro_stone").func_77656_e(4).func_77625_d(1).func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    WITHER_FISHINGROD = (new Item() {
        @SideOnly(Side.CLIENT)
        public boolean func_77662_d() {
          return true;
        }
        
        @SideOnly(Side.CLIENT)
        public boolean func_77629_n_() {
          return true;
        }
      }).func_77655_b("wither_fishingrod").func_111206_d("palamod:pillage/effects/wither_fishingrod").func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    WITHER_SADDLE = (new Item()).func_77655_b("wither_saddle").func_111206_d("palamod:pillage/effects/wither_saddle").func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    GameRegistry.registerItem(HOUSING_STONE, HOUSING_STONE.func_77658_a());
    GameRegistry.registerItem(INFUSED_STONE, INFUSED_STONE.func_77658_a());
    GameRegistry.registerItem(GLUE, GLUE.func_77658_a());
    GameRegistry.registerItem(OBSIDIAN_PICKAXE, OBSIDIAN_PICKAXE.func_77658_a());
    GameRegistry.registerItem((Item)MID_LIFE, MID_LIFE.func_77658_a());
    GameRegistry.registerItem(AGRO_STONE, AGRO_STONE.func_77658_a());
    GameRegistry.registerItem(WITHER_SADDLE, WITHER_SADDLE.func_77658_a());
    GameRegistry.registerItem(WITHER_FISHINGROD, WITHER_FISHINGROD.func_77658_a());
  }
  
  public static void registerRecipes() {
    GameRegistry.addRecipe(new ItemStack(OBSIDIAN_PICKAXE, 1), new Object[] { "XYX", " Z ", " Z ", 
          
          Character.valueOf('X'), new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM, 1), 
          Character.valueOf('Y'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('Z'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(HOUSING_STONE, 1), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(INFUSED_STONE, 1, 1), 
          Character.valueOf('Y'), ItemsRegister.CHESTEXPLORER });
    GameRegistry.addRecipe(new ItemStack(INFUSED_STONE, 1), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150348_b, 
          Character.valueOf('Y'), BlocksRegister.FLOWER_ENDIUM });
    GameRegistry.addRecipe(new ItemStack(GLUE, 1), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), PPRegisterer.PPBlocks.WATER_LEAVES, 
          Character.valueOf('Y'), Items.field_151123_aH });
    GameRegistry.addRecipe(new ItemStack((Item)MID_LIFE, 1), new Object[] { "XYY", " ZY", "X X", Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('Y'), ItemsRegister.DYNAMITE_BIG, Character.valueOf('Z'), Items.field_151032_g });
    GameRegistry.addRecipe(new ItemStack(AGRO_STONE, 1), new Object[] { "XXX", "YZY", "ZZZ", Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('Y'), ItemsRegister.WITHER_SKULLFRAGMENT, Character.valueOf('Z'), ItemsRegister.SEED_ORANGEBLUE });
    GameRegistry.addRecipe(new ItemStack(WITHER_SADDLE, 1), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), ItemsRegister.WITHER_SKULLFRAGMENT, 
          Character.valueOf('Y'), Items.field_151141_av });
    GameRegistry.addRecipe(new ItemStack(WITHER_FISHINGROD, 1), new Object[] { "   ", " X ", " Z ", Character.valueOf('X'), Items.field_151112_aM, 
          Character.valueOf('Z'), new ItemStack(Items.field_151144_bL, 1) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\registerer\PPRegisterer$PPItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
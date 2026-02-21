package fr.paladium.palamod.modules.pillage.registerer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.paladium.common.items.ItemArrowBase;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.pillage.common.blocks.BlockBase;
import fr.paladium.palamod.modules.pillage.common.blocks.BlockCompressedQuartz;
import fr.paladium.palamod.modules.pillage.common.blocks.BlockCoordinateJammer;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.ObsiEffect;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TNTEffect;
import fr.paladium.palamod.modules.pillage.common.blocks.tree.BirchWaterLeaves;
import fr.paladium.palamod.modules.pillage.common.blocks.tree.BirchWaterLog;
import fr.paladium.palamod.modules.pillage.common.blocks.tree.BirchWaterPlanks;
import fr.paladium.palamod.modules.pillage.common.blocks.tree.BirchWaterSapling;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.AntiAggroObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.BigObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.BoomObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.FakeObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.FrozenObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.KnockbackObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.LavaObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.MegaBoomObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.PoisonObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.RedstoneObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.SlimeObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.SpikeObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi.WitherObsi;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt.BigTNT;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt.EffectTNT;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt.EndiumTNT;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt.SpongeTNT;
import fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt.WitherTNT;
import fr.paladium.palamod.modules.pillage.common.items.ItemBlockMultipleTypes;
import fr.paladium.palamod.modules.pillage.common.items.ItemInfusedStone;
import fr.paladium.palamod.modules.pillage.common.items.ItemObsidianPickaxe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class PPRegisterer {
  public static class PPBlocks {
    public static Block WATER_SAPLING;
    
    public static Block WATER_LEAVES;
    
    public static Block WATER_LOG;
    
    public static Block WATER_PLANKS;
    
    public static Block COMPRESSED_SPONGE;
    
    public static Block EMPOWERED_QUARTZ;
    
    public static Block COMPRESSED_QUARTZ;
    
    public static Block HOME_FINDER;
    
    public static Block COORDINATE_JAMMER;
    
    public static List<TNTEffect> TNTS = new ArrayList<>();
    
    public static List<ObsiEffect> OBSIS = new ArrayList<>();
    
    public static void init() {
      WATER_SAPLING = (Block)new BirchWaterSapling("water_sapling");
      WATER_LEAVES = (Block)new BirchWaterLeaves("water_leaves");
      WATER_LOG = (Block)new BirchWaterLog("water_log");
      WATER_PLANKS = (Block)new BirchWaterPlanks("water_planks");
      COMPRESSED_SPONGE = (Block)new BlockBase(Material.field_151576_e, "compressed_sponge");
      EMPOWERED_QUARTZ = (new BlockBase(Material.field_151576_e, "empowered_quartz")).func_149658_d("palamod:pillage/home_finder/empowered_quartz").func_149711_c(0.8F);
      COMPRESSED_QUARTZ = (Block)new BlockCompressedQuartz("compressed_quartz");
      COORDINATE_JAMMER = (new BlockCoordinateJammer("coordinate_jammer")).func_149711_c(2.0F);
      BlocksRegister.TNT_EFFECT = addEffectBlock((ObjectEffect)new EffectTNT());
      BlocksRegister.TNT_BIG = addEffectBlock((ObjectEffect)new BigTNT());
      BlocksRegister.TNT_ENDIUM = addEffectBlock((ObjectEffect)new EndiumTNT());
      BlocksRegister.TNT_WITHER = addEffectBlock((ObjectEffect)new WitherTNT());
      BlocksRegister.TNT_SPONGE = addEffectBlock((ObjectEffect)new SpongeTNT());
      BlocksRegister.OBSI_BIG = addEffectBlock((ObjectEffect)new BigObsi());
      BlocksRegister.OBSI_LAVA = addEffectBlock((ObjectEffect)new LavaObsi());
      BlocksRegister.OBSI_FAKE = addEffectBlock((ObjectEffect)new FakeObsi());
      BlocksRegister.OBSI_BOOM = addEffectBlock((ObjectEffect)new BoomObsi());
      BlocksRegister.OBSI_MEGABOOM = addEffectBlock((ObjectEffect)new MegaBoomObsi());
      BlocksRegister.OBSI_SPIKE = addEffectBlock((ObjectEffect)new SpikeObsi());
      BlocksRegister.OBSI_SLIME = addEffectBlock((ObjectEffect)new SlimeObsi());
      BlocksRegister.OBSI_POISON = addEffectBlock((ObjectEffect)new PoisonObsi());
      BlocksRegister.OBSI_WITHER = addEffectBlock((ObjectEffect)new WitherObsi());
      BlocksRegister.OBSI_REDSTONE = addEffectBlock((ObjectEffect)new RedstoneObsi());
      BlocksRegister.OBSI_ANTIAGGRO = addEffectBlock((ObjectEffect)new AntiAggroObsi());
      BlocksRegister.OBSI_FROZEN = addEffectBlock((ObjectEffect)new FrozenObsi());
      BlocksRegister.OBSI_KNOCKBACK = addEffectBlock((ObjectEffect)new KnockbackObsi());
    }
    
    public static void registerRecipes() {
      OreDictionary.registerOre("plankWood", new ItemStack(WATER_PLANKS, 1, 32767));
      GameRegistry.addRecipe(new ItemStack(WATER_PLANKS, 4, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(WATER_LOG, 1, 0) });
      GameRegistry.addRecipe(new ItemStack(COMPRESSED_SPONGE, 1), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), ModBlocks.sponge, 
            Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE });
      GameRegistry.addRecipe(new ItemStack(HOME_FINDER, 1), new Object[] { 
            "XYX", "ZWZ", "UUU", Character.valueOf('X'), Items.field_151156_bN, 
            Character.valueOf('Y'), PPRegisterer.PPItems.GLUE, Character.valueOf('Z'), Blocks.field_150411_aY, Character.valueOf('W'), 
            new ItemStack(Items.field_151144_bL, 1, 4), 
            Character.valueOf('U'), ItemsRegister.COMPRESSED_PALADIUM });
      GameRegistry.addRecipe(new ItemStack(COORDINATE_JAMMER, 1), new Object[] { "ZXZ", "XYX", "ZXZ", Character.valueOf('X'), ItemsRegister.FINDIUM, 
            Character.valueOf('Y'), ItemsRegister.UNCLAIMFINDER_ROUGE, Character.valueOf('Z'), BlocksRegister.BLOCK_PALADIUM });
      GameRegistry.addRecipe(new ItemStack(COMPRESSED_QUARTZ, 1, 0), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), Blocks.field_150371_ca });
      GameRegistry.addRecipe(new ItemStack(COMPRESSED_QUARTZ, 1, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), new ItemStack(COMPRESSED_QUARTZ, 1, 0) });
      GameRegistry.addRecipe(new ItemStack(COMPRESSED_QUARTZ, 1, 2), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), new ItemStack(COMPRESSED_QUARTZ, 1, 1) });
      GameRegistry.addRecipe(new ItemStack(COMPRESSED_QUARTZ, 1, 3), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), new ItemStack(COMPRESSED_QUARTZ, 1, 2) });
      GameRegistry.addRecipe(new ItemStack(COMPRESSED_QUARTZ, 1, 4), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), new ItemStack(COMPRESSED_QUARTZ, 1, 3) });
      GameRegistry.addShapelessRecipe(new ItemStack(Blocks.field_150371_ca, 9), new Object[] { new ItemStack(COMPRESSED_QUARTZ, 1, 0) });
      GameRegistry.addShapelessRecipe(new ItemStack(COMPRESSED_QUARTZ, 9, 0), new Object[] { new ItemStack(COMPRESSED_QUARTZ, 1, 1) });
      GameRegistry.addShapelessRecipe(new ItemStack(COMPRESSED_QUARTZ, 9, 1), new Object[] { new ItemStack(COMPRESSED_QUARTZ, 1, 2) });
      GameRegistry.addShapelessRecipe(new ItemStack(COMPRESSED_QUARTZ, 9, 2), new Object[] { new ItemStack(COMPRESSED_QUARTZ, 1, 3) });
      GameRegistry.addShapelessRecipe(new ItemStack(COMPRESSED_QUARTZ, 9, 3), new Object[] { new ItemStack(COMPRESSED_QUARTZ, 1, 4) });
      GameRegistry.addRecipe(new ItemStack(EMPOWERED_QUARTZ, 1), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(COMPRESSED_QUARTZ, 1, 0), 
            Character.valueOf('Y'), new ItemStack((Block)BlocksRegister.SHINY_ERABLE_WOOD, 1) });
      TNTS.forEach(b -> b.effect.registerRecipes((Block)b));
      OBSIS.forEach(b -> b.objectEffect.registerRecipes((Block)b));
    }
    
    private static Block addEffectBlock(ObjectEffect effect) {
      FMLCommonHandler.instance().bus().register(effect);
      MinecraftForge.EVENT_BUS.register(effect);
      if (effect.getName().contains("tnt")) {
        TNTEffect block = new TNTEffect(effect);
        TNTS.add(block);
        GameRegistry.registerBlock((Block)block, ItemBlockMultipleTypes.class, block.func_149739_a());
        return (Block)block;
      } 
      if (effect.getName().toLowerCase().contains("obsi")) {
        ObsiEffect block = new ObsiEffect(effect);
        OBSIS.add(block);
        GameRegistry.registerBlock((Block)block, ItemBlockMultipleTypes.class, block.func_149739_a());
        return (Block)block;
      } 
      return null;
    }
    
    public enum CanonTypes {
      BASE, CANNON;
    }
  }
  
  public static class PPItems {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\registerer\PPRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
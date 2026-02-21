package fr.paladium.palamod.modules.back2future;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.back2future.blocks.BlockBanner;
import fr.paladium.palamod.modules.back2future.blocks.BlockBeetroot;
import fr.paladium.palamod.modules.back2future.blocks.BlockSilkedMushroom;
import fr.paladium.palamod.modules.back2future.blocks.BlockStrippedNewLog;
import fr.paladium.palamod.modules.back2future.blocks.BlockStrippedOldLog;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodBark;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodButton;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodDoor;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodFence;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodFenceGate;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodPressurePlate;
import fr.paladium.palamod.modules.back2future.blocks.BlockWoodTrapdoor;
import fr.paladium.palamod.modules.back2future.blocks.ChorusFlower;
import fr.paladium.palamod.modules.back2future.blocks.ChorusPlant;
import fr.paladium.palamod.modules.back2future.blocks.CoarseDirt;
import fr.paladium.palamod.modules.back2future.blocks.ConcreteRegistry;
import fr.paladium.palamod.modules.back2future.blocks.CryingObsidian;
import fr.paladium.palamod.modules.back2future.blocks.EndBricks;
import fr.paladium.palamod.modules.back2future.blocks.EndRod;
import fr.paladium.palamod.modules.back2future.blocks.FrostedIce;
import fr.paladium.palamod.modules.back2future.blocks.GrassPath;
import fr.paladium.palamod.modules.back2future.blocks.InvertedDaylightDetector;
import fr.paladium.palamod.modules.back2future.blocks.IronTrapdoor;
import fr.paladium.palamod.modules.back2future.blocks.NewAnvil;
import fr.paladium.palamod.modules.back2future.blocks.NewBeacon;
import fr.paladium.palamod.modules.back2future.blocks.NewBrewingStand;
import fr.paladium.palamod.modules.back2future.blocks.NewDaylightSensor;
import fr.paladium.palamod.modules.back2future.blocks.NewEnchantmentTable;
import fr.paladium.palamod.modules.back2future.blocks.OldGravel;
import fr.paladium.palamod.modules.back2future.blocks.OldRose;
import fr.paladium.palamod.modules.back2future.blocks.PrismarineBlocks;
import fr.paladium.palamod.modules.back2future.blocks.PurpurBlock;
import fr.paladium.palamod.modules.back2future.blocks.PurpurPillar;
import fr.paladium.palamod.modules.back2future.blocks.PurpurSlab;
import fr.paladium.palamod.modules.back2future.blocks.PurpurStairs;
import fr.paladium.palamod.modules.back2future.blocks.RedSandstone;
import fr.paladium.palamod.modules.back2future.blocks.RedSandstoneSlab;
import fr.paladium.palamod.modules.back2future.blocks.RedSandstoneStairs;
import fr.paladium.palamod.modules.back2future.blocks.SeaLantern;
import fr.paladium.palamod.modules.back2future.blocks.SlimeBlock;
import fr.paladium.palamod.modules.back2future.blocks.Sponge;
import fr.paladium.palamod.modules.back2future.blocks.Stone;
import java.lang.reflect.Field;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

public class ModBlocks {
  public static final Block stone = (Block)new Stone();
  
  public static final Block iron_trapdoor = (Block)new IronTrapdoor();
  
  public static final Block prismarine = (Block)new PrismarineBlocks();
  
  public static final Block sea_lantern = (Block)new SeaLantern();
  
  public static final Block inverted_daylight_detector = (Block)new InvertedDaylightDetector();
  
  public static final Block red_sandstone = (Block)new RedSandstone();
  
  public static final Block red_sandstone_slab = (Block)new RedSandstoneSlab();
  
  public static final Block red_sandstone_stairs = (Block)new RedSandstoneStairs();
  
  public static final Block brown_mushroom_block = (Block)new BlockSilkedMushroom(Blocks.field_150420_aW, "brown");
  
  public static final Block red_mushroom_block = (Block)new BlockSilkedMushroom(Blocks.field_150419_aX, "red");
  
  public static final Block coarse_dirt = (Block)new CoarseDirt();
  
  public static final Block banner = (Block)new BlockBanner();
  
  public static final Block slime = (Block)new SlimeBlock();
  
  public static final Block sponge = (Block)new Sponge();
  
  public static final Block old_gravel = (Block)new OldGravel();
  
  public static final Block beetroot = (Block)new BlockBeetroot();
  
  public static final Block purpur_block = (Block)new PurpurBlock();
  
  public static final Block purpur_pillar = (Block)new PurpurPillar();
  
  public static final Block purpur_stairs = (Block)new PurpurStairs();
  
  public static final Block purpur_slab = (Block)new PurpurSlab();
  
  public static final Block end_bricks = (Block)new EndBricks();
  
  public static final Block grass_path = (Block)new GrassPath();
  
  public static final Block end_rod = (Block)new EndRod();
  
  public static final Block chorus_plant = (Block)new ChorusPlant();
  
  public static final Block chorus_flower = (Block)new ChorusFlower();
  
  public static final Block crying_obsidian = (Block)new CryingObsidian();
  
  public static final Block frosted_ice = (Block)new FrostedIce();
  
  public static final Block brewing_stand = (Block)new NewBrewingStand();
  
  public static final Block rose = (Block)new OldRose();
  
  public static final Block beacon = (Block)new NewBeacon();
  
  public static final Block enchantment_table = (Block)new NewEnchantmentTable();
  
  public static final Block anvil = (Block)new NewAnvil();
  
  public static final Block daylight_sensor = (Block)new NewDaylightSensor();
  
  public static final Block log_stripped = (Block)new BlockStrippedOldLog();
  
  public static final Block log2_stripped = (Block)new BlockStrippedNewLog();
  
  public static final Block log_bark = (Block)new BlockWoodBark();
  
  public static final Block[] doors = new Block[BlockWood.field_150096_a.length - 1];
  
  public static final Block[] fences = new Block[BlockWood.field_150096_a.length];
  
  public static final Block[] gates = new Block[BlockWood.field_150096_a.length - 1];
  
  public static final Block[] pressurePlates = new Block[BlockWood.field_150096_a.length - 1];
  
  public static final Block[] buttons = new Block[BlockWood.field_150096_a.length - 1];
  
  public static final Block[] trapdoors = new Block[BlockWood.field_150096_a.length - 1];
  
  static {
    int i;
    for (i = 0; i < doors.length; i++)
      doors[i] = (Block)new BlockWoodDoor(i + 1); 
    for (i = 0; i < fences.length; i++)
      fences[i] = (Block)new BlockWoodFence(i); 
    for (i = 0; i < gates.length; i++)
      gates[i] = (Block)new BlockWoodFenceGate(i + 1); 
    for (i = 0; i < pressurePlates.length; i++)
      pressurePlates[i] = (Block)new BlockWoodPressurePlate(i + 1); 
    for (i = 0; i < buttons.length; i++)
      buttons[i] = (Block)new BlockWoodButton(i + 1); 
    for (i = 0; i < trapdoors.length; i++)
      trapdoors[i] = (Block)new BlockWoodTrapdoor(i + 1); 
  }
  
  public static void init() {
    ConcreteRegistry.init();
    try {
      for (Field f : ModBlocks.class.getDeclaredFields()) {
        Object obj = f.get(null);
        if (obj instanceof Block) {
          registerBlock((Block)obj);
        } else if (obj instanceof Block[]) {
          for (Block block : (Block[])obj) {
            if (block != null)
              registerBlock(block); 
          } 
        } 
      } 
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } 
  }
  
  private static void registerBlock(Block block) {
    if (!(block instanceof IConfigurable) || ((IConfigurable)block).isEnabled()) {
      String name = block.func_149739_a();
      String[] strings = name.split("\\.");
      if (block instanceof ISubBlocksBlock) {
        GameRegistry.registerBlock(block, ((ISubBlocksBlock)block).getItemBlockClass(), strings[strings.length - 1]);
      } else {
        GameRegistry.registerBlock(block, strings[strings.length - 1]);
      } 
      if (block instanceof IBurnableBlock)
        Blocks.field_150480_ab.setFireInfo(block, 5, 20); 
    } 
  }
  
  public static interface IBurnableBlock {}
  
  public static interface ISubBlocksBlock {
    Class<? extends ItemBlock> getItemBlockClass();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\ModBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
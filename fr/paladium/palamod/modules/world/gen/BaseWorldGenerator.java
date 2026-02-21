package fr.paladium.palamod.modules.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.modules.world.structure.WorldTree1Gen;
import fr.paladium.palamod.modules.world.structure.WorldTree2Gen;
import fr.paladium.palamod.modules.world.structure.WorldTree3Gen;
import fr.paladium.palamod.modules.world.structure.WorldTree4Gen;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BaseWorldGenerator implements IWorldGenerator {
  private final WorldGenMinable AMETHYST_ORE = new WorldGenMinable(PWorld.AMETHYST_ORE, 0, 8, Blocks.field_150348_b);
  
  private final WorldGenMinable TITANE_ORE = new WorldGenMinable(PWorld.TITANE_ORE, 0, 6, Blocks.field_150348_b);
  
  private final WorldGenMinable PALADIUM_ORE = new WorldGenMinable(PWorld.PALADIUM_ORE, 0, 4, Blocks.field_150348_b);
  
  private final WorldGenMinable PALADIUM_GREEN_ORE = new WorldGenMinable(PWorld.PALADIUM_GREEN_ORE, 0, 3, Blocks.field_150348_b);
  
  private final WorldGenMinable FINDIUM_ORE = new WorldGenMinable(PWorld.FINDIUM_ORE, 0, 3, Blocks.field_150348_b);
  
  private final WorldGenMinable TRIXIUM_ORE = new WorldGenMinable(PWorld.TRIXIUM_ORE, 0, 3, Blocks.field_150348_b);
  
  private final WorldGenMinable XPBush = new WorldGenMinable((Block)BlocksRegister.BUSH_XP_BERRY, 0, 3, Blocks.field_150348_b);
  
  private final WorldGenMinable GRANITE_BLOCK = new WorldGenMinable(PWorld.GRANITE_BLOCK, 0, 8, Blocks.field_150348_b);
  
  private final WorldGenMinable DIORITE_BLOCK = new WorldGenMinable(PWorld.DIORITE_BLOCK, 0, 8, Blocks.field_150348_b);
  
  private final WorldGenMinable ANDESITE_BLOCK = new WorldGenMinable(PWorld.ANDESITE_BLOCK, 0, 8, Blocks.field_150348_b);
  
  private final WorldGenMinable LIMESTONE_BLOCK = new WorldGenMinable(PWorld.LIMESTONE_BLOCK, 0, 8, Blocks.field_150348_b);
  
  private final WorldGenMinable MARBLE_BLOCK = new WorldGenMinable(PWorld.MARBLE_BLOCK, 0, 7, Blocks.field_150348_b);
  
  private final WorldGenMinable ENDIUM_NUGGET_ORE = new WorldGenMinable(BlocksRegister.ENDIUM_NUGGET_ORE, 0, 3, Blocks.field_150348_b);
  
  private final WorldGenMinable INVOKER_ORE = new WorldGenMinable(BlocksRegister.INVOKER_ORE, 0, 3, Blocks.field_150348_b);
  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (world.field_73011_w.field_76577_b != WorldType.field_77138_c)
      generateUndergroundOres(random, chunkX * 16, chunkZ * 16, world); 
  }
  
  private void generateUndergroundOres(Random random, int xChunk, int zChunk, World world) {
    int multiplier = PMiner.proxy.isMinerDimension() ? 3 : 1;
    double spawnBoost = 1.0D;
    if (PConfigs.amethyst_ore_enabled) {
      int i = (int)Math.ceil((5 * multiplier) * 1.0D);
      for (int j = 0; j <= i; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.amethyst_ore_layer_min + random.nextInt(PConfigs.amethyst_ore_layer_max - PConfigs.amethyst_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.amethyst_ore_rarety);
        if (gen == 1)
          this.AMETHYST_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } 
    if (PConfigs.titane_ore_enabled) {
      int i = (int)Math.ceil((4 * multiplier) * 1.0D);
      for (int j = 0; j <= i; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.titane_ore_layer_min + random.nextInt(PConfigs.titane_ore_layer_max - PConfigs.titane_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.titane_ore_rarety);
        if (gen == 1)
          this.TITANE_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } 
    if (PConfigs.doublepaladium_ore_enabled) {
      int i = (int)Math.ceil((2 * multiplier) * 1.0D);
      for (int j = 0; j <= i; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.paladium_ore_layer_min + random.nextInt(PConfigs.paladium_ore_layer_max - PConfigs.paladium_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.paladium_ore_rarety);
        if (gen <= PConfigs.paladium_ore_rarety / 2)
          this.PALADIUM_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } else {
      int i = (int)Math.ceil((2 * multiplier) * 1.0D);
      for (int j = 0; j <= i; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.paladium_ore_layer_min + random.nextInt(PConfigs.paladium_ore_layer_max - PConfigs.paladium_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.paladium_ore_rarety);
        if (gen == 1)
          this.PALADIUM_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } 
    if (PConfigs.paladiumgreen_ore_enabled) {
      int i = (int)Math.ceil((1 * multiplier) * 1.0D);
      for (int j = 0; j <= i + 1; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.paladiumgreen_ore_layer_min + random.nextInt(PConfigs.paladiumgreen_ore_layer_max - PConfigs.paladiumgreen_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.paladiumgreen_ore_rarety);
        if (gen == 1)
          this.PALADIUM_GREEN_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } 
    if (PConfigs.findium_ore_enabled) {
      int i = (int)Math.ceil((3 * multiplier) * 1.0D);
      for (int j = 0; j <= i; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.findium_ore_layer_min + random.nextInt(PConfigs.findium_ore_layer_max - PConfigs.findium_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.findium_ore_rarety);
        if (gen == 1)
          this.FINDIUM_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } 
    if (PConfigs.trixium_ore_enabled) {
      int i = (int)Math.ceil((3 * multiplier) * 1.0D);
      for (int j = 0; j <= i; j++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.trixium_ore_layer_min + random.nextInt(PConfigs.trixium_ore_layer_max - PConfigs.trixium_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.trixium_ore_rarety);
        if (gen == 1)
          this.TRIXIUM_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
      } 
    } 
    if (PMiner.proxy.isMinerDimension())
      for (int i = 0; i <= 3; i++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = PConfigs.paladium_ore_layer_min + random.nextInt(PConfigs.paladium_ore_layer_max - PConfigs.paladium_ore_layer_min);
        int zPos = zChunk + random.nextInt(16);
        int gen = random.nextInt(PConfigs.paladiumgreen_ore_rarety * 3);
        if (gen == 1) {
          this.ENDIUM_NUGGET_ORE.func_76484_a(world, random, xPos, yPos, zPos);
          boolean found = false;
          for (int j = -8; j <= 8; j++) {
            for (int k = -8; k <= 8; k++) {
              for (int m = -8; m <= 8; m++) {
                if (world.func_147439_a(xPos + j, yPos + k, zPos + m) == BlocksRegister.ENDIUM_NUGGET_ORE)
                  if (!found) {
                    found = true;
                  } else {
                    world.func_147449_b(xPos + j, yPos + k, zPos + m, Blocks.field_150348_b);
                  }  
              } 
            } 
          } 
        } 
      }  
    int attempts = (int)Math.ceil((3 * multiplier) * 1.0D);
    int q;
    for (q = 0; q <= attempts; q++) {
      int xPos = xChunk + random.nextInt(16);
      int yPos = PConfigs.findium_ore_layer_min + random.nextInt(PConfigs.findium_ore_layer_max - PConfigs.findium_ore_layer_min);
      int zPos = zChunk + random.nextInt(16);
      int gen = random.nextInt(PConfigs.findium_ore_rarety * 2);
      if (gen == 1)
        this.INVOKER_ORE.func_76484_a(world, random, xPos, yPos, zPos); 
    } 
    attempts = (int)Math.ceil((2 * multiplier) * 1.0D);
    for (q = 0; q <= attempts; q++) {
      int xPos = xChunk + random.nextInt(16);
      int yPos = PConfigs.titane_ore_layer_min + random.nextInt(PConfigs.titane_ore_layer_max - PConfigs.titane_ore_layer_min);
      int zPos = zChunk + random.nextInt(16);
      int gen = random.nextInt(PConfigs.findium_ore_rarety);
      if (gen == 1)
        this.XPBush.func_76484_a(world, random, xPos, yPos, zPos); 
    } 
    if (PConfigs.granite_enabled)
      for (q = 0; q <= 16; q++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = 40 + random.nextInt(88);
        int zPos = zChunk + random.nextInt(16);
        this.GRANITE_BLOCK.func_76484_a(world, random, xPos, yPos, zPos);
      }  
    if (PConfigs.diorite_enabled)
      for (q = 0; q <= 16; q++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = 40 + random.nextInt(88);
        int zPos = zChunk + random.nextInt(16);
        this.DIORITE_BLOCK.func_76484_a(world, random, xPos, yPos, zPos);
      }  
    if (PConfigs.andesite_enabled)
      for (q = 0; q <= 16; q++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = 40 + random.nextInt(88);
        int zPos = zChunk + random.nextInt(16);
        this.ANDESITE_BLOCK.func_76484_a(world, random, xPos, yPos, zPos);
      }  
    if (PConfigs.limestone_enabled)
      for (q = 0; q <= 16; q++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = 40 + random.nextInt(88);
        int zPos = zChunk + random.nextInt(16);
        this.LIMESTONE_BLOCK.func_76484_a(world, random, xPos, yPos, zPos);
      }  
    if (PConfigs.marble_enabled)
      for (q = 0; q <= 12; q++) {
        int xPos = xChunk + random.nextInt(16);
        int yPos = 40 + random.nextInt(88);
        int zPos = zChunk + random.nextInt(16);
        this.MARBLE_BLOCK.func_76484_a(world, random, xPos, yPos, zPos);
      }  
    if (PConfigs.custom_tree_judeecercis_enabled) {
      int xPos = xChunk + random.nextInt(16);
      int zPos = zChunk + random.nextInt(16);
      int yPos = getIntTopBlock(world, xPos, zPos);
      if (world.func_147439_a(xPos, yPos, zPos).equals(Blocks.field_150349_c) && random.nextInt(100) <= 8)
        generateRandomTree(PWorld.LEAVE_JUDEECERCIS, PWorld.LOG_JUDEECERCIS).func_76484_a(world, random, xPos, yPos, zPos); 
    } 
    if (PConfigs.custom_tree_jacaranda_enabled) {
      int xPos = xChunk + random.nextInt(16);
      int zPos = zChunk + random.nextInt(16);
      int yPos = getIntTopBlock(world, xPos, zPos);
      if (world.func_147439_a(xPos, yPos, zPos).equals(Blocks.field_150349_c) && random.nextInt(100) <= 8)
        generateRandomTree(PWorld.LEAVE_JACARANDA, PWorld.LOG_JACARANDA).func_76484_a(world, random, xPos, yPos, zPos); 
    } 
    if (PConfigs.custom_tree_erable_enabled) {
      int xPos = xChunk + random.nextInt(16);
      int zPos = zChunk + random.nextInt(16);
      int yPos = getIntTopBlock(world, xPos, zPos);
      if (world.func_147439_a(xPos, yPos, zPos).equals(Blocks.field_150349_c) && random.nextInt(100) <= 8)
        generateRandomTree(PWorld.LEAVE_ERABLE, PWorld.LOG_ERABLE).func_76484_a(world, random, xPos, yPos, zPos); 
    } 
    if (PConfigs.custom_tree_ostrya_enabled) {
      int xPos = xChunk + random.nextInt(16);
      int zPos = zChunk + random.nextInt(16);
      int yPos = getIntTopBlock(world, xPos, zPos);
      if (world.func_147439_a(xPos, yPos, zPos).equals(Blocks.field_150349_c) && random.nextInt(100) <= 8)
        generateRandomTree(PWorld.LEAVE_OSTRYA, PWorld.LOG_OSTRYA).func_76484_a(world, random, xPos, yPos, zPos); 
    } 
  }
  
  public int getIntTopBlock(World world, int x, int y) {
    int k;
    for (k = 63; !world.func_147437_c(x, k + 1, y); k++);
    return k;
  }
  
  public WorldGenerator generateRandomTree(Block leave, Block log) {
    int random = (int)(Math.random() * 3.0D);
    if (random <= 1)
      return (WorldGenerator)new WorldTree1Gen(leave, log); 
    if (random <= 2)
      return (WorldGenerator)new WorldTree2Gen(leave, log); 
    if (random <= 3)
      return (WorldGenerator)new WorldTree3Gen(leave, log); 
    if (random <= 4)
      return (WorldGenerator)new WorldTree4Gen(leave, log); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\gen\BaseWorldGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
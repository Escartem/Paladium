package fr.paladium.palamod.modules.back2future.world;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class OceanMonument {
  private static final List<BiomeGenBase> validBiomes = Arrays.asList(new BiomeGenBase[] { BiomeGenBase.field_76771_b, BiomeGenBase.field_150575_M, BiomeGenBase.field_76781_i, BiomeGenBase.field_76776_l, BiomeGenBase.field_76777_m });
  
  private static final Map<WorldCoord, Integer> map = new HashMap<>();
  
  public static void makeMap() {
    try {
      InputStream is = Back2Future.class.getResourceAsStream("/assets/OceanMonument.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String s;
      while ((s = br.readLine()) != null) {
        String[] data = s.split("-");
        data[0] = data[0].trim();
        data[0] = data[0].substring(1, data[0].length() - 1);
        data[1] = data[1].trim();
        String[] coords = data[0].split(",");
        WorldCoord key = new WorldCoord(Integer.parseInt(coords[0].trim()), Integer.parseInt(coords[1].trim()), Integer.parseInt(coords[2].trim()));
        int value = Integer.parseInt(data[1]);
        map.put(key, Integer.valueOf(value));
      } 
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static Map<WorldCoord, Integer> getMap() {
    return map;
  }
  
  public static void buildTemple(World world, int x, int y, int z) {
    if (world.field_72995_K)
      return; 
    for (Map.Entry<WorldCoord, Integer> entry : getMap().entrySet()) {
      WorldCoord pos = entry.getKey();
      int value = ((Integer)entry.getValue()).intValue();
      Block block = null;
      int meta = 0;
      switch (value) {
        case 0:
        case 1:
        case 2:
          block = ModBlocks.prismarine;
          meta = value;
          break;
        case 3:
          block = ModBlocks.sea_lantern;
          break;
        case 4:
          block = Blocks.field_150340_R;
          break;
        case 5:
          block = Back2Future.enableSponge ? ModBlocks.sponge : Blocks.field_150360_v;
          meta = 1;
          break;
        case 6:
          block = Blocks.field_150355_j;
          break;
      } 
      if (block != null)
        world.func_147465_d(pos.x + x, pos.y + y, pos.z + z, block, meta, 2); 
    } 
    for (int i = 0; i < 7; i++) {
      generatePillar(world, x + 5 * i + 4 * i, y, z, ModBlocks.prismarine, 1);
      generatePillar(world, x, y, z + 5 * i + 4 * i, ModBlocks.prismarine, 1);
      generatePillar(world, x + 54, y, z + 5 * i + 4 * i, ModBlocks.prismarine, 1);
      if (i != 3)
        generatePillar(world, x + 5 * i + 4 * i, y, z + 54, ModBlocks.prismarine, 1); 
    } 
  }
  
  private static void generatePillar(World world, int x, int y, int z, Block block, int meta) {
    int i;
    for (i = 1; i <= 5; i++)
      generatePillarSection(world, x, y - i, z, block, meta); 
    y -= 5;
    for (; y >= 0; y--) {
      generatePillarSection(world, x, y, z, block, meta);
      for (i = 0; i < 4; i++) {
        for (int k = 0; k < 4; k++) {
          if (world.func_147439_a(x + i, y, z).func_149688_o() != Material.field_151586_h && y > 3) {
            generatePillarSection(world, x, y - 1, z, block, meta);
            generatePillarSection(world, x, y - 2, z, block, meta);
            return;
          } 
        } 
      } 
    } 
  }
  
  private static void generatePillarSection(World world, int x, int y, int z, Block block, int meta) {
    for (int i = 0; i < 4; i++) {
      for (int k = 0; k < 4; k++) {
        if (world.func_147439_a(x + i, y, z).func_149712_f(world, x + i, y, z + k) > 0.0F)
          world.func_147465_d(x + i, y, z + k, block, meta, 2); 
      } 
    } 
  }
  
  public static void generateFile(World world, int x, int y, int z, String path) {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
      for (int i = 0; i < 58; i++) {
        for (int j = 0; j < 22; j++) {
          for (int k = 0; k < 58; k++) {
            Block b = world.func_147439_a(x + i, y + j, z + k);
            int meta = world.func_72805_g(x + i, y + j, z + k);
            String s = "(" + i + ", " + j + ", " + k + ") - ";
            if (b == ModBlocks.prismarine) {
              s = s + meta;
            } else if (b == ModBlocks.sea_lantern) {
              s = s + '\003';
            } else if (b == Blocks.field_150340_R) {
              s = s + '\004';
            } else if (b == Blocks.field_150360_v) {
              s = s + '\005';
            } else if (b == Blocks.field_150399_cn) {
              s = s + '\006';
            } else {
              s = null;
            } 
            if (s != null) {
              bw.write(s);
              bw.newLine();
            } 
          } 
        } 
      } 
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean canSpawnAt(World worldObj, int chunkX, int chunkZ) {
    int spacing = 32;
    int separation = 5;
    int xx = chunkX;
    int zz = chunkZ;
    if (chunkX < 0)
      chunkX -= spacing - 1; 
    if (chunkZ < 0)
      chunkZ -= spacing - 1; 
    int i1 = chunkX / spacing;
    int j1 = chunkZ / spacing;
    Random random = worldObj.func_72843_D(i1, j1, 10387313);
    i1 *= spacing;
    j1 *= spacing;
    i1 += (random.nextInt(spacing - separation) + random.nextInt(spacing - separation)) / 2;
    j1 += (random.nextInt(spacing - separation) + random.nextInt(spacing - separation)) / 2;
    if (xx == i1 && zz == j1) {
      if (worldObj.func_72959_q().func_76935_a(xx * 16 + 8, zz * 16 + 8) != BiomeGenBase.field_150575_M)
        return false; 
      if (worldObj.func_72959_q().func_76940_a(xx * 16 + 8, zz * 16 + 8, 29, validBiomes))
        return true; 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\world\OceanMonument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
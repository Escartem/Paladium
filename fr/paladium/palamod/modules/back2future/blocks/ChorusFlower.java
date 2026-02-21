package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ChorusFlower extends Block implements IConfigurable {
  @SideOnly(Side.CLIENT)
  private IIcon deadIcon;
  
  public ChorusFlower() {
    super(Material.field_151575_d);
    func_149711_c(0.4F);
    func_149675_a(true);
    func_149672_a(Block.field_149766_f);
    func_149658_d("chorus_flower");
    func_149663_c(Utils.getUnlocalisedName("chorus_flower"));
    func_149647_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return !(entity instanceof net.minecraft.entity.boss.EntityDragon);
  }
  
  public int func_149645_b() {
    return RenderIDs.CHORUS_FLOWER;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (meta >= 5) ? this.deadIcon : this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    super.func_149651_a(reg);
    this.deadIcon = reg.func_94245_a("chorus_flower_dead");
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (world.field_72995_K)
      return; 
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 5)
      return; 
    if (!func_149718_j(world, x, y, z)) {
      world.func_147480_a(x, y, z, true);
    } else if (world.func_147437_c(x, y + 1, z)) {
      boolean canGrowUp = false;
      boolean isSegmentOnEndstone = false;
      Block lowerBlock = world.func_147439_a(x, y - 1, z);
      if (lowerBlock == Blocks.field_150377_bs) {
        canGrowUp = true;
      } else if (lowerBlock == ModBlocks.chorus_plant) {
        int par8 = 1;
        int height;
        for (height = 0; height < 4; height++) {
          Block b = world.func_147439_a(x, y - par8 + 1, z);
          if (b != ModBlocks.chorus_plant) {
            if (b == Blocks.field_150377_bs)
              isSegmentOnEndstone = true; 
            break;
          } 
          par8++;
        } 
        height = 4;
        if (isSegmentOnEndstone)
          height++; 
        if (par8 < 2 || rand.nextInt(height) >= par8)
          canGrowUp = true; 
      } else if (lowerBlock.isAir((IBlockAccess)world, x, y - 1, z)) {
        canGrowUp = true;
      } 
      if (canGrowUp && isSpaceAroundFree(world, x, y + 1, z, ForgeDirection.DOWN) && world.func_147437_c(x, y + 2, z)) {
        world.func_147449_b(x, y, z, ModBlocks.chorus_plant);
        world.func_147465_d(x, y + 1, z, this, meta, 3);
      } else if (meta < 4) {
        int tries = rand.nextInt(4);
        boolean grew = false;
        if (isSegmentOnEndstone)
          tries++; 
        for (int i = 0; i < tries; i++) {
          ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
          int xx = x + dir.offsetX;
          int yy = y + dir.offsetY;
          int zz = z + dir.offsetZ;
          if (world.func_147437_c(xx, yy, zz) && isSpaceAroundFree(world, xx, yy, zz, dir.getOpposite())) {
            world.func_147465_d(xx, yy, zz, this, meta + 1, 3);
            grew = true;
          } 
        } 
        if (grew) {
          world.func_147465_d(x, y, z, ModBlocks.chorus_plant, 0, 3);
        } else {
          world.func_147465_d(x, y, z, this, 5, 3);
        } 
      } else if (meta == 4) {
        world.func_147465_d(x, y, z, this, 5, 3);
      } 
    } 
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block neighbour) {
    if (!func_149718_j(world, x, y, z))
      world.func_147480_a(x, y, z, false); 
  }
  
  public boolean func_149718_j(World world, int x, int y, int z) {
    return canPlantStay(world, x, y, z);
  }
  
  public static boolean canPlantStay(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y - 1, z);
    if (block == ModBlocks.chorus_plant || block == Blocks.field_150377_bs)
      return true; 
    if (!block.isAir((IBlockAccess)world, x, y - 1, z))
      return false; 
    int adjecentCount = 0;
    for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
      Block adjecentBlock = world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
      if (adjecentBlock == ModBlocks.chorus_plant) {
        adjecentCount++;
      } else if (!adjecentBlock.isAir((IBlockAccess)world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) {
        return false;
      } 
    } 
    return (adjecentCount == 1);
  }
  
  public static boolean isSpaceAroundFree(World world, int x, int y, int z, ForgeDirection skip) {
    ForgeDirection dir;
    Iterator<ForgeDirection> iterator = Arrays.<ForgeDirection>asList(ForgeDirection.VALID_DIRECTIONS).iterator();
    do {
      if (!iterator.hasNext())
        return true; 
      dir = iterator.next();
    } while (dir == skip || world.func_147437_c(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149742_c(World world, int x, int y, int z) {
    return (super.func_149742_c(world, x, y, z) && func_149718_j(world, x, y, z));
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\ChorusFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
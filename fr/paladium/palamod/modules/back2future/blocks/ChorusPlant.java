package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ChorusPlant extends Block implements IConfigurable {
  public ChorusPlant() {
    super(Material.field_151575_d);
    func_149711_c(0.5F);
    func_149675_a(true);
    func_149672_a(Block.field_149766_f);
    func_149658_d("chorus_plant");
    func_149663_c(Utils.getUnlocalisedName("chorus_plant"));
    func_149647_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (world.field_72995_K)
      return; 
    if (world.func_147437_c(x, y + 1, z)) {
      boolean canGrowUp = false;
      boolean isSegmentOnEndstone = false;
      Block lowerBlock = world.func_147439_a(x, y - 1, z);
      if (lowerBlock == Blocks.field_150377_bs) {
        canGrowUp = true;
      } else if (lowerBlock == ModBlocks.chorus_plant) {
        int segHeight = 1;
        int height;
        for (height = 0; height < 4; height++) {
          Block b = world.func_147439_a(x, y - segHeight + 1, z);
          if (b != ModBlocks.chorus_plant) {
            if (b == Blocks.field_150377_bs)
              isSegmentOnEndstone = true; 
            break;
          } 
          segHeight++;
        } 
        height = 4;
        if (isSegmentOnEndstone)
          height++; 
        if (segHeight < 2 || rand.nextInt(height) >= segHeight)
          canGrowUp = true; 
      } else if (lowerBlock.isAir((IBlockAccess)world, x, y - 1, z)) {
        canGrowUp = true;
      } 
      if (canGrowUp && isSpaceAroundFree(world, x, y + 1, z, ForgeDirection.DOWN) && world.func_147437_c(x, y + 2, z)) {
        int baseY = y;
        int height = 1;
        while (world.func_147439_a(x, baseY - 1, z) == ModBlocks.chorus_plant && height < 6) {
          baseY--;
          height++;
        } 
        if (world.func_147439_a(x, baseY - 1, z) == Blocks.field_150377_bs && height < 5) {
          world.func_147449_b(x, y + 1, z, ModBlocks.chorus_plant);
          world.func_147464_a(x, y + 1, z, this, 100);
        } 
      } 
    } 
    world.func_147464_a(x, y, z, this, 100);
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
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return (!(entity instanceof net.minecraft.entity.boss.EntityDragon) && world.func_147439_a(x, y - 1, z) != Blocks.field_150377_bs);
  }
  
  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
    if (world.func_147439_a(x, y - 1, z) == Blocks.field_150377_bs && !player.field_71075_bZ.field_75098_d) {
      if (world.func_147439_a(x, y + 1, z) == this)
        world.func_147480_a(x, y + 1, z, true); 
      return false;
    } 
    return super.removedByPlayer(world, player, x, y, z, willHarvest);
  }
  
  public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    if (world.func_147439_a(x, y - 1, z) == Blocks.field_150377_bs)
      return; 
    super.onBlockExploded(world, x, y, z, explosion);
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block neighbour) {
    if (world.func_147439_a(x, y - 1, z) == Blocks.field_150377_bs)
      return; 
    if (neighbour == this)
      world.func_147480_a(x, y, z, true); 
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  protected boolean func_149700_E() {
    return false;
  }
  
  public int func_149645_b() {
    return RenderIDs.CHORUS_PLANT;
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return ModItems.chorus_fruit;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\ChorusPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
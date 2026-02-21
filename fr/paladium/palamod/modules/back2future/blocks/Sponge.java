package fr.paladium.palamod.modules.back2future.blocks;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.world.WorldCoord;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Sponge extends BlockGeneric implements IConfigurable {
  public Sponge() {
    super(Material.field_151583_m, new String[] { "", "wet" });
    func_149711_c(0.6F);
    func_149672_a(field_149779_h);
    func_149658_d("sponge");
    func_149663_c(Utils.getUnlocalisedName("sponge"));
    func_149647_a(Back2Future.enableSponge ? Back2Future.creativeTab : null);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
    if (!world.field_72995_K && 
      player instanceof net.minecraft.entity.player.EntityPlayerMP && !EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
      return;
    } 
    tryAbsorb(world, x, y, z, (world.func_72805_g(x, y, z) == 1));
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block neighborBlock) {
    tryAbsorb(world, x, y, z, (world.func_72805_g(x, y, z) == 1));
    super.func_149695_a(world, x, y, z, neighborBlock);
  }
  
  protected void tryAbsorb(World worldIn, int x, int y, int z, boolean wet) {
    if (!wet && absorb(worldIn, x, y, z)) {
      worldIn.func_72921_c(x, y, z, 1, 2);
      worldIn.func_72926_e(2001, x, y, z, Block.func_149682_b(Blocks.field_150355_j));
    } 
  }
  
  private boolean absorb(World world, int x, int y, int z) {
    LinkedList<Tuple> linkedlist = Lists.newLinkedList();
    ArrayList<WorldCoord> arraylist = Lists.newArrayList();
    linkedlist.add(new Tuple(new WorldCoord(x, y, z), Integer.valueOf(0)));
    int i = 0;
    while (!linkedlist.isEmpty()) {
      Tuple tuple = linkedlist.poll();
      WorldCoord blockpos1 = (WorldCoord)tuple.func_76341_a();
      int j = ((Integer)tuple.func_76340_b()).intValue();
      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
        WorldCoord blockpos2 = blockpos1.add(dir);
        if (world.func_147439_a(blockpos2.x, blockpos2.y, blockpos2.z).func_149688_o() == Material.field_151586_h) {
          world.func_147468_f(blockpos2.x, blockpos2.y, blockpos2.z);
          arraylist.add(blockpos2);
          i++;
          if (j < 5)
            linkedlist.add(new Tuple(blockpos2, Integer.valueOf(j + 1))); 
        } 
      } 
      if (i > 64)
        break; 
    } 
    Iterator<WorldCoord> iterator = arraylist.iterator();
    while (iterator.hasNext()) {
      WorldCoord blockpos1 = iterator.next();
      world.func_147460_e(blockpos1.x, blockpos1.y, blockpos1.z, Blocks.field_150350_a);
    } 
    return (i > 0);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {
    if (world.func_72805_g(x, y, z) == 1) {
      ForgeDirection dir = getRandomDirection(rand);
      if (dir != ForgeDirection.UP && !World.func_147466_a((IBlockAccess)world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) {
        double d0 = x;
        double d1 = y;
        double d2 = z;
        if (dir == ForgeDirection.DOWN) {
          d1 -= 0.05D;
          d0 += rand.nextDouble();
          d2 += rand.nextDouble();
        } else {
          d1 += rand.nextDouble() * 0.8D;
          if (dir == ForgeDirection.EAST || dir == ForgeDirection.WEST) {
            d2 += rand.nextDouble();
            if (dir == ForgeDirection.EAST) {
              d0++;
            } else {
              d0 += 0.05D;
            } 
          } else {
            d0 += rand.nextDouble();
            if (dir == ForgeDirection.SOUTH) {
              d2++;
            } else {
              d2 += 0.05D;
            } 
          } 
        } 
        world.func_72869_a("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
  
  private ForgeDirection getRandomDirection(Random rand) {
    return ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
  }
  
  public boolean isEnabled() {
    return Back2Future.enableSponge;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\Sponge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
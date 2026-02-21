package fr.paladium.palamod.modules.ajobs.common.blocks.root;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRootSeeds extends BlockBush implements IGrowable {
  @SideOnly(Side.CLIENT)
  private IIcon[] icon;
  
  public BlockRootSeeds(String unlocalizedName) {
    func_149675_a(true);
    float f = 0.5F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    func_149647_a((CreativeTabs)null);
    func_149711_c(0.0F);
    func_149672_a(field_149779_h);
    func_149649_H();
    func_149663_c(unlocalizedName);
    func_149658_d("palajobs:" + unlocalizedName);
    func_149649_H();
  }
  
  public boolean func_149727_a(World p_149727_1_, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    return false;
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random) {
    super.func_149674_a(world, x, y, z, random);
    if (world.func_72957_l(x, y + 1, z) >= 9) {
      int data = world.func_72805_g(x, y, z);
      if (data < 3) {
        float f = getGrowthRate(world, x, y, z);
        if (random.nextInt((int)(25.0F / f) + 1) == 0) {
          data++;
          world.func_72921_c(x, y, z, data, 2);
          if (data == 3)
            setBlocks(world, x, y, z); 
        } 
      } 
    } 
  }
  
  public void setBlocks(World world, int x, int y, int z) {
    world.func_147449_b(x, y, z, Blocks.field_150350_a);
    world.func_147449_b(x, y + 1, z, (Block)BlocksRegister.connectedDirtTilled);
    world.func_147449_b(x, y + 3, z, BlocksRegistry.ROOT_LEAVES);
  }
  
  private float getGrowthRate(World world, int x, int y, int z) {
    float f = 1.0F;
    Block block = world.func_147439_a(x, y, z - 1);
    Block block1 = world.func_147439_a(x, y, z + 1);
    Block block2 = world.func_147439_a(x - 1, y, z);
    Block block3 = world.func_147439_a(x + 1, y, z);
    Block block4 = world.func_147439_a(x - 1, y, z - 1);
    Block block5 = world.func_147439_a(x + 1, y, z - 1);
    Block block6 = world.func_147439_a(x + 1, y, z + 1);
    Block block7 = world.func_147439_a(x - 1, y, z + 1);
    boolean flag = (block2 == this || block3 == this);
    boolean flag1 = (block == this || block1 == this);
    boolean flag2 = (block4 == this || block5 == this || block6 == this || block7 == this);
    for (int l = x - 1; l <= x + 1; l++) {
      for (int i1 = z - 1; i1 <= z + 1; i1++) {
        float f1 = 0.0F;
        if (world.func_147439_a(l, y - 1, i1).canSustainPlant((IBlockAccess)world, l, y - 1, i1, ForgeDirection.UP, (IPlantable)this)) {
          f1 = 1.0F;
          if (world.func_147439_a(l, y - 1, i1).isFertile(world, l, y - 1, i1))
            f1 = 3.0F; 
        } 
        if (l != x || i1 != z)
          f1 /= 4.0F; 
        f += f1;
      } 
    } 
    if (flag2 || (flag && flag1))
      f /= 2.0F; 
    return f;
  }
  
  private void incrementGrowStage(World parWorld, Random parRand, int x, int y, int z) {
    int growStage = parWorld.func_72805_g(x, y, z) + MathHelper.func_76136_a(parRand, 2, 5);
    if (growStage < 3) {
      float f = getGrowthRate(parWorld, x, y, z);
      if (parRand.nextInt((int)(25.0F / f) + 1) == 0) {
        growStage++;
        parWorld.func_72921_c(x, y, z, growStage, 2);
        if (growStage == 3)
          setBlocks(parWorld, x, y, z); 
      } 
    } 
    parWorld.func_72921_c(x, y, z, growStage, 2);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int i1, int i2) {
    if (i2 < 0 || i2 > 2)
      i2 = 2; 
    return this.icon[i2];
  }
  
  public Item func_149650_a(int meta, Random random, int i1) {
    return null;
  }
  
  public int func_149745_a(Random random) {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.icon = new IIcon[3];
    for (int i = 0; i < this.icon.length; i++)
      this.icon[i] = register.func_94245_a(func_149641_N() + "_" + i); 
  }
  
  protected boolean func_149854_a(Block parBlock) {
    if (parBlock.getClass().equals(BlockFarmland.class))
      return true; 
    return false;
  }
  
  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    return world.func_147468_f(x, y, z);
  }
  
  public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
    return false;
  }
  
  public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
    return true;
  }
  
  public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
    incrementGrowStage(parWorld, parRand, parX, parY, parZ);
  }
  
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
    return EnumPlantType.Crop;
  }
  
  public Block getPlant(IBlockAccess world, int x, int y, int z) {
    return BlocksRegistry.ROOT_SEEDS_BLOCK;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\root\BlockRootSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
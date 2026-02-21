package fr.paladium.palamod.modules.pillage.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.ObsiEffect;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockObserver extends ObsiEffect {
  public boolean powered;
  
  @SideOnly(Side.CLIENT)
  private IIcon top;
  
  @SideOnly(Side.CLIENT)
  private IIcon bottom;
  
  @SideOnly(Side.CLIENT)
  private IIcon front;
  
  @SideOnly(Side.CLIENT)
  private IIcon backOff;
  
  @SideOnly(Side.CLIENT)
  private IIcon backOn;
  
  public BlockObserver(ObjectEffect effect) {
    super(effect);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    int meta = world.func_72805_g(x, y, z);
    if (isPowered(meta)) {
      setNotPowered(world, x, y, z);
    } else {
      setPowered(world, x, y, z);
      world.func_147464_a(x, y, z, (Block)this, 2);
    } 
    updateNeighborsInFront(world, x, y, z);
  }
  
  public void onNeighborChange(IBlockAccess blockAccess, int x, int y, int z, int tileX, int tileY, int tileZ) {
    World world = (World)blockAccess;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    int meta = world.func_72805_g(x, y, z);
    ForgeDirection direction = ForgeDirection.getOrientation(getDirection(meta));
    int newX = x + direction.offsetX;
    int newY = y + direction.offsetY;
    int newZ = z + direction.offsetZ;
    boolean change = world.func_147439_a(newX, newY, newZ).equals(block);
    if (!world.field_72995_K && change)
      startSignal(world, x, y, z); 
  }
  
  private void startSignal(World world, int x, int y, int z) {
    if (!this.powered && 
      !world.func_147477_a(x, y, z, (Block)this))
      world.func_147464_a(x, y, z, (Block)this, 2); 
  }
  
  protected void updateNeighborsInFront(World worldIn, int x, int y, int z) {
    int meta = worldIn.func_72805_g(x, y, z);
    ForgeDirection direction = ForgeDirection.getOrientation(getDirection(meta)).getOpposite();
    int newX = x + direction.offsetX;
    int newY = y + direction.offsetY;
    int newZ = z + direction.offsetZ;
    worldIn.func_147460_e(newX, newY, newZ, (Block)this);
    worldIn.func_147441_b(newX, newY, newZ, (Block)this, getDirection(meta));
  }
  
  public boolean func_149744_f() {
    return true;
  }
  
  public int func_149748_c(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
    return super.func_149709_b(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_);
  }
  
  public int func_149709_b(IBlockAccess blockAccess, int x, int y, int z, int side) {
    World world = (World)blockAccess;
    int meta = world.func_72805_g(x, y, z);
    return (isPowered(meta) && getDirection(meta) == side) ? 15 : 0;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (side == 0)
      return this.bottom; 
    if (side == 1)
      return this.top; 
    if (side == 2)
      return this.front; 
    if (side == 3)
      return isPowered(meta) ? this.backOn : this.backOff; 
    if (side != meta)
      return this.field_149761_L; 
    return this.front;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.field_149761_L = iconRegister.func_94245_a(func_149641_N() + "_side");
    this.front = iconRegister.func_94245_a(func_149641_N() + "_front");
    this.backOff = iconRegister.func_94245_a(func_149641_N() + "_back_off");
    this.backOn = iconRegister.func_94245_a(func_149641_N() + "_back_on");
    this.top = iconRegister.func_94245_a(func_149641_N() + "_top");
    this.bottom = iconRegister.func_94245_a(func_149641_N() + "_bottom");
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0, 2); 
    if (l == 1)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 1, 2); 
    if (l == 2)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2); 
    if (l == 3)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2); 
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      int meta = world.func_72805_g(x, y, z);
      if (isPowered(meta))
        func_149674_a(world, x, y, z, world.field_73012_v); 
      startSignal(world, x, y, z);
    } 
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    if (isPowered(meta) && world.func_147477_a(x, y, z, (Block)this))
      updateNeighborsInFront(world, x, y, z); 
  }
  
  private void setPowered(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    world.func_72921_c(x, y, z, meta | 0x4, 2);
  }
  
  private void setNotPowered(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    world.func_72921_c(x, y, z, meta & 0x3, 2);
  }
  
  private boolean isPowered(int meta) {
    return ((meta & 0x4) == 4);
  }
  
  public int getDirection(int meta) {
    return meta & 0x3;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\BlockObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.items.block.ItemBanner;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockBanner extends BlockContainer implements ModBlocks.ISubBlocksBlock, IConfigurable {
  public BlockBanner() {
    super(Material.field_151575_d);
    func_149649_H();
    func_149711_c(1.0F);
    func_149672_a(field_149766_f);
    func_149663_c(Utils.getUnlocalisedName("banner"));
    func_149647_a(Back2Future.enableBanners ? Back2Future.creativeTab : null);
    float f = 0.25F;
    float f1 = 1.0F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    TileEntityBanner tile = (TileEntityBanner)Utils.getTileEntity(world, x, y, z, TileEntityBanner.class);
    if (tile == null)
      return; 
    if (!tile.isStanding) {
      int meta = world.func_72805_g(x, y, z);
      float f = 0.0F;
      float f1 = 0.78125F;
      float f2 = 0.0F;
      float f3 = 1.0F;
      float f4 = 0.125F;
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      if (meta == 2)
        func_149676_a(f2, f, 1.0F - f4, f3, f1, 1.0F); 
      if (meta == 3)
        func_149676_a(f2, f, 0.0F, f3, f1, f4); 
      if (meta == 4)
        func_149676_a(1.0F - f4, f, f2, 1.0F, f1, f3); 
      if (meta == 5)
        func_149676_a(0.0F, f, f2, f4, f1, f3); 
    } else {
      float f = 0.25F;
      float f1 = 1.0F;
      func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    } 
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block neighbour) {
    TileEntityBanner tile = (TileEntityBanner)Utils.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBanner.class);
    if (tile == null)
      return; 
    boolean flag = false;
    if (tile.isStanding) {
      if (!world.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a())
        flag = true; 
    } else {
      int meta = world.func_72805_g(x, y, z);
      flag = true;
      if (meta == 2 && world.func_147439_a(x, y, z + 1).func_149688_o().func_76220_a())
        flag = false; 
      if (meta == 3 && world.func_147439_a(x, y, z - 1).func_149688_o().func_76220_a())
        flag = false; 
      if (meta == 4 && world.func_147439_a(x + 1, y, z).func_149688_o().func_76220_a())
        flag = false; 
      if (meta == 5 && world.func_147439_a(x - 1, y, z).func_149688_o().func_76220_a())
        flag = false; 
    } 
    if (flag) {
      func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147468_f(x, y, z);
    } 
    super.func_149695_a(world, x, y, z, neighbour);
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
    TileEntityBanner banner = (TileEntityBanner)Utils.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBanner.class);
    if (banner != null)
      return banner.createStack(); 
    return super.getPickBlock(target, world, x, y, z, player);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d) {
      ArrayList<ItemStack> drops = getDrops(world, x, y, z, meta, 0);
      if (ForgeEventFactory.fireBlockHarvesting(drops, world, (Block)this, x, y, z, meta, 0, 1.0F, false, player) > 0.0F)
        for (ItemStack stack : drops)
          func_149642_a(world, x, y, z, stack);  
    } 
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    TileEntityBanner banner = (TileEntityBanner)Utils.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBanner.class);
    if (banner != null)
      ret.add(banner.createStack()); 
    return ret;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return Blocks.field_150344_f.func_149733_h(side);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {}
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    func_149719_a((IBlockAccess)world, x, y, z);
    return super.func_149633_g(world, x, y, z);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityBanner();
  }
  
  public int func_149645_b() {
    return -1;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemBanner.class;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableBanners;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
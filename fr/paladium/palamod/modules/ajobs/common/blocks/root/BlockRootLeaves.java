package fr.paladium.palamod.modules.ajobs.common.blocks.root;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.tab.JobsTab;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityRoot;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockRootLeaves extends BlockLeavesBase implements IShearable {
  private IIcon onIcon;
  
  private IIcon offIcon;
  
  private IIcon inventoryIcon;
  
  public BlockRootLeaves(String unlocalizedName) {
    super(Material.field_151584_j, false);
    func_149711_c(0.2F);
    func_149713_g(1);
    func_149672_a(field_149779_h);
    func_149663_c(unlocalizedName);
    func_149658_d("palajobs:" + unlocalizedName);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityRoot))
      return; 
    TileEntityRoot root = (TileEntityRoot)tile;
    Location location = new Location(world, x, (y - 1), z);
    Location minLocation = root.getMinLocation();
    if (minLocation == null)
      return; 
    for (int currentY = (int)location.getY(); currentY > minLocation.getY(); currentY--) {
      Block currentBlock = world.func_147439_a((int)location.getX(), currentY, (int)location.getZ());
      if (currentBlock.equals(BlocksRegistry.ROOT_LOG))
        world.func_147468_f((int)location.getX(), currentY, (int)location.getZ()); 
    } 
    super.func_149749_a(world, x, y, z, block, meta);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.inventoryIcon = iconRegister.func_94245_a(func_149641_N());
    this.onIcon = iconRegister.func_94245_a(func_149641_N() + "_on");
    this.offIcon = iconRegister.func_94245_a(func_149641_N() + "_off");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int dir) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile instanceof TileEntityRoot) {
      TileEntityRoot tileRoot = (TileEntityRoot)tile;
      if (tileRoot.isBlocked())
        return this.offIcon; 
    } 
    return this.onIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.offIcon;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityRoot();
  }
  
  public boolean hasTileEntity(int meta) {
    return true;
  }
  
  public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    ret.add(new ItemStack((Block)this, 1));
    return ret;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\root\BlockRootLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
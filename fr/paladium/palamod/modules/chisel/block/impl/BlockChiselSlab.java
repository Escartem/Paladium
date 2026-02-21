package fr.paladium.palamod.modules.chisel.block.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.chisel.block.BlockChisel;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChiselSlab extends BlockChisel {
  private Block modelBlock;
  
  private int startingMeta;
  
  private int totalSize;
  
  public BlockChiselSlab(Material material) {
    super(material);
  }
  
  public BlockChiselSlab(Material material, Block model, int meta, int totalSize) {
    super(material);
    this.modelBlock = model;
    this.startingMeta = meta;
    this.totalSize = totalSize;
  }
  
  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List arraylist, Entity entity) {
    func_149719_a((IBlockAccess)world, x, y, z);
    super.func_149743_a(world, x, y, z, axisalignedbb, arraylist, entity);
  }
  
  public void func_149683_g() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z) / 8;
    float minY = (meta == 1) ? 0.5F : 0.0F;
    float maxY = (meta == 1) ? 1.0F : 0.5F;
    func_149676_a(0.0F, minY, 0.0F, 1.0F, maxY, 1.0F);
  }
  
  public int func_149660_a(World par1World, int blockX, int blockY, int blockZ, int side, float clickX, float clickY, float clickZ, int metadata) {
    if (side == 1)
      return metadata; 
    if (side == 0 || clickY >= 0.5F)
      return metadata | 0x8; 
    return metadata;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {}
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    meta = meta % 8 + this.startingMeta;
    return this.modelBlock.func_149691_a(side, meta);
  }
  
  public void func_149666_a(Item id, CreativeTabs tab, List<ItemStack> list) {
    for (int iter = 0; iter < this.totalSize; iter++)
      list.add(new ItemStack(id, 1, iter)); 
  }
  
  public int func_149692_a(int meta) {
    return meta % 8;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
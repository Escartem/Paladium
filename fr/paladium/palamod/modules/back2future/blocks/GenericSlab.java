package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.items.block.ItemGenericSlab;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class GenericSlab extends Block implements ModBlocks.ISubBlocksBlock, IConfigurable {
  private final Block base;
  
  public GenericSlab(Material material, Block base) {
    super(material);
    this.base = base;
    func_149713_g(0);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    int meta = world.func_72805_g(x, y, z);
    return (meta == 2 || (side == ForgeDirection.UP && meta == 1) || (side == ForgeDirection.DOWN && meta == 0));
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if (meta == 0) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    } else if (meta == 1) {
      func_149676_a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    } else if (meta == 2) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    } 
  }
  
  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity) {
    func_149719_a((IBlockAccess)world, x, y, z);
    super.func_149743_a(world, x, y, z, box, list, entity);
  }
  
  public void func_149683_g() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
  }
  
  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    if (side == 1)
      return 0; 
    if (side == 0 || hitY > 0.5D)
      return 1; 
    return 0;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    int size = 1;
    if (meta == 2)
      size = 2; 
    ret.add(new ItemStack(this, size));
    return ret;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.base.func_149691_a(side, 0);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {}
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
    return true;
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemGenericSlab.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\GenericSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
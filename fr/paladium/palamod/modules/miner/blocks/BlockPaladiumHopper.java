package fr.paladium.palamod.modules.miner.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.miner.proxy.ClientProxy;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityPaladiumHopper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPaladiumHopper extends BlockContainer implements ITooltipWiki {
  @SideOnly(Side.CLIENT)
  public static IIcon field_149921_b;
  
  @SideOnly(Side.CLIENT)
  public IIcon field_149923_M;
  
  @SideOnly(Side.CLIENT)
  public static IIcon field_149924_N;
  
  public BlockPaladiumHopper() {
    super(Material.field_151573_f);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149663_c("paladium_hopper");
    func_149658_d("palamod:paladium_hopper");
    func_149711_c(5.0F);
  }
  
  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    float f = 0.125F;
    func_149676_a(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
    int opposite = Facing.field_71588_a[p_149660_5_];
    if (opposite == 1)
      opposite = 0; 
    return opposite;
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityPaladiumHopper();
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    func_149919_e(world, x, y, z);
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    func_149919_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
  }
  
  private void func_149919_e(World p_149919_1_, int p_149919_2_, int p_149919_3_, int p_149919_4_) {
    int l = p_149919_1_.func_72805_g(p_149919_2_, p_149919_3_, p_149919_4_);
    int i1 = BlockHopper.func_149918_b(l);
    boolean flag = !p_149919_1_.func_72864_z(p_149919_2_, p_149919_3_, p_149919_4_);
    boolean flag1 = BlockHopper.func_149917_c(l);
    if (flag != flag1)
      p_149919_1_.func_72921_c(p_149919_2_, p_149919_3_, p_149919_4_, i1 | (flag ? 0 : 8), 4); 
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderPaladiumHopper;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? this.field_149923_M : field_149921_b;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    field_149921_b = p_149651_1_.func_94245_a("palamod:paladium_hopper_outside");
    this.field_149923_M = p_149651_1_.func_94245_a("palamod:paladium_hopper_top");
    field_149924_N = p_149651_1_.func_94245_a("palamod:paladium_hopper_inside");
  }
  
  @SideOnly(Side.CLIENT)
  public static IIcon getHopperIcon(String p_149916_0_) {
    return "paladium_hopper_outside".equals(p_149916_0_) ? field_149921_b : ("paladium_hopper_inside".equals(p_149916_0_) ? field_149924_N : null);
  }
  
  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "palamod:paladium_hopper";
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockPaladiumHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
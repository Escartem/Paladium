package fr.paladium.palamod.modules.enderchest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseBlockContainer;
import fr.paladium.palamod.modules.enderchest.tileentity.TileEntityPaladiumEnderChest;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPaladiumEnderChest extends BaseBlockContainer {
  protected String field_149770_b;
  
  public BlockPaladiumEnderChest(String unlocalizedName) {
    super(Material.field_151573_f, 12.0F, "paladium/paladium_block");
    this.field_149770_b = unlocalizedName;
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c(this.field_149770_b);
    func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 2);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return 22;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(Blocks.field_150343_Z);
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 8;
  }
  
  protected boolean func_149700_E() {
    return true;
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    byte b0 = 0;
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      b0 = 2; 
    if (l == 1)
      b0 = 5; 
    if (l == 2)
      b0 = 3; 
    if (l == 3)
      b0 = 4; 
    p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, b0, 2);
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    InventoryEnderChest inventoryenderchest = p_149727_5_.func_71005_bN();
    TileEntityPaladiumEnderChest tileentityenderchest = (TileEntityPaladiumEnderChest)p_149727_1_.func_147438_o(p_149727_2_, p_149727_3_, p_149727_4_);
    if (inventoryenderchest == null || tileentityenderchest == null || p_149727_1_.func_147439_a(p_149727_2_, p_149727_3_ + 1, p_149727_4_).func_149721_r() || p_149727_1_.field_72995_K)
      return true; 
    inventoryenderchest.func_146031_a((TileEntityEnderChest)tileentityenderchest);
    p_149727_5_.func_71007_a((IInventory)inventoryenderchest);
    return true;
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityPaladiumEnderChest();
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
    for (int l = 0; l < 3; l++) {
      double d1 = (p_149734_3_ + p_149734_5_.nextFloat());
      double d3 = 0.0D;
      double d4 = 0.0D;
      double d5 = 0.0D;
      int i1 = p_149734_5_.nextInt(2) * 2 - 1;
      int j1 = p_149734_5_.nextInt(2) * 2 - 1;
      d3 = (p_149734_5_.nextFloat() - 0.5D) * 0.125D;
      d4 = (p_149734_5_.nextFloat() - 0.5D) * 0.125D;
      d5 = (p_149734_5_.nextFloat() - 0.5D) * 0.125D;
      double d2 = p_149734_4_ + 0.5D + 0.25D * j1;
      d5 = (p_149734_5_.nextFloat() * 1.0F * j1);
      double d0 = p_149734_2_ + 0.5D + 0.25D * i1;
      d3 = (p_149734_5_.nextFloat() * 1.0F * i1);
      p_149734_1_.func_72869_a("portal", d0, d1, d2, d3, d4, d5);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149761_L = p_149651_1_.func_94245_a("obsidian");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\block\BlockPaladiumEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
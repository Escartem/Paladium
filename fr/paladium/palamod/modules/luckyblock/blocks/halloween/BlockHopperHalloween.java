package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHopperHalloween;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopperHalloween extends BlockHopper {
  @SideOnly(Side.CLIENT)
  public static IIcon top;
  
  @SideOnly(Side.CLIENT)
  public static IIcon outside;
  
  @SideOnly(Side.CLIENT)
  public static IIcon inside;
  
  public BlockHopperHalloween() {
    func_149722_s();
    func_149752_b(6.0E7F);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("hopperhalloween");
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this;
    outside = p_149651_1_.func_94245_a("palamod:trickorthreat/hopper_outside");
    this;
    top = p_149651_1_.func_94245_a("palamod:trickorthreat/hopper_top");
    this;
    inside = p_149651_1_.func_94245_a("palamod:trickorthreat/hopper_inside");
  }
  
  public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public static IIcon getHopperIcon(String p_149916_0_) {
    return p_149916_0_.equals("hopper_outside") ? outside : (
      p_149916_0_.equals("hopper_inside") ? inside : null);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityHopperHalloween();
  }
  
  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "hopper";
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderHopperHalloween;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149743_a(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
    super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    float f = 0.125F;
    func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
    super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
    super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
    super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {}
  
  public IIcon func_149691_a(int par1, int par2) {
    this;
    this;
    return (par1 == 1) ? top : inside;
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    return false;
  }
  
  public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockHopperHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
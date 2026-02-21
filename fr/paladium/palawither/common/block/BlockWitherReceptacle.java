package fr.paladium.palawither.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawither.client.render.block.BlockWitherReceptacleRenderer;
import fr.paladium.palawither.common.CommonProxy;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitherReceptacle extends Block implements ITileEntityProvider {
  public BlockWitherReceptacle() {
    super(Material.field_151576_e);
    func_149752_b(5000.0F);
    func_149672_a(Block.field_149780_i);
    func_149663_c("wither_receptacle");
    func_149658_d("palawither:wither_receptacle/off");
    func_149647_a(CommonProxy.CREATIVE_TAB);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.81F, 1.0F);
    func_149715_a(1.0F);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    world.func_72921_c(x, y, z, MathHelper.func_76128_c(entity.field_70177_z * 4.0D / 360.0D + 0.5D) & 0x3, 2);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {
      TileEntity te = world.func_147438_o(x, y, z);
      if (!(te instanceof TileEntityWitherReceptacle))
        return false; 
      TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)te;
      if (player.field_71075_bZ.field_75098_d && player.func_70093_af() && player.func_70694_bm() == null && receptacle.getCooldown() > 0 && receptacle.isActive() && receptacle.isDecreasingCooldown()) {
        receptacle.setCooldown(receptacle.getCooldown() / 2);
        return true;
      } 
      if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof fr.paladium.palawither.common.item.ItemWitherGem) {
        receptacle.set(player, player.func_70694_bm());
        return true;
      } 
    } 
    return super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity te = world.func_147438_o(x, y, z);
    if (!(te instanceof TileEntityWitherReceptacle) || player.field_71075_bZ.field_75098_d) {
      world.func_147480_a(x, y, z, true);
      return;
    } 
    TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)te;
    if (!receptacle.isActive())
      return; 
    receptacle.damage(player);
  }
  
  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
    if (!player.field_71075_bZ.field_75098_d || world.field_72995_K) {
      TileEntity te = world.func_147438_o(x, y, z);
      if (!(te instanceof TileEntityWitherReceptacle))
        return false; 
      TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)te;
      if (receptacle.isActive())
        return false; 
    } 
    return super.removedByPlayer(world, player, x, y, z, willHarvest);
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    if (entity.field_70170_p.field_72995_K)
      return false; 
    TileEntity te = world.func_147438_o(x, y, z);
    if (!(te instanceof TileEntityWitherReceptacle))
      return true; 
    TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)te;
    if (!receptacle.isActive())
      return true; 
    receptacle.damage(1500.0D);
    return false;
  }
  
  public float func_149712_f(World world, int x, int y, int z) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (!(te instanceof TileEntityWitherReceptacle))
      return -1.0F; 
    return ((TileEntityWitherReceptacle)te).isActive() ? -1.0F : 5.0F;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityWitherReceptacle();
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return BlockWitherReceptacleRenderer.RENDER_ID;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149637_q() {
    return false;
  }
  
  public boolean func_149721_r() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\block\BlockWitherReceptacle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
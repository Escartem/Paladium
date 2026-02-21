package fr.paladium.palawarzoneevent.module.warzone.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawarzoneevent.module.warzone.client.renderer.TileEntityWarzoneSignRenderer;
import fr.paladium.palawarzoneevent.module.warzone.client.ui.UIWarzone;
import fr.paladium.palawarzoneevent.module.warzone.common.block.tileentity.TileEntityWarzoneSign;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWarzoneSign extends Block implements ITileEntityProvider {
  protected BlockWarzoneSign(CreativeTabs creativeTab) {
    super(Material.field_151575_d);
    func_149663_c("warzone_sign");
    func_149711_c(-1.0F);
    func_149752_b(6000000.0F);
    func_149658_d("palawarzoneevent:transparent");
    func_149647_a(creativeTab);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile instanceof TileEntityWarzoneSign) {
      TileEntityWarzoneSign sign = (TileEntityWarzoneSign)tile;
      int rotation = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      sign.setRotation(rotation * -90.0F + 360.0F);
      sign.func_70296_d();
      world.func_147471_g(x, y, z);
    } 
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (world.field_72995_K)
      return true; 
    ZUIServer.open(UIWarzone.class, (EntityPlayerMP)player);
    return true;
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityWarzoneSign();
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return TileEntityWarzoneSignRenderer.RENDER_ID;
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


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\block\BlockWarzoneSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
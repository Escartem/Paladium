package fr.paladium.palaautomation.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.client.ClientProxy;
import fr.paladium.palaautomation.common.handler.ReceiverGuiHandler;
import fr.paladium.palaautomation.common.tab.AutomationTab;
import fr.paladium.palaautomation.common.tile.impl.TileEntityReceiver;
import fr.paladium.palaautomation.common.util.PipeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockReceiver extends Block implements ITileEntityProvider {
  public static final String NAME = "receiver";
  
  public BlockReceiver() {
    super(Material.field_76233_E);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)AutomationTab.getInstance());
    func_149663_c("receiver");
    func_149658_d("palaautomation:receiver");
    func_149711_c(1.5F);
    func_149752_b(10.0F);
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    super.func_149699_a(world, x, y, z, player);
    if (world.field_72995_K)
      return; 
    if (!player.func_70093_af())
      return; 
    PipeUtils.withdrawItem(player, world, x, y, z);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    if (PipeUtils.tryRotate((EntityPlayerMP)player, world, x, y, z) != null)
      return true; 
    PipeUtils.debugPipeMachine((ICommandSender)player, world, x, y, z);
    ReceiverGuiHandler.open((EntityPlayerMP)player, x, y, z);
    return true;
  }
  
  private TileEntityReceiver getTileEntityReceiver(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity instanceof TileEntityReceiver)
      return (TileEntityReceiver)tileEntity; 
    return null;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    PipeUtils.onBreakPipeMachine(world, x, y, z, block, metadata);
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityReceiver();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderReceiverId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\block\BlockReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palaautomation.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.client.ClientProxy;
import fr.paladium.palaautomation.common.handler.GiverGuiHandler;
import fr.paladium.palaautomation.common.tab.AutomationTab;
import fr.paladium.palaautomation.common.tile.impl.TileEntityGiver;
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

public class BlockGiver extends Block implements ITileEntityProvider {
  public static final String NAME = "giver";
  
  public BlockGiver() {
    super(Material.field_76233_E);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)AutomationTab.getInstance());
    func_149663_c("giver");
    func_149658_d("palaautomation:giver");
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
    GiverGuiHandler.open((EntityPlayerMP)player, x, y, z);
    return true;
  }
  
  private TileEntityGiver getTileEntityGiver(World world, int x, int y, int z) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityGiver))
      return null; 
    return (TileEntityGiver)tile;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    PipeUtils.onBreakPipeMachine(world, x, y, z, block, metadata);
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityGiver();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderGiverId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\block\BlockGiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCOpenSolarTestUIPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityTelescope;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockTelescope extends Block implements ITileEntityProvider {
  private static final String NAME = "telescope";
  
  public BlockTelescope() {
    super(Material.field_151575_d);
    func_149663_c("telescope");
    func_149658_d("palamod:telescope");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityTelescope))
      return true; 
    TileEntityTelescope telescope = (TileEntityTelescope)tile;
    SCOpenSolarTestUIPacket sCOpenSolarTestUIPacket = new SCOpenSolarTestUIPacket(telescope.field_145851_c, telescope.field_145848_d, telescope.field_145849_e);
    CommonMarch.getInstance().getNetwork().sendTo((IMessage)sCOpenSolarTestUIPacket, (EntityPlayerMP)player);
    return true;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityTelescope))
      return; 
    ItemStack stack = new ItemStack(BlocksRegister.TELESCOPE);
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74757_a("rewardTaken", ((TileEntityTelescope)tile).isRewardTaken());
    stack.func_77982_d(compound);
    ItemUtils.spawnItemInWorld(world, x, y, z, stack);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
    NBTTagCompound compound = item.func_77978_p();
    if (compound == null)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityTelescope))
      return; 
    boolean taken = false;
    if (compound.func_74764_b("rewardTaken"))
      taken = compound.func_74767_n("rewardTaken"); 
    ((TileEntityTelescope)tile).setRewardTaken(taken);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityTelescope();
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderTelescope;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\block\BlockTelescope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
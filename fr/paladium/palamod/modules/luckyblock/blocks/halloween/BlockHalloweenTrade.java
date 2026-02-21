package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHalloweenTrade;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHalloweenTrade extends Block {
  private static final String ON_COOLDOWN_MESSAGE = "§cVous devez attendre %s avant de pouvoir réutiliser ce bloc.";
  
  private static final String GIVE_PIECE_MESSAGE = "§aVous avez reçu une récompense, revenez dans §c%s §apour en avoir une autre.";
  
  public BlockHalloweenTrade() {
    super(Material.field_151576_e);
    func_149663_c("halloween_trade");
    func_149658_d("palamod:halloween_trade");
    func_149711_c(5.0F);
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityHalloweenTrade))
      return true; 
    TileEntityHalloweenTrade trade = (TileEntityHalloweenTrade)tile;
    long currentTime = System.currentTimeMillis();
    long nextUsage = trade.getNextUsageMillis();
    if (currentTime < nextUsage) {
      String timeLeft = DurationConverter.fromMillisToString(nextUsage - currentTime);
      MonthlyUtils.sendMessage(player, new String[] { String.format("§cVous devez attendre %s avant de pouvoir réutiliser ce bloc.", new Object[] { timeLeft }) });
      return true;
    } 
    ItemStack stack = new ItemStack(Items.field_151158_bO, 64);
    ItemUtils.spawnItemInWorld(world, x, y + 0.5D, z, stack);
    trade.setNextUsageMillis(currentTime + TileEntityHalloweenTrade.USAGE_COOLDOWN);
    MonthlyUtils.sendMessage(player, new String[] { String.format("§aVous avez reçu une récompense, revenez dans §c%s §apour en avoir une autre.", new Object[] { DurationConverter.fromMillisToString(TileEntityHalloweenTrade.USAGE_COOLDOWN) }) });
    return true;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderHalloweenTrade;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityHalloweenTrade();
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityHalloweenTrade))
      return; 
    ItemStack stack = createBlockWithTimeStamp(((TileEntityHalloweenTrade)tile).getNextUsageMillis());
    ItemUtils.spawnItemInWorld(world, x, y, z, stack);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
    NBTTagCompound compound = item.func_77978_p();
    if (compound == null)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityHalloweenTrade))
      return; 
    long nextUsage = 0L;
    if (compound.func_74764_b("nextUsageMillis"))
      nextUsage = compound.func_74763_f("nextUsageMillis"); 
    ((TileEntityHalloweenTrade)tile).setNextUsageMillis(nextUsage);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public static ItemStack createBlockWithTimeStamp(long nextUsageMillis) {
    ItemStack stack = new ItemStack(BlocksRegister.HALLOWEEN_TRADE);
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74772_a("nextUsageMillis", nextUsageMillis);
    stack.func_77982_d(compound);
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
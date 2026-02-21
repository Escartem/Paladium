package fr.paladium.palamod.modules.luckyblock.blocks.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAlarmClock extends Block implements ITooltipInformations {
  public BlockAlarmClock() {
    super(Material.field_76233_E);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("alarm_clock");
    func_149658_d("palamod:alarm_clock");
    func_149711_c(1.0F);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    if (!world.field_72995_K) {
      TileEntityAlarmClock tileEntityAlarmClock = (TileEntityAlarmClock)world.func_147438_o(x, y, z);
      if (tileEntityAlarmClock != null) {
        if (tileEntityAlarmClock.getOwner().equals(FastUUID.toString((Entity)player))) {
          PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_ALARMCLOCK, true, x, y, z), (EntityPlayerMP)player);
        } else {
          PlayerUtils.sendMessage(player, "Cette alarme ne vous appartient pas.");
        } 
        return true;
      } 
    } 
    return false;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityAlarmClock tile = (TileEntityAlarmClock)world.func_147438_o(x, y, z);
    if (tile != null)
      tile.setOwner(FastUUID.toString((Entity)player)); 
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    super.func_149749_a(world, x, y, z, block, metadata);
    world.func_147459_d(x, y, z, this);
  }
  
  public boolean func_149744_f() {
    return true;
  }
  
  public int func_149709_b(IBlockAccess blockAccess, int x, int y, int z, int side) {
    TileEntityAlarmClock tileEntityAlarmClock = (TileEntityAlarmClock)blockAccess.func_147438_o(x, y, z);
    if (tileEntityAlarmClock != null && tileEntityAlarmClock.isRedstoneActive())
      return 15; 
    return 0;
  }
  
  public void notifyNearbyBlocks(World world, int x, int y, int z) {
    world.func_147459_d(x, y - 1, z, this);
    world.func_147459_d(x, y + 1, z, this);
    world.func_147459_d(x - 1, y, z, this);
    world.func_147459_d(x + 1, y, z, this);
    world.func_147459_d(x, y, y - 1, this);
    world.func_147459_d(x, y, y + 1, this);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderAlarmClock;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityAlarmClock();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Objet pouvant être posé.", "Peut être configuré pour sonner à une heure prédéfinie par la personne qui le pose." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\june\BlockAlarmClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
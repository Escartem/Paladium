package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.VentilatorDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityVentilator;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVentilator extends Block implements ITooltipInformations {
  public static final String DESCRIPTION = "Fait reculer le joueur à une vitesse de 1 blocs par seconde. Peut être activé et désactivé avec un clic droit.";
  
  public static final String NAME = "ventilator";
  
  private static final String CHANGE_MODE_MESSAGE = "&eVous avez changé le mode pour&7: %s&e.";
  
  private static final String NOT_OWNER_MESSAGE = "&cVous n'êtes pas le propriétaire de ce ventilateur.";
  
  private static final String ACTIVE_NAME = "&aVentilateur activé";
  
  private static final String DESACTIVE_NAME = "&cVentilateur désactivé";
  
  private static final String CHANGE_DIRECTION_MESSAGE = "&eVous avez changé la direction du ventilateur.";
  
  private IIcon[] icons = new IIcon[3];
  
  public BlockVentilator() {
    super(Material.field_76233_E);
    func_149663_c("ventilator");
    func_149658_d("palamod:ventilator");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityVentilator))
      return false; 
    TileEntityVentilator ventilator = (TileEntityVentilator)tile;
    if (ventilator.getOwnerId() == null) {
      ventilator.setOwnerId(player.func_110124_au());
    } else if (!ventilator.getOwnerId().equals(player.func_110124_au())) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'êtes pas le propriétaire de ce ventilateur." });
      return true;
    } 
    if (!player.func_70093_af()) {
      boolean newStatus = !ventilator.isActive();
      String message = newStatus ? "&aVentilateur activé" : "&cVentilateur désactivé";
      ventilator.setActive(newStatus);
      MonthlyUtils.sendMessage(player, new String[] { String.format("&eVous avez changé le mode pour&7: %s&e.", new Object[] { message }) });
      return true;
    } 
    rotate((EntityPlayerMP)player, world, x, y, z, ventilator);
    MonthlyUtils.sendMessage(player, new String[] { "&eVous avez changé la direction du ventilateur." });
    return true;
  }
  
  public void rotate(EntityPlayerMP player, World world, int x, int y, int z, TileEntityVentilator ventilator) {
    if (ventilator.getDirection() == VentilatorDirection.NORTH) {
      rotate(player, world, x, y, z, ventilator, VentilatorDirection.EAST);
    } else if (ventilator.getDirection() == VentilatorDirection.EAST) {
      rotate(player, world, x, y, z, ventilator, VentilatorDirection.SOUTH);
    } else if (ventilator.getDirection() == VentilatorDirection.SOUTH) {
      rotate(player, world, x, y, z, ventilator, VentilatorDirection.WEST);
    } else if (ventilator.getDirection() == VentilatorDirection.WEST) {
      rotate(player, world, x, y, z, ventilator, VentilatorDirection.NORTH);
    } 
  }
  
  public boolean changeStatus(World world, int x, int y, int z, TileEntityVentilator ventilator) {
    boolean ret = !ventilator.isActive();
    ventilator.setActive(ret);
    return ret;
  }
  
  public void rotate(EntityPlayerMP player, World world, int x, int y, int z, TileEntityVentilator ventilator, VentilatorDirection direction) {
    ventilator.setDirection(direction);
    ventilator.updateCuboid();
    world.func_147471_g(x, y, z);
    updateClientSide(player, ventilator);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityVentilator))
      return; 
    VentilatorDirection direction = VentilatorDirection.fromMetadata(l);
    ((TileEntityVentilator)tile).setDirection(direction);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_front");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[2] = register.func_94245_a(func_149641_N() + "_side");
    this.field_149761_L = this.icons[0];
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    TileEntityVentilator ventilator = (TileEntityVentilator)ba.func_147438_o(x, y, z);
    if (ventilator == null || ventilator.getDirection() == null)
      return this.icons[0]; 
    VentilatorDirection direction = ventilator.getDirection();
    if (direction == VentilatorDirection.NORTH)
      return (dir == 2) ? this.icons[0] : this.icons[2]; 
    if (direction == VentilatorDirection.EAST)
      return (dir == 5) ? this.icons[0] : this.icons[1]; 
    if (direction == VentilatorDirection.SOUTH)
      return (dir == 3) ? this.icons[0] : this.icons[1]; 
    if (direction == VentilatorDirection.WEST)
      return (dir == 4) ? this.icons[0] : this.icons[1]; 
    return this.icons[0];
  }
  
  public void updateClientSide(EntityPlayerMP player, TileEntityVentilator ventilator) {
    Packet packet = ventilator.func_145844_m();
    player.field_71135_a.func_147359_a(packet);
    List<EntityPlayerMP> players = player.field_70170_p.func_72872_a(EntityPlayerMP.class, 
        
        AxisAlignedBB.func_72330_a(player.field_70165_t - 32.0D, player.field_70163_u - 32.0D, player.field_70161_v - 32.0D, player.field_70165_t + 32.0D, player.field_70163_u + 32.0D, player.field_70161_v + 32.0D));
    players.forEach(target -> target.field_71135_a.func_147359_a(packet));
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityVentilator();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Fait reculer le joueur à une vitesse de 1 blocs par seconde. Peut être activé et désactivé avec un clic droit.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\blocks\BlockVentilator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
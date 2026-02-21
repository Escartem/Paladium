package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityPrimaryCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPrimaryCrabEgg extends Block implements ITooltipInformations {
  public static final String DESCRIPTION = "Couve cet œuf pour qu’il éclose !";
  
  public static final String NAME = "primary_crab_egg";
  
  private static final String START_MESSAGE = "&eVous avez commencé à couver l'oeuf de crabe, cliquez dessus pour savoir le temps restant !";
  
  private static final String STATUS_MESSAGE = "&eIl reste &6%s &eavant que l'oeuf n'éclose !";
  
  public BlockPrimaryCrabEgg() {
    super(Material.field_76233_E);
    func_149663_c("primary_crab_egg");
    func_149658_d("palamod:primary_crab_egg");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityPrimaryCrabEgg))
      return true; 
    TileEntityPrimaryCrabEgg tileEntityPrimaryCrabEgg = (TileEntityPrimaryCrabEgg)tileEntity;
    long remaining = tileEntityPrimaryCrabEgg.getRemainingTime();
    MonthlyUtils.sendMessage(player, new String[] { String.format("&eIl reste &6%s &eavant que l'oeuf n'éclose !", new Object[] { DurationConverter.fromMillisToString(remaining) }) });
    return true;
  }
  
  public void func_149724_b(World world, int x, int y, int z, Entity entity) {
    if (world.field_72995_K)
      return; 
    if (!(entity instanceof EntityPlayer))
      return; 
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityPrimaryCrabEgg))
      return; 
    TileEntityPrimaryCrabEgg tileEntityPrimaryCrabEgg = (TileEntityPrimaryCrabEgg)tileEntity;
    if (tileEntityPrimaryCrabEgg.getOwnerId() != null)
      return; 
    tileEntityPrimaryCrabEgg.setOwnerId(entity.func_110124_au());
    MonthlyUtils.sendMessage((EntityPlayer)entity, new String[] { "&eVous avez commencé à couver l'oeuf de crabe, cliquez dessus pour savoir le temps restant !" });
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityPrimaryCrabEgg();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Couve cet œuf pour qu’il éclose !");
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderPrimaryCrabEgg;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\blocks\BlockPrimaryCrabEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
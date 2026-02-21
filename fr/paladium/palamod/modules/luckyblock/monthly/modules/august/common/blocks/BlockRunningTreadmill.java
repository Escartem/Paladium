package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.SummerBodyUI;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerBodyDilemmaEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityTreadmill;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRunningTreadmill extends Block implements ITooltipInformations {
  public static final String DESCRIPTION = "Pose cet objet et monte dessus pour découvrir une épreuve.Réussi-la pour débloquer ton summer body !";
  
  public static final String NAME = "running_treadmill";
  
  public BlockRunningTreadmill() {
    super(Material.field_76233_E);
    func_149663_c("running_treadmill");
    func_149658_d("palamod:running_treadmill");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    return true;
  }
  
  public void func_149724_b(World world, int x, int y, int z, Entity entity) {
    if (!world.field_72995_K)
      return; 
    openUI(x, y, z);
  }
  
  @SideOnly(Side.CLIENT)
  public void openUI(int x, int y, int z) {
    if ((Minecraft.func_71410_x()).field_71462_r != null)
      return; 
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new SummerBodyUI(new DoubleLocation(x, y, z)));
  }
  
  public void handlePacket(EntityPlayerMP player, int x, int y, int z, boolean success) {
    if (success) {
      SummerBodyDilemmaEvent.INSTANCE.onWin(player, x, y, z);
    } else {
      SummerBodyDilemmaEvent.INSTANCE.onLose(player, x, y, z);
    } 
    World world = player.field_70170_p;
    world.func_147468_f(x, y, z);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityTreadmill();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderTreadmill;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Pose cet objet et monte dessus pour découvrir une épreuve.Réussi-la pour débloquer ton summer body !");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\blocks\BlockRunningTreadmill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
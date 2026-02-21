package fr.paladium.palaautomation.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.PalaAutomationMod;
import fr.paladium.palaautomation.client.ui.UICrafter;
import fr.paladium.palaautomation.common.CommonProxy;
import fr.paladium.palaautomation.common.container.ContainerCrafter;
import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CrafterGuiHandler extends GHandler {
  @SideOnly(Side.SERVER)
  public static void open(EntityPlayerMP player, int x, int y, int z) {
    player.openGui(PalaAutomationMod.getInstance(), CommonProxy.getInstance().getCrafterHandlerId(), player.field_70170_p, x, y, z);
    TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityCrafter))
      return; 
    ((TileEntityCrafter)tile).onCrafterOpened(player);
  }
  
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer entityPlayer, World world, int x, int y, int z) {
    if (!world.func_72899_e(x, y, z))
      return null; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityCrafter))
      return null; 
    return (Container)new ContainerCrafter((TileEntityCrafter)tile, entityPlayer);
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer entityPlayer, World world, int x, int y, int z) {
    return (GuiScreen)ZUI.open((UI)new UICrafter((TileEntityCrafter)world.func_147438_o(x, y, z)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\handler\CrafterGuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
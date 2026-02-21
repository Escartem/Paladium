package fr.paladium.palaspawner.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.palaspawner.SpawnerMod;
import fr.paladium.palaspawner.client.ui.UISpawnerController;
import fr.paladium.palaspawner.common.SpawnerCommonProxy;
import fr.paladium.palaspawner.common.container.ContainerSpawnerController;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpawnerControllerGuiHandler extends GHandler {
  @SideOnly(Side.SERVER)
  public static void open(EntityPlayerMP player, int x, int y, int z) {
    player.openGui(SpawnerMod.getInstance(), SpawnerCommonProxy.getInstance().getSpawnerGuiHandlerId(), player.field_70170_p, x, y, z);
  }
  
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer entityPlayer, World world, int x, int y, int z) {
    if (!world.func_72899_e(x, y, z))
      return null; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntitySpawnerController))
      return null; 
    return (Container)new ContainerSpawnerController((TileEntitySpawnerController)tile, entityPlayer);
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer entityPlayer, World world, int x, int y, int z) {
    return (GuiScreen)ZUI.open((UI)new UISpawnerController((TileEntitySpawnerController)world.func_147438_o(x, y, z)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\handler\SpawnerControllerGuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
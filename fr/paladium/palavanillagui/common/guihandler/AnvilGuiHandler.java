package fr.paladium.palavanillagui.common.guihandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.palavanillagui.client.ui.anvil.UIAnvil;
import fr.paladium.palavanillagui.common.container.ContainerAnvil;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class AnvilGuiHandler extends GHandler {
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (GuiScreen)ZUI.open((UI)new UIAnvil(new ContainerAnvil(player.field_71071_by, world, x, y, z)));
  }
  
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (Container)new ContainerAnvil(player.field_71071_by, world, x, y, z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\guihandler\AnvilGuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
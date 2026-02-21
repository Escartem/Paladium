package fr.paladium.palacommunityevent.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palacommunityevent.client.gui.advent.UIAdventCalendar;
import fr.paladium.palacommunityevent.common.handler.utils.Handler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class AdventCalendarHandler extends Handler {
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (GuiScreen)new UIAdventCalendar();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\handler\AdventCalendarHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
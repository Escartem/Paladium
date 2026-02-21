package fr.paladium.palatrade.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.PalaTrade;
import fr.paladium.palatrade.client.ClientProxy;
import fr.paladium.palatrade.client.gui.UITrade;
import fr.paladium.palatrade.common.container.ContainerTrade;
import fr.paladium.palatrade.common.container.inventory.InventoryTrade;
import fr.paladium.palatrade.common.handler.utils.Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class GuiTradeHandler extends Handler {
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (Container)new ContainerTrade((IInventory)player.field_71071_by, new InventoryTrade());
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (GuiScreen)new UITrade((((ClientProxy)PalaTrade.proxy).getTarget() == null) ? "loading..." : ((ClientProxy)PalaTrade.proxy).getTarget(), (IInventory)(Minecraft.func_71410_x()).field_71439_g.field_71071_by, new InventoryTrade());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\handler\GuiTradeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
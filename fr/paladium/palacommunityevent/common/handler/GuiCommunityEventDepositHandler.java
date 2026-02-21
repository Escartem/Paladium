package fr.paladium.palacommunityevent.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palacommunityevent.client.gui.container.ContainerCommunityEvent;
import fr.paladium.palacommunityevent.client.gui.container.UICommunityEventContainer;
import fr.paladium.palacommunityevent.common.handler.utils.Handler;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class GuiCommunityEventDepositHandler extends Handler {
  private PalaCommunityEventManager manager = PalaCommunityEventManager.getInstance();
  
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    String requestedEvent = (String)this.manager.getCommunityEventGUIBrige().get(player.func_110124_au());
    CommunityEvent communityEvent = this.manager.getCommunityEventById(requestedEvent);
    return (Container)new ContainerCommunityEvent(player, communityEvent);
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (GuiScreen)new UICommunityEventContainer();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\handler\GuiCommunityEventDepositHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.client.gui.UIAsk;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import io.netty.buffer.ByteBuf;

@Packet(side = Side.CLIENT)
public class PacketOpenAskGUI implements IMessage {
  private boolean closeable;
  
  public PacketOpenAskGUI() {}
  
  public PacketOpenAskGUI(boolean closeable) {
    this.closeable = closeable;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.closeable = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.closeable);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<PacketOpenAskGUI, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketOpenAskGUI message, MessageContext ctx) {
      ZUI.open((UI)new UIAsk(message.closeable));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketOpenAskGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
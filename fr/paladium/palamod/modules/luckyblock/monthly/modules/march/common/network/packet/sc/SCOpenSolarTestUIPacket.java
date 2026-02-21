package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.solar.SolarTestUI;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import io.netty.buffer.ByteBuf;

public class SCOpenSolarTestUIPacket implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  public SCOpenSolarTestUIPacket() {}
  
  public SCOpenSolarTestUIPacket(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCOpenSolarTestUIPacket, IMessage> {
    public IMessage onMessage(SCOpenSolarTestUIPacket message, MessageContext ctx) {
      ZUI.open((UI)new SolarTestUI(message.x, message.y, message.z));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\sc\SCOpenSolarTestUIPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
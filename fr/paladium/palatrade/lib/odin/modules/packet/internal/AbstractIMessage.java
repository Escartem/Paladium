package fr.paladium.palatrade.lib.odin.modules.packet.internal;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;

public class AbstractIMessage implements IMessage {
  private int packetId;
  
  private ForgePacket packet;
  
  public AbstractIMessage() {}
  
  public AbstractIMessage(int packetId) {
    this.packetId = packetId;
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.packetId);
    this.packet.toBytes(buf);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.packet = OdinPacketModule.getInstance().getPacket(buf.readInt());
    this.packet.fromBytes(buf);
  }
  
  public int getPacketId() {
    return this.packetId;
  }
  
  public ForgePacket getPacket() {
    return this.packet;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\packet\internal\AbstractIMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
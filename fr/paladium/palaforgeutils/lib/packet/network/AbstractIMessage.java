package fr.paladium.palaforgeutils.lib.packet.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class AbstractIMessage implements IMessage {
  private String packetName;
  
  private ForgePacket packet;
  
  public void toBytes(ByteBuf buf) {}
  
  public void fromBytes(ByteBuf buf) {
    this.packet = PacketUtils.getPacket(ByteBufUtils.readUTF8String(buf));
    if (this.packet == null)
      return; 
    this.packet.setCallbackUUID((UUID)PacketSerialUtils.read(buf, new Class[0]));
    this.packet.fromBytes(buf);
  }
  
  public String getPacketName() {
    return this.packetName;
  }
  
  public ForgePacket getPacket() {
    return this.packet;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\network\AbstractIMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
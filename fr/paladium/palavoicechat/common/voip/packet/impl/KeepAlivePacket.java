package fr.paladium.palavoicechat.common.voip.packet.impl;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import io.netty.buffer.ByteBuf;

public class KeepAlivePacket implements Packet<KeepAlivePacket> {
  @PacketData
  private String serverName;
  
  public KeepAlivePacket() {}
  
  public KeepAlivePacket(String serverName) {
    this.serverName = serverName;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public KeepAlivePacket fromBytes(ByteBuf buf) {
    return this;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.serverName);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\KeepAlivePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
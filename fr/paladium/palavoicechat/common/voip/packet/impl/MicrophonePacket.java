package fr.paladium.palavoicechat.common.voip.packet.impl;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;

public class MicrophonePacket implements Packet<MicrophonePacket> {
  private byte[] data;
  
  public MicrophonePacket() {}
  
  public byte[] getData() {
    return this.data;
  }
  
  public MicrophonePacket(byte[] data) {
    this.data = data;
  }
  
  public MicrophonePacket fromBytes(ByteBuf buf) {
    MicrophonePacket soundPacket = new MicrophonePacket();
    soundPacket.data = ByteBufUtils.readBytes(buf);
    return soundPacket;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeBytes(buf, this.data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\MicrophonePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
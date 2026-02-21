package fr.paladium.palavoicechat.common.voip.packet.impl;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class SoundPacket implements Packet<SoundPacket> {
  private UUID sender;
  
  private String senderName;
  
  private byte[] data;
  
  public SoundPacket() {}
  
  public UUID getSender() {
    return this.sender;
  }
  
  public String getSenderName() {
    return this.senderName;
  }
  
  public byte[] getData() {
    return this.data;
  }
  
  public SoundPacket(UUID sender, String senderName, byte[] data) {
    this.sender = sender;
    this.senderName = senderName;
    this.data = data;
  }
  
  public SoundPacket fromBytes(ByteBuf buf) {
    SoundPacket soundPacket = new SoundPacket();
    soundPacket.sender = ByteBufUtils.readUUID(buf);
    soundPacket.senderName = ByteBufUtils.readUTF8String(buf);
    soundPacket.data = ByteBufUtils.readBytes(buf);
    return soundPacket;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUUID(buf, this.sender);
    ByteBufUtils.writeUTF8String(buf, this.senderName);
    ByteBufUtils.writeBytes(buf, this.data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\SoundPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palavoicechat.common.voip.packet.impl;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class SendSecretPacket implements Packet<SendSecretPacket> {
  private UUID playerUUID;
  
  private UUID secret;
  
  public SendSecretPacket() {}
  
  public SendSecretPacket(UUID playerUUID, UUID secret) {
    this.playerUUID = playerUUID;
    this.secret = secret;
  }
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public UUID getSecret() {
    return this.secret;
  }
  
  public SendSecretPacket fromBytes(ByteBuf buf) {
    SendSecretPacket secretPacket = new SendSecretPacket();
    secretPacket.playerUUID = ByteBufUtils.readUUID(buf);
    secretPacket.secret = ByteBufUtils.readUUID(buf);
    return secretPacket;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUUID(buf, this.playerUUID);
    ByteBufUtils.writeUUID(buf, this.secret);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\SendSecretPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
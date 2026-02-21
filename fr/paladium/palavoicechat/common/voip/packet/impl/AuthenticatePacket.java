package fr.paladium.palavoicechat.common.voip.packet.impl;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class AuthenticatePacket implements Packet<AuthenticatePacket> {
  private UUID playerUUID;
  
  private UUID secret;
  
  public AuthenticatePacket(UUID playerUUID, UUID secret) {
    this.playerUUID = playerUUID;
    this.secret = secret;
  }
  
  public AuthenticatePacket() {}
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public UUID getSecret() {
    return this.secret;
  }
  
  public AuthenticatePacket fromBytes(ByteBuf buf) {
    AuthenticatePacket authPacket = new AuthenticatePacket();
    authPacket.playerUUID = ByteBufUtils.readUUID(buf);
    authPacket.secret = ByteBufUtils.readUUID(buf);
    return authPacket;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUUID(buf, this.playerUUID);
    ByteBufUtils.writeUUID(buf, this.secret);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\AuthenticatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
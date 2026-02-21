package fr.paladium.palavoicechat.common.voip.packet.impl;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class PlayerDisconnectionPacket implements Packet<PlayerDisconnectionPacket> {
  private UUID playerUUID;
  
  public PlayerDisconnectionPacket(UUID playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public PlayerDisconnectionPacket() {}
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public PlayerDisconnectionPacket fromBytes(ByteBuf buf) {
    PlayerDisconnectionPacket packet = new PlayerDisconnectionPacket();
    packet.playerUUID = ByteBufUtils.readUUID(buf);
    return packet;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUUID(buf, this.playerUUID);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\PlayerDisconnectionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
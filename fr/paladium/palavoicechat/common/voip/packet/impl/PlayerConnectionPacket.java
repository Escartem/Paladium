package fr.paladium.palavoicechat.common.voip.packet.impl;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class PlayerConnectionPacket implements Packet<PlayerConnectionPacket> {
  private UUID playerUUID;
  
  public PlayerConnectionPacket() {}
  
  public PlayerConnectionPacket(UUID playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public PlayerConnectionPacket fromBytes(ByteBuf buf) {
    PlayerConnectionPacket packet = new PlayerConnectionPacket();
    packet.playerUUID = ByteBufUtils.readUUID(buf);
    return packet;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUUID(buf, this.playerUUID);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\PlayerConnectionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
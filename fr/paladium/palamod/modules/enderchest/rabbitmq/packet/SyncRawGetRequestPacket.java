package fr.paladium.palamod.modules.enderchest.rabbitmq.packet;

import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitPacketBuilder;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitQueue;
import java.util.UUID;

public class SyncRawGetRequestPacket extends RabbitPacketBuilder {
  private final UUID playerUuid;
  
  private final String serverName;
  
  public UUID getPlayerUuid() {
    return this.playerUuid;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public SyncRawGetRequestPacket(UUID playerUuid, String serverName) {
    this.playerUuid = playerUuid;
    this.serverName = serverName;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.SIMPLE;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.SYNC_RAW_GET_REQUEST;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\packet\SyncRawGetRequestPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.enderchest.rabbitmq.packet;

import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitPacketBuilder;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitQueue;
import java.util.UUID;

public class SyncRawGetResultPacket extends RabbitPacketBuilder {
  private final UUID playerUuid;
  
  private final String serverName;
  
  private final String data;
  
  public UUID getPlayerUuid() {
    return this.playerUuid;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public String getData() {
    return this.data;
  }
  
  public SyncRawGetResultPacket(UUID playerUuid, String serverName, String data) {
    this.playerUuid = playerUuid;
    this.serverName = serverName;
    this.data = data;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.SIMPLE;
  }
  
  public RabbitQueue getPacketQueue() {
    return null;
  }
  
  public String getQueueName() {
    return RabbitQueue.SYNC_RAW_GET_RESULT.getQueueName() + "." + this.serverName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\packet\SyncRawGetResultPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
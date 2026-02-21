package fr.paladium.palamod.modules.luckyblock.rabbitmq.packet;

import fr.paladium.palamod.util.rabbitmq.RabbitPacketBuilder;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;

public class FoghornMessagePacket extends RabbitPacketBuilder {
  private final String senderUUID;
  
  private final String server;
  
  private final double x;
  
  private final double y;
  
  private final double z;
  
  public FoghornMessagePacket(String senderUUID, String server, double x, double y, double z) {
    this.senderUUID = senderUUID;
    this.server = server;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public String getSenderUUID() {
    return this.senderUUID;
  }
  
  public String getServer() {
    return this.server;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double getZ() {
    return this.z;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.LB_FOGHORN_USE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\rabbitmq\packet\FoghornMessagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palaforgeutils.lib.rabbitmq.packet;

public class RabbitPacket {
  private final String queueName;
  
  private final RabbitPacketType type;
  
  private final String message;
  
  public RabbitPacket(String queueName, RabbitPacketType type, String message) {
    this.queueName = queueName;
    this.type = type;
    this.message = message;
  }
  
  public String getQueueName() {
    return this.queueName;
  }
  
  public RabbitPacketType getType() {
    return this.type;
  }
  
  public String getMessage() {
    return this.message;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\packet\RabbitPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
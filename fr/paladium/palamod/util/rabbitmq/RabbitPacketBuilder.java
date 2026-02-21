package fr.paladium.palamod.util.rabbitmq;

public abstract class RabbitPacketBuilder implements RabbitPacketRoute {
  public String getQueueName() {
    return getPacketQueue().getQueueName();
  }
  
  public void send(RabbitService service) {
    service.sendPacket(getPacketType(), getQueueName(), this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\rabbitmq\RabbitPacketBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
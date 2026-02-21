package fr.paladium.palamod.modules.enderchest.rabbitmq;

public abstract class RabbitPacketBuilder implements RabbitPacketRoute {
  public String getQueueName() {
    return getPacketQueue().getQueueName();
  }
  
  public boolean send(RabbitService service) {
    return service.sendPacket(getPacketType(), getQueueName(), this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\RabbitPacketBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
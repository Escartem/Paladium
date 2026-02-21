package fr.paladium.palaforgeutils.lib.rabbitmq.packet;

import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitService;

public abstract class RabbitPacketBuilder implements RabbitPacketRoute {
  public void send(RabbitService service) {
    service.sendPacket(getPacketType(), service.getQueueName(), this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\packet\RabbitPacketBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
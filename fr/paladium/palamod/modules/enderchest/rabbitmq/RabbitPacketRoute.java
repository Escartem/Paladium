package fr.paladium.palamod.modules.enderchest.rabbitmq;

public interface RabbitPacketRoute {
  RabbitPacketType getPacketType();
  
  RabbitQueue getPacketQueue();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\RabbitPacketRoute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
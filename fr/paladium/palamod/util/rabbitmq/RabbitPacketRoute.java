package fr.paladium.palamod.util.rabbitmq;

public interface RabbitPacketRoute {
  RabbitPacketType getPacketType();
  
  RabbitQueue getPacketQueue();
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\rabbitmq\RabbitPacketRoute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
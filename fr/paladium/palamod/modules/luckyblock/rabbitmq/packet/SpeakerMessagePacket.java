package fr.paladium.palamod.modules.luckyblock.rabbitmq.packet;

import fr.paladium.palamod.util.rabbitmq.RabbitPacketBuilder;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;

public class SpeakerMessagePacket extends RabbitPacketBuilder {
  private final String sender;
  
  private final String senderUUID;
  
  private final String message;
  
  public String getSender() {
    return this.sender;
  }
  
  public String getSenderUUID() {
    return this.senderUUID;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public SpeakerMessagePacket(String sender, String senderUUID, String message) {
    this.sender = sender;
    this.senderUUID = senderUUID;
    this.message = message;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.LB_SPEAKER_MESSAGE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\rabbitmq\packet\SpeakerMessagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.mailbox.rabbitmq.packets;

import fr.paladium.palamod.util.rabbitmq.RabbitPacketBuilder;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;

public class MailboxPersonnalNotificationPacket extends RabbitPacketBuilder {
  private final String sender;
  
  private final String receiver;
  
  private final String object;
  
  public String getSender() {
    return this.sender;
  }
  
  public String getReceiver() {
    return this.receiver;
  }
  
  public String getObject() {
    return this.object;
  }
  
  public MailboxPersonnalNotificationPacket(String sender, String receiver, String object) {
    this.sender = sender;
    this.receiver = receiver;
    this.object = object;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.MAILBOX_PERSONNAL;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\rabbitmq\packets\MailboxPersonnalNotificationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
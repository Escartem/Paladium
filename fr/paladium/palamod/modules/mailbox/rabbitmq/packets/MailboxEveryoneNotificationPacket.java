package fr.paladium.palamod.modules.mailbox.rabbitmq.packets;

import fr.paladium.palamod.util.rabbitmq.RabbitPacketBuilder;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;

public class MailboxEveryoneNotificationPacket extends RabbitPacketBuilder {
  private final String sender;
  
  private final String object;
  
  public String getSender() {
    return this.sender;
  }
  
  public String getObject() {
    return this.object;
  }
  
  public MailboxEveryoneNotificationPacket(String sender, String object) {
    this.sender = sender;
    this.object = object;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.MAILBOX_EVERYONE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\rabbitmq\packets\MailboxEveryoneNotificationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
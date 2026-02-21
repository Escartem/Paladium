package fr.paladium.palamod.modules.mailbox.rabbitmq.listeners;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.mailbox.rabbitmq.packets.MailboxPersonnalNotificationPacket;
import fr.paladium.palamod.util.rabbitmq.RabbitListener;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;
import fr.paladium.palamod.util.rabbitmq.RabbitService;
import java.net.UnknownHostException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class MailboxPersonnalNotificationListener extends RabbitListener<MailboxPersonnalNotificationPacket> {
  public MailboxPersonnalNotificationListener(RabbitService service) throws UnknownHostException {
    super(service, RabbitPacketType.PUBLISH, RabbitQueue.MAILBOX_PERSONNAL.getQueueName(), MailboxPersonnalNotificationPacket.class);
  }
  
  public void onReceive(MailboxPersonnalNotificationPacket packet) {
    if (packet == null)
      return; 
    List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
    for (EntityPlayerMP p : players) {
      if (p.func_70005_c_().equalsIgnoreCase(packet.getReceiver()))
        (new Notification(Notification.NotificationType.INFO, packet.getSender() + " vous a envoyé un mail", "mailbox")).send(p); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\rabbitmq\listeners\MailboxPersonnalNotificationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
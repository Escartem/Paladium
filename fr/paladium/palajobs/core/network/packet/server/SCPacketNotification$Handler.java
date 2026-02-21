package fr.paladium.palajobs.core.network.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.client.notification.JobNotification;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.zephyrui.lib.notification.NotificationOverlay;
import java.util.Optional;

public class Handler implements IMessageHandler<SCPacketNotification, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketNotification message, MessageContext ctx) {
    Optional<AbstractJob> optional = JobsManager.getInstance().getJobByName(message.job);
    if (!optional.isPresent())
      return null; 
    AbstractJob job = optional.get();
    if (SCPacketNotification.access$000() == null) {
      SCPacketNotification.access$002(new JobNotification(job.getType(), message.lvl, message.xp, message.multiplicateur));
      SCPacketNotification.access$000().send();
      return null;
    } 
    Optional<Notification> optionalNotification = NotificationOverlay.getInstance().getSonner().getNotifications().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == SCPacketNotification.access$000().getNotification())).findFirst();
    Optional<Notification> optionalQueuedNotification = NotificationOverlay.getInstance().getSonner().getNotificationQueue().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == SCPacketNotification.access$000().getNotification())).findFirst();
    if (!optionalNotification.isPresent() && !optionalQueuedNotification.isPresent()) {
      SCPacketNotification.access$002(new JobNotification(job.getType(), message.lvl, message.xp, message.multiplicateur));
      SCPacketNotification.access$000().send();
      return null;
    } 
    SCPacketNotification.access$000().update(job.getType(), message.lvl, message.xp, message.multiplicateur);
    NotificationOverlay.getInstance().getSonner().hardFocused(true);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\packet\server\SCPacketNotification$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
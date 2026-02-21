package fr.paladium.palajobs.core.network.packet.server;

import cpw.mods.fml.common.network.ByteBufUtils;
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
import io.netty.buffer.ByteBuf;
import java.util.Optional;

public class SCPacketNotification implements IMessage {
  private static JobNotification notification;
  
  public String job;
  
  public int lvl;
  
  public double xp;
  
  public double multiplicateur;
  
  public SCPacketNotification() {}
  
  public SCPacketNotification(String job, int lvl, double xp, double multiplicateur) {
    this.job = job;
    this.lvl = lvl;
    this.xp = xp;
    this.multiplicateur = multiplicateur;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.job = ByteBufUtils.readUTF8String(buf);
    this.lvl = buf.readInt();
    this.xp = buf.readDouble();
    this.multiplicateur = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.job);
    buf.writeInt(this.lvl);
    buf.writeDouble(this.xp);
    buf.writeDouble(this.multiplicateur);
  }
  
  public static class Handler implements IMessageHandler<SCPacketNotification, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketNotification message, MessageContext ctx) {
      Optional<AbstractJob> optional = JobsManager.getInstance().getJobByName(message.job);
      if (!optional.isPresent())
        return null; 
      AbstractJob job = optional.get();
      if (SCPacketNotification.notification == null) {
        SCPacketNotification.notification = new JobNotification(job.getType(), message.lvl, message.xp, message.multiplicateur);
        SCPacketNotification.notification.send();
        return null;
      } 
      Optional<Notification> optionalNotification = NotificationOverlay.getInstance().getSonner().getNotifications().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == SCPacketNotification.notification.getNotification())).findFirst();
      Optional<Notification> optionalQueuedNotification = NotificationOverlay.getInstance().getSonner().getNotificationQueue().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == SCPacketNotification.notification.getNotification())).findFirst();
      if (!optionalNotification.isPresent() && !optionalQueuedNotification.isPresent()) {
        SCPacketNotification.notification = new JobNotification(job.getType(), message.lvl, message.xp, message.multiplicateur);
        SCPacketNotification.notification.send();
        return null;
      } 
      SCPacketNotification.notification.update(job.getType(), message.lvl, message.xp, message.multiplicateur);
      NotificationOverlay.getInstance().getSonner().hardFocused(true);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\packet\server\SCPacketNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
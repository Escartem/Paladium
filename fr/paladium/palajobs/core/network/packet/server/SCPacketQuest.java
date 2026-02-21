package fr.paladium.palajobs.core.network.packet.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.notification.NotificationOverlay;
import io.netty.buffer.ByteBuf;
import java.util.Optional;

public class SCPacketQuest implements IMessage {
  private static Notification notification;
  
  public String questId;
  
  public int advancement;
  
  public SCPacketQuest() {}
  
  public SCPacketQuest(String questId, int advancement) {
    this.questId = questId;
    this.advancement = advancement;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.questId = ByteBufUtils.readUTF8String(buf);
    this.advancement = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.questId);
    buf.writeInt(this.advancement);
  }
  
  public static class Handler implements IMessageHandler<SCPacketQuest, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketQuest message, MessageContext ctx) {
      Optional<AbstractQuest> optional = JobsManager.getInstance().getQuest(message.questId);
      if (!optional.isPresent())
        return null; 
      AbstractQuest quest = optional.get();
      if (SCPacketQuest.notification == null) {
        SCPacketQuest.notification = new Notification(Notification.NotificationType.INFO, TTT.format(quest.getJob().getName(), new Object[0]) + " - " + message.advancement + " / " + quest.getQuantity(), "jobs");
        SCPacketQuest.notification.send();
        return null;
      } 
      Optional<Notification> optionalNotification = NotificationOverlay.getInstance().getSonner().getNotifications().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == SCPacketQuest.notification)).findFirst();
      if (!optionalNotification.isPresent()) {
        SCPacketQuest.notification = new Notification(Notification.NotificationType.INFO, TTT.format(quest.getJob().getName(), new Object[0]) + " - " + message.advancement + " / " + quest.getQuantity(), "jobs");
        SCPacketQuest.notification.send();
        return null;
      } 
      SCPacketQuest.notification.description(TTT.format(quest.getJob().getName(), new Object[0]) + " - " + message.advancement + " / " + quest.getQuantity());
      SCPacketQuest.notification.setStart(System.currentTimeMillis());
      NotificationOverlay.getInstance().getSonner().hardFocused(true);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\packet\server\SCPacketQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
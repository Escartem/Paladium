package fr.paladium.palajobs.core.network.packet.server;

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
import java.util.Optional;

public class Handler implements IMessageHandler<SCPacketQuest, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketQuest message, MessageContext ctx) {
    Optional<AbstractQuest> optional = JobsManager.getInstance().getQuest(message.questId);
    if (!optional.isPresent())
      return null; 
    AbstractQuest quest = optional.get();
    if (SCPacketQuest.access$000() == null) {
      SCPacketQuest.access$002(new Notification(Notification.NotificationType.INFO, TTT.format(quest.getJob().getName(), new Object[0]) + " - " + message.advancement + " / " + quest.getQuantity(), "jobs"));
      SCPacketQuest.access$000().send();
      return null;
    } 
    Optional<Notification> optionalNotification = NotificationOverlay.getInstance().getSonner().getNotifications().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == SCPacketQuest.access$000())).findFirst();
    if (!optionalNotification.isPresent()) {
      SCPacketQuest.access$002(new Notification(Notification.NotificationType.INFO, TTT.format(quest.getJob().getName(), new Object[0]) + " - " + message.advancement + " / " + quest.getQuantity(), "jobs"));
      SCPacketQuest.access$000().send();
      return null;
    } 
    SCPacketQuest.access$000().description(TTT.format(quest.getJob().getName(), new Object[0]) + " - " + message.advancement + " / " + quest.getQuantity());
    SCPacketQuest.access$000().setStart(System.currentTimeMillis());
    NotificationOverlay.getInstance().getSonner().hardFocused(true);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\packet\server\SCPacketQuest$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
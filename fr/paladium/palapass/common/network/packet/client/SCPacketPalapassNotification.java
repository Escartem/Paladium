package fr.paladium.palapass.common.network.packet.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.zephyrui.lib.notification.NotificationOverlay;
import java.util.Optional;

public class SCPacketPalapassNotification extends ForgePacket {
  private static Notification notification;
  
  @PacketData
  private Notification.NotificationType type;
  
  @PacketData
  private String content;
  
  public SCPacketPalapassNotification() {}
  
  public SCPacketPalapassNotification(Notification.NotificationType type, String content) {
    this.type = type;
    this.content = content;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (notification == null) {
      notification = new Notification(this.type, this.content, "palapass");
      notification.send();
      return;
    } 
    Optional<Notification> optionalNotification = NotificationOverlay.getInstance().getSonner().getNotifications().stream().filter(Notification.class::isInstance).map(Notification.class::cast).filter(n -> (n == notification)).findFirst();
    if (!optionalNotification.isPresent()) {
      notification = new Notification(this.type, this.content, "palapass");
      notification.send();
      return;
    } 
    notification.type(this.type);
    notification.description(this.content);
    notification.setStart(System.currentTimeMillis());
    NotificationOverlay.getInstance().getSonner().hardFocused(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\client\SCPacketPalapassNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
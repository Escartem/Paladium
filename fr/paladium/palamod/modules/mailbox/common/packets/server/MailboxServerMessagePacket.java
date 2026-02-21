package fr.paladium.palamod.modules.mailbox.common.packets.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.dto.UUID;
import fr.paladium.palamod.modules.achievements.types.WriteMailAchievemnt;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.pojo.inputs.SendMail;
import fr.paladium.palamod.modules.mailbox.rabbitmq.packets.MailboxPersonnalNotificationPacket;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import retrofit2.Response;

public class MailboxServerMessagePacket implements IMessage {
  public void setObject(String object) {
    this.object = object;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailboxServerMessagePacket))
      return false; 
    MailboxServerMessagePacket other = (MailboxServerMessagePacket)o;
    if (!other.canEqual(this))
      return false; 
    Object this$object = getObject(), other$object = other.getObject();
    if ((this$object == null) ? (other$object != null) : !this$object.equals(other$object))
      return false; 
    Object this$content = getContent(), other$content = other.getContent();
    if ((this$content == null) ? (other$content != null) : !this$content.equals(other$content))
      return false; 
    Object this$recipient = getRecipient(), other$recipient = other.getRecipient();
    return !((this$recipient == null) ? (other$recipient != null) : !this$recipient.equals(other$recipient));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailboxServerMessagePacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $object = getObject();
    result = result * 59 + (($object == null) ? 43 : $object.hashCode());
    Object $content = getContent();
    result = result * 59 + (($content == null) ? 43 : $content.hashCode());
    Object $recipient = getRecipient();
    return result * 59 + (($recipient == null) ? 43 : $recipient.hashCode());
  }
  
  public String toString() {
    return "MailboxServerMessagePacket(object=" + getObject() + ", content=" + getContent() + ", recipient=" + getRecipient() + ")";
  }
  
  public MailboxServerMessagePacket(String object, String content, String recipient) {
    this.object = object;
    this.content = content;
    this.recipient = recipient;
  }
  
  private String object = "";
  
  public String getObject() {
    return this.object;
  }
  
  private String content = "";
  
  public String getContent() {
    return this.content;
  }
  
  private String recipient = "";
  
  public String getRecipient() {
    return this.recipient;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.object = ByteBufUtils.readUTF8String(buf);
    this.content = ByteBufUtils.readUTF8String(buf);
    this.recipient = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.object);
    ByteBufUtils.writeUTF8String(buf, this.content);
    ByteBufUtils.writeUTF8String(buf, this.recipient);
  }
  
  public MailboxServerMessagePacket() {}
  
  public static class Handler implements IMessageHandler<MailboxServerMessagePacket, IMessage> {
    public IMessage onMessage(MailboxServerMessagePacket message, MessageContext ctx) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
              Response<UUID> call = ApiServices.Http.getMinecraftApi().getUUIDFromUsername(message.recipient).execute();
              if (call.code() != 200) {
                (new Notification(Notification.NotificationType.ERROR, "Ce destinataire n'est pas valide", "mailbox")).send(entityPlayerMP);
                return;
              } 
              UUID uuidFromMojang = (UUID)call.body();
              String formattedUUID = uuidFromMojang.getId().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
              SendMail sendMail = new SendMail(message.object, message.content, uuidFromMojang.getName(), formattedUUID, entityPlayerMP.func_70005_c_(), FastUUID.toString((Entity)entityPlayerMP), new ArrayList(), "", EnumMailType.MESSAGE, PMailbox.SIDE, -1, false, false);
              Response<String> call2 = ApiServices.Http.getMailboxService().sendEmail(sendMail).execute();
              if (call2.code() != 201)
                return; 
              (new Notification(Notification.NotificationType.SUCCESS, "Vous avez bien envoyé ce mail", "mailbox")).send(entityPlayerMP);
              WriteMailAchievemnt.performCheck((EntityPlayer)entityPlayerMP);
              MailboxPersonnalNotificationPacket mailboxPersonnalNotificationPacket = new MailboxPersonnalNotificationPacket(sendMail.getSenderName(), sendMail.getRecipientName(), sendMail.getObject());
              mailboxPersonnalNotificationPacket.send(PMailbox.rabbit);
            } catch (IOException e) {
              e.printStackTrace();
            } 
          }0L);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerMessagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.mailbox.common.packets.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.pojo.inputs.SendMail;
import fr.paladium.palamod.modules.mailbox.rabbitmq.packets.MailboxPersonnalNotificationPacket;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import retrofit2.Response;

public class MailboxServerAnswerPacket implements IMessage {
  public void setUuidMail(String uuidMail) {
    this.uuidMail = uuidMail;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailboxServerAnswerPacket))
      return false; 
    MailboxServerAnswerPacket other = (MailboxServerAnswerPacket)o;
    if (!other.canEqual(this))
      return false; 
    Object this$uuidMail = getUuidMail(), other$uuidMail = other.getUuidMail();
    if ((this$uuidMail == null) ? (other$uuidMail != null) : !this$uuidMail.equals(other$uuidMail))
      return false; 
    Object this$content = getContent(), other$content = other.getContent();
    return !((this$content == null) ? (other$content != null) : !this$content.equals(other$content));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailboxServerAnswerPacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $uuidMail = getUuidMail();
    result = result * 59 + (($uuidMail == null) ? 43 : $uuidMail.hashCode());
    Object $content = getContent();
    return result * 59 + (($content == null) ? 43 : $content.hashCode());
  }
  
  public String toString() {
    return "MailboxServerAnswerPacket(uuidMail=" + getUuidMail() + ", content=" + getContent() + ")";
  }
  
  public MailboxServerAnswerPacket(String uuidMail, String content) {
    this.uuidMail = uuidMail;
    this.content = content;
  }
  
  private String uuidMail = "";
  
  public String getUuidMail() {
    return this.uuidMail;
  }
  
  private String content = "";
  
  public String getContent() {
    return this.content;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.uuidMail = ByteBufUtils.readUTF8String(buf);
    this.content = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.uuidMail);
    ByteBufUtils.writeUTF8String(buf, this.content);
  }
  
  public MailboxServerAnswerPacket() {}
  
  public static class Handler implements IMessageHandler<MailboxServerAnswerPacket, IMessage> {
    public IMessage onMessage(MailboxServerAnswerPacket message, MessageContext ctx) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
              Response<Mail> call = ApiServices.Http.getMailboxService().getEmail(message.uuidMail).execute();
              if (call.code() != 200)
                return; 
              Mail mailToAnswer = (Mail)call.body();
              SendMail sendMail = new SendMail("[RE]: " + mailToAnswer.getObject(), message.content, mailToAnswer.getSenderName(), mailToAnswer.getSenderUUID(), entityPlayerMP.func_70005_c_(), FastUUID.toString((Entity)entityPlayerMP), new ArrayList(), "", EnumMailType.MESSAGE, PMailbox.SIDE, -1, false, false);
              Response<String> call2 = ApiServices.Http.getMailboxService().sendEmail(sendMail).execute();
              if (call2.code() != 201)
                return; 
              (new Notification(Notification.NotificationType.SUCCESS, "Vous avez bien répondu à ce mail", "mailbox")).send(entityPlayerMP);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerAnswerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.mailbox.common.packets.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.lib.apollon.Apollon;
import fr.paladium.lib.apollon.notification.ANotification;
import fr.paladium.lib.apollon.notification.FlatNotification;
import fr.paladium.lib.apollon.notification.NotificationPosition;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.dto.UUID;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.pojo.inputs.SendMail;
import fr.paladium.palamod.modules.mailbox.common.containers.MailboxSendAdminMailContainer;
import fr.paladium.palamod.modules.mailbox.rabbitmq.packets.MailboxEveryoneNotificationPacket;
import fr.paladium.palamod.modules.mailbox.rabbitmq.packets.MailboxPersonnalNotificationPacket;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.ItemStackSerializer;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import retrofit2.Response;

public class MailboxServerAdminMessagePacket implements IMessage {
  public void setObject(String object) {
    this.object = object;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
  
  public void setPaladium(boolean paladium) {
    this.paladium = paladium;
  }
  
  public void setEveryone(boolean everyone) {
    this.everyone = everyone;
  }
  
  public void setDays(int days) {
    this.days = days;
  }
  
  public void setMailType(String mailType) {
    this.mailType = mailType;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailboxServerAdminMessagePacket))
      return false; 
    MailboxServerAdminMessagePacket other = (MailboxServerAdminMessagePacket)o;
    if (!other.canEqual(this))
      return false; 
    if (isPaladium() != other.isPaladium())
      return false; 
    if (isEveryone() != other.isEveryone())
      return false; 
    if (getDays() != other.getDays())
      return false; 
    Object this$object = getObject(), other$object = other.getObject();
    if ((this$object == null) ? (other$object != null) : !this$object.equals(other$object))
      return false; 
    Object this$content = getContent(), other$content = other.getContent();
    if ((this$content == null) ? (other$content != null) : !this$content.equals(other$content))
      return false; 
    Object this$recipient = getRecipient(), other$recipient = other.getRecipient();
    if ((this$recipient == null) ? (other$recipient != null) : !this$recipient.equals(other$recipient))
      return false; 
    Object this$mailType = getMailType(), other$mailType = other.getMailType();
    return !((this$mailType == null) ? (other$mailType != null) : !this$mailType.equals(other$mailType));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailboxServerAdminMessagePacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isPaladium() ? 79 : 97);
    result = result * 59 + (isEveryone() ? 79 : 97);
    result = result * 59 + getDays();
    Object $object = getObject();
    result = result * 59 + (($object == null) ? 43 : $object.hashCode());
    Object $content = getContent();
    result = result * 59 + (($content == null) ? 43 : $content.hashCode());
    Object $recipient = getRecipient();
    result = result * 59 + (($recipient == null) ? 43 : $recipient.hashCode());
    Object $mailType = getMailType();
    return result * 59 + (($mailType == null) ? 43 : $mailType.hashCode());
  }
  
  public String toString() {
    return "MailboxServerAdminMessagePacket(object=" + getObject() + ", content=" + getContent() + ", recipient=" + getRecipient() + ", paladium=" + isPaladium() + ", everyone=" + isEveryone() + ", days=" + getDays() + ", mailType=" + getMailType() + ")";
  }
  
  public MailboxServerAdminMessagePacket(String object, String content, String recipient, boolean paladium, boolean everyone, int days, String mailType) {
    this.object = object;
    this.content = content;
    this.recipient = recipient;
    this.paladium = paladium;
    this.everyone = everyone;
    this.days = days;
    this.mailType = mailType;
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
  
  private boolean paladium = false;
  
  public boolean isPaladium() {
    return this.paladium;
  }
  
  private boolean everyone = false;
  
  public boolean isEveryone() {
    return this.everyone;
  }
  
  private int days = 3;
  
  private String mailType;
  
  public int getDays() {
    return this.days;
  }
  
  public String getMailType() {
    return this.mailType;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.object = ByteBufUtils.readUTF8String(buf);
    this.content = ByteBufUtils.readUTF8String(buf);
    this.recipient = ByteBufUtils.readUTF8String(buf);
    this.paladium = buf.readBoolean();
    this.everyone = buf.readBoolean();
    this.days = buf.readInt();
    this.mailType = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.object);
    ByteBufUtils.writeUTF8String(buf, this.content);
    ByteBufUtils.writeUTF8String(buf, this.recipient);
    buf.writeBoolean(this.paladium);
    buf.writeBoolean(this.everyone);
    buf.writeInt(this.days);
    ByteBufUtils.writeUTF8String(buf, this.mailType);
  }
  
  public MailboxServerAdminMessagePacket() {}
  
  public static class Handler implements IMessageHandler<MailboxServerAdminMessagePacket, IMessage> {
    public IMessage onMessage(MailboxServerAdminMessagePacket message, MessageContext ctx) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
              if (!BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.mailbox.admin")) {
                entityPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'envoyer un mail en tant qu'administrateur !"));
                return;
              } 
              if (message.everyone && !BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.mailbox.admin.everyone")) {
                entityPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'envoyer un @everyone !"));
                return;
              } 
              if (message.paladium && !BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.mailbox.admin.paladium")) {
                entityPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'envoyer un mail en tant que Paladium !"));
                return;
              } 
              if (((EntityPlayer)entityPlayerMP).field_71070_bA == null || !(((EntityPlayer)entityPlayerMP).field_71070_bA instanceof MailboxSendAdminMailContainer))
                return; 
              MailboxSendAdminMailContainer container = (MailboxSendAdminMailContainer)((EntityPlayer)entityPlayerMP).field_71070_bA;
              UUID uuidFromMojang = null;
              String formattedUUID = "";
              if (!message.everyone) {
                Response<UUID> call = ApiServices.Http.getMinecraftApi().getUUIDFromUsername(message.recipient).execute();
                if (call.code() != 200) {
                  Apollon.instance().getNotificationManager().sendNotification((ANotification)new FlatNotification(4000L, NotificationPosition.TOP_RIGHT, "Mailbox - Erreur", "§fCe destinataire n'est pas valide !", Color.decode("#e74c3c"), ""), entityPlayerMP);
                  return;
                } 
                uuidFromMojang = (UUID)call.body();
                formattedUUID = uuidFromMojang.getId().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
              } 
              ArrayList<String> encodedItems = new ArrayList<>();
              for (int i = 0; i < 14; i++) {
                if (container.func_75139_a(i).func_75211_c() != null) {
                  ItemStack is = container.func_75139_a(i).func_75211_c().func_77946_l();
                  container.func_75139_a(i).func_75215_d(null);
                  encodedItems.add(Base64.getEncoder().encodeToString(ItemStackSerializer.write(is).getBytes()));
                } 
              } 
              SendMail sendMail = new SendMail(message.object, message.content, message.everyone ? "@everyone" : uuidFromMojang.getName(), message.everyone ? "@everyone" : formattedUUID, entityPlayerMP.func_70005_c_(), FastUUID.toString((Entity)entityPlayerMP), encodedItems, "", EnumMailType.valueOf(message.mailType), PMailbox.SIDE, 1440 * message.days, message.paladium, message.everyone);
              Response<String> call2 = ApiServices.Http.getMailboxService().sendEmail(sendMail).execute();
              if (sendMail.isEveryone()) {
                MailboxEveryoneNotificationPacket mailboxEveryoneNotificationPacket = new MailboxEveryoneNotificationPacket(sendMail.isPaladium() ? "§cPaladium" : sendMail.getSenderName(), sendMail.getObject());
                mailboxEveryoneNotificationPacket.send(PMailbox.rabbit);
              } else {
                MailboxPersonnalNotificationPacket mailboxPersonnalNotificationPacket = new MailboxPersonnalNotificationPacket(sendMail.getSenderName(), sendMail.getRecipientName(), sendMail.getObject());
                mailboxPersonnalNotificationPacket.send(PMailbox.rabbit);
              } 
              if (call2.code() != 201)
                return; 
              Apollon.instance().getNotificationManager().sendNotification((ANotification)new FlatNotification(4000L, NotificationPosition.TOP_RIGHT, "Mailbox", "§fVous avez bien envoyé ce mail !", Color.decode("#27ae60"), ""), entityPlayerMP);
              entityPlayerMP.func_71053_j();
            } catch (IOException e) {
              e.printStackTrace();
            } 
          }0L);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerAdminMessagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
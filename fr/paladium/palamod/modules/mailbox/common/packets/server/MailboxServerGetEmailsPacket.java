package fr.paladium.palamod.modules.mailbox.common.packets.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.common.packets.client.MailboxClientGetEmailsPacket;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

public class MailboxServerGetEmailsPacket implements IMessage {
  public void setPlayerUUID(String playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public void setFilter(String filter) {
    this.filter = filter;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailboxServerGetEmailsPacket))
      return false; 
    MailboxServerGetEmailsPacket other = (MailboxServerGetEmailsPacket)o;
    if (!other.canEqual(this))
      return false; 
    Object this$playerUUID = getPlayerUUID(), other$playerUUID = other.getPlayerUUID();
    if ((this$playerUUID == null) ? (other$playerUUID != null) : !this$playerUUID.equals(other$playerUUID))
      return false; 
    Object this$filter = getFilter(), other$filter = other.getFilter();
    return !((this$filter == null) ? (other$filter != null) : !this$filter.equals(other$filter));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailboxServerGetEmailsPacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $playerUUID = getPlayerUUID();
    result = result * 59 + (($playerUUID == null) ? 43 : $playerUUID.hashCode());
    Object $filter = getFilter();
    return result * 59 + (($filter == null) ? 43 : $filter.hashCode());
  }
  
  public String toString() {
    return "MailboxServerGetEmailsPacket(playerUUID=" + getPlayerUUID() + ", filter=" + getFilter() + ")";
  }
  
  public MailboxServerGetEmailsPacket(String playerUUID, String filter) {
    this.playerUUID = playerUUID;
    this.filter = filter;
  }
  
  private String playerUUID = "";
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  private String filter = "";
  
  public String getFilter() {
    return this.filter;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.playerUUID = ByteBufUtils.readUTF8String(buf);
    this.filter = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.playerUUID);
    ByteBufUtils.writeUTF8String(buf, this.filter);
  }
  
  public MailboxServerGetEmailsPacket() {}
  
  public static class Handler implements IMessageHandler<MailboxServerGetEmailsPacket, IMessage> {
    public IMessage onMessage(final MailboxServerGetEmailsPacket message, final MessageContext ctx) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
            public void run() {
              try {
                List<Mail> mails = new ArrayList<>();
                String playerUUID = message.playerUUID;
                Response<List<Mail>> call = ApiServices.Http.getMailboxService().getEmailFromRecipientUUIDAndSide(playerUUID, PMailbox.SIDE.toString()).execute();
                if (call.code() != 200)
                  return; 
                mails = (List<Mail>)call.body();
                MailboxClientGetEmailsPacket packet = new MailboxClientGetEmailsPacket(mails, message.filter);
                PMailbox.networkWrapper.sendTo((IMessage)packet, (ctx.getServerHandler()).field_147369_b);
              } catch (IOException e) {
                e.printStackTrace();
              } 
            }
          }0L);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerGetEmailsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
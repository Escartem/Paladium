package fr.paladium.palamod.modules.mailbox.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.common.packets.client.MailboxClientGetEmailsPacket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

class null implements Runnable {
  public void run() {
    try {
      List<Mail> mails = new ArrayList<>();
      String playerUUID = MailboxServerGetEmailsPacket.access$000(message);
      Response<List<Mail>> call = ApiServices.Http.getMailboxService().getEmailFromRecipientUUIDAndSide(playerUUID, PMailbox.SIDE.toString()).execute();
      if (call.code() != 200)
        return; 
      mails = (List<Mail>)call.body();
      MailboxClientGetEmailsPacket packet = new MailboxClientGetEmailsPacket(mails, MailboxServerGetEmailsPacket.access$100(message));
      PMailbox.networkWrapper.sendTo((IMessage)packet, (ctx.getServerHandler()).field_147369_b);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerGetEmailsPacket$Handler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.mailbox.common.packets.server;

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
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import retrofit2.Response;

public class Handler implements IMessageHandler<MailboxServerMessagePacket, IMessage> {
  public IMessage onMessage(MailboxServerMessagePacket message, MessageContext ctx) {
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
            Response<UUID> call = ApiServices.Http.getMinecraftApi().getUUIDFromUsername(MailboxServerMessagePacket.access$000(message)).execute();
            if (call.code() != 200) {
              (new Notification(Notification.NotificationType.ERROR, "Ce destinataire n'est pas valide", "mailbox")).send(entityPlayerMP);
              return;
            } 
            UUID uuidFromMojang = (UUID)call.body();
            String formattedUUID = uuidFromMojang.getId().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
            SendMail sendMail = new SendMail(MailboxServerMessagePacket.access$100(message), MailboxServerMessagePacket.access$200(message), uuidFromMojang.getName(), formattedUUID, entityPlayerMP.func_70005_c_(), FastUUID.toString((Entity)entityPlayerMP), new ArrayList(), "", EnumMailType.MESSAGE, PMailbox.SIDE, -1, false, false);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerMessagePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
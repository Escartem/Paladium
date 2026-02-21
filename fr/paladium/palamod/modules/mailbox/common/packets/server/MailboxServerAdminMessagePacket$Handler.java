package fr.paladium.palamod.modules.mailbox.common.packets.server;

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

public class Handler implements IMessageHandler<MailboxServerAdminMessagePacket, IMessage> {
  public IMessage onMessage(MailboxServerAdminMessagePacket message, MessageContext ctx) {
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
            if (!BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.mailbox.admin")) {
              entityPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'envoyer un mail en tant qu'administrateur !"));
              return;
            } 
            if (MailboxServerAdminMessagePacket.access$000(message) && !BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.mailbox.admin.everyone")) {
              entityPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'envoyer un @everyone !"));
              return;
            } 
            if (MailboxServerAdminMessagePacket.access$100(message) && !BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.mailbox.admin.paladium")) {
              entityPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'envoyer un mail en tant que Paladium !"));
              return;
            } 
            if (((EntityPlayer)entityPlayerMP).field_71070_bA == null || !(((EntityPlayer)entityPlayerMP).field_71070_bA instanceof MailboxSendAdminMailContainer))
              return; 
            MailboxSendAdminMailContainer container = (MailboxSendAdminMailContainer)((EntityPlayer)entityPlayerMP).field_71070_bA;
            UUID uuidFromMojang = null;
            String formattedUUID = "";
            if (!MailboxServerAdminMessagePacket.access$000(message)) {
              Response<UUID> call = ApiServices.Http.getMinecraftApi().getUUIDFromUsername(MailboxServerAdminMessagePacket.access$200(message)).execute();
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
            SendMail sendMail = new SendMail(MailboxServerAdminMessagePacket.access$300(message), MailboxServerAdminMessagePacket.access$400(message), MailboxServerAdminMessagePacket.access$000(message) ? "@everyone" : uuidFromMojang.getName(), MailboxServerAdminMessagePacket.access$000(message) ? "@everyone" : formattedUUID, entityPlayerMP.func_70005_c_(), FastUUID.toString((Entity)entityPlayerMP), encodedItems, "", EnumMailType.valueOf(MailboxServerAdminMessagePacket.access$500(message)), PMailbox.SIDE, 1440 * MailboxServerAdminMessagePacket.access$600(message), MailboxServerAdminMessagePacket.access$100(message), MailboxServerAdminMessagePacket.access$000(message));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerAdminMessagePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
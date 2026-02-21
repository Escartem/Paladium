package fr.paladium.palamod.modules.mailbox.server.commands;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.Command;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.dto.UUID;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.List;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import retrofit2.Response;

public class ShopDumpCommand extends Command {
  public static final String COMMAND_NAME = "shopmaildump";
  
  public static final String PERMISSION = "paladium.shopmail";
  
  public String func_71517_b() {
    return "shopmaildump";
  }
  
  public boolean perform(ICommandSender sender, String[] args) {
    if (args.length < 1)
      return false; 
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            UUID uniqueId = getUniqueId(args[0]);
            if (uniqueId == null) {
              ChatUtils.sendColoredMessage(sender, new String[] { "§cImpossible de trouver l'UUID de cet utilisateur." });
              return;
            } 
            Response<List<Mail>> response = ApiServices.Http.getMailboxService().getEmailFromRecipientUUID(uniqueId.toString()).execute();
            if (!response.isSuccessful()) {
              ChatUtils.sendColoredMessage(sender, new String[] { "§cUne erreur est survenue lors de la récupération des mails." });
              return;
            } 
            List<Mail> mailList = (List<Mail>)response.body();
            if (mailList == null || mailList.isEmpty()) {
              ChatUtils.sendColoredMessage(sender, new String[] { "§cAucun mail trouvé pour cet utilisateur." });
              return;
            } 
            ChatUtils.sendColoredMessage(sender, new String[] { "§aListe des mails de Paladium pour l'utilisateur " + args[0] + " :" });
            mailList.stream().filter(()).forEach(());
          } catch (Exception e) {
            e.printStackTrace();
          } 
        }0L);
    return true;
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "§c/shopmaildump <username or uniqueId>";
  }
  
  public String getCommandUsage(ICommandSender sender, String[] strings) {
    return func_71518_a(sender);
  }
  
  private UUID getUniqueIdFromUsername(String username) throws Exception {
    Response<UUID> call = ApiServices.Http.getMinecraftApi().getUUIDFromUsername(username).execute();
    if (call.code() != 200)
      return null; 
    UUID uuidFromMojang = (UUID)call.body();
    String formattedUUID = uuidFromMojang.getId().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
    return UUID.fromString(formattedUUID);
  }
  
  public UUID getUniqueId(String username) throws Exception {
    UUID uniqueId = uuidFromString(username);
    if (uniqueId == null)
      uniqueId = getUniqueIdFromUsername(username); 
    return uniqueId;
  }
  
  private UUID uuidFromString(String arg) {
    try {
      return UUID.fromString(arg);
    } catch (Exception e) {
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\server\commands\ShopDumpCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
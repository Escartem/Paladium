package fr.paladium.palamod.modules.mailbox.common.events;

import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailSide;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import retrofit2.Response;

class null implements Runnable {
  public void run() {
    try {
      Response<List<Mail>> responseRecupItems = ApiServices.Http.getMailboxService().getEmailFromRecipientUUIDAndSide(player.func_110124_au().toString(), EnumMailSide.FACTION.toString()).execute();
      return;
    } catch (IOException e) {
      if (!ForgeEnv.isDev())
        e.printStackTrace(); 
      return;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\events\MailBoxEvents$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
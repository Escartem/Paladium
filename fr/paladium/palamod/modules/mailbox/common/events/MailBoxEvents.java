package fr.paladium.palamod.modules.mailbox.common.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailSide;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import retrofit2.Response;

public class MailBoxEvents {
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent e) {
    if (!e.world.field_72995_K && e.entity instanceof EntityPlayerMP) {
      final EntityPlayerMP player = (EntityPlayerMP)e.entity;
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
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
          }0L);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\events\MailBoxEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
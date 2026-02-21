package fr.paladium.palavote.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palavote.PalaVoteMod;
import fr.paladium.palavote.client.ui.UIPalaVote;
import fr.paladium.palavote.common.action.PalaVoteServerAction;
import fr.paladium.palavote.server.config.PalaVoteConfig;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class PalaVoteInteractServerListener {
  @SubscribeEvent
  public void onInteract(EntityInteractEvent event) {
    if (!(event.entityPlayer instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    PalaVoteConfig config = PalaVoteMod.getServer().getConfig();
    if (!config.isEnabled()) {
      (new Notification(Notification.NotificationType.ERROR, "Le vote est actuellement terminé", "palavote")).send(player);
      return;
    } 
    if (!event.target.func_70005_c_().equalsIgnoreCase(config.getName()))
      return; 
    ZUIServer.open(UIPalaVote.class, player, new Object[] { new PalaVoteServerAction.PalaVoteData(config) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\server\listener\PalaVoteInteractServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
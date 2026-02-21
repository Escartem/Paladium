package fr.paladium.palaforgeutils.lib.server.internal.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.internal.network.SCPacketServerInformations;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerInformationsPlayerListener {
  @SubscribeEvent
  @SideOnly(Side.SERVER)
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    if (!(event.player instanceof EntityPlayerMP))
      return; 
    PalaForgeUtilsMod.proxy.getNetwork().sendTo((IMessage)new SCPacketServerInformations(Server.getHostName(), Server.getRawServerType(), Server.getServerName()), (EntityPlayerMP)event.player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\internal\listener\ServerInformationsPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
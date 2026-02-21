package fr.paladium.palavoicechat.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palavoicechat.common.eep.VoiceChatExtendedEntityProperties;
import fr.paladium.palavoicechat.common.network.SCVoiceServerUnavailablePacket;
import fr.paladium.palavoicechat.server.manager.KeepAliveManager;
import fr.paladium.palavoicechat.server.manager.MuteCacheManager;
import fr.paladium.palavoicechat.server.voip.IoNettyVoIPServer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerListener {
  @SubscribeEvent
  public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
    if (event.player instanceof EntityPlayerMP)
      IoNettyVoIPServer.getInstance().disconnectPlayer((EntityPlayerMP)event.player); 
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.START || !IoNettyVoIPServer.getInstance().isPlayerConnected(event.player))
      return; 
    if (!KeepAliveManager.getInstance().isAlive()) {
      IoNettyVoIPServer.getInstance().disconnectPlayer((EntityPlayerMP)event.player);
      (new SCVoiceServerUnavailablePacket()).send((EntityPlayerMP)event.player);
      return;
    } 
    if (event.player.field_70173_aa % 10 == 0) {
      boolean playerMuted = MuteCacheManager.getInstance().isPlayerMuted(event.player);
      VoiceChatExtendedEntityProperties props = VoiceChatExtendedEntityProperties.get(event.player);
      if (props == null)
        return; 
      props.setMuted(playerMuted);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\listener\ServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
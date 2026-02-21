package fr.paladium.palamod.modules.world.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.client.gui.PGuiOverlayDebug;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.world.network.PacketDebugInfo;
import fr.paladium.palamod.modules.world.network.PacketRequestDebugInfo;
import net.minecraft.entity.player.EntityPlayerMP;

public class DebugInfoHandler {
  @SubscribeEvent
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    if (Utils.isClient()) {
      PalaMod.getNetwork().sendToServer((IMessage)new PacketRequestDebugInfo());
    } else if (PConfigs.server_minage_enabled || PMiner.proxy.isMinerDimension()) {
      PalaMod.getNetwork().sendTo((IMessage)new PacketDebugInfo(PConfigs.paladiumgreen_ore_enabled, PMiner.proxy.isMinerDimension(), PConfigs.doublepaladium_ore_enabled), (EntityPlayerMP)event.player);
    } 
  }
  
  @SubscribeEvent
  public void onDisconnectedFromServerEvent(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
    if (Utils.isClient())
      PGuiOverlayDebug.clearBlockLayer(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\events\DebugInfoHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
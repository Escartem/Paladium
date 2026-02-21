package fr.paladium.palamod.modules.nemesis.module.impl.dump.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet.BBPacketNemesisDumpStreamData;

public class NemesisDumpClientListener {
  @SubscribeEvent
  public void onSwitch(ServerChangeEvent.Post event) {
    BBPacketNemesisDumpStreamData.stopStream();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\client\listener\NemesisDumpClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palahologram.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palahologram.common.network.SCPacketCopyHologram;
import fr.paladium.palahologram.common.network.SCPacketSyncHolograms;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    initNetwork("palahologram");
    PacketUtils.registerPacket(getNetwork(), SCPacketSyncHolograms.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketCopyHologram.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
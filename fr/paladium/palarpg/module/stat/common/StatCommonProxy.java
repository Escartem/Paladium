package fr.paladium.palarpg.module.stat.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.listener.StatCommonLivingListener;
import fr.paladium.palarpg.module.stat.common.network.SCRPGDamagePacket;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;

public class StatCommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    addListener(new Class[] { StatCommonLivingListener.class });
    RPGPlayer.registerPlayerData(new Class[] { RPGStatPlayerData.class });
    initNetwork("palarpg_stats");
    PacketUtils.registerPacket(getNetwork(), SCRPGDamagePacket.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\StatCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
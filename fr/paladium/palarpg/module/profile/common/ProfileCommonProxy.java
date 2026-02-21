package fr.paladium.palarpg.module.profile.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.common.network.SCPacketSyncSkillTrees;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;

public class ProfileCommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    initNetwork("palaboss_profile");
    PacketUtils.registerPacket(getNetwork(), SCPacketSyncSkillTrees.class);
    RPGPlayer.registerPlayerData(new Class[] { RPGProfilePlayerData.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\ProfileCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
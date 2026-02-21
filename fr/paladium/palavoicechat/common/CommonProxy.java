package fr.paladium.palavoicechat.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palavoicechat.common.eep.VoiceChatExtendedEntityProperties;
import fr.paladium.palavoicechat.common.listener.FlagRegisterListener;
import fr.paladium.palavoicechat.common.network.BBAuthenticatePacket;
import fr.paladium.palavoicechat.common.network.BBPacketGetPlayersVoice;
import fr.paladium.palavoicechat.common.network.CSDisconnectVCPacket;
import fr.paladium.palavoicechat.common.network.SCPacketReconnectAfterDown;
import fr.paladium.palavoicechat.common.network.SCVoiceServerUnavailablePacket;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    initNetwork("palaVoiceChat");
    PacketUtils.registerPacket(getNetwork(), BBAuthenticatePacket.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetPlayersVoice.class);
    PacketUtils.registerPacket(getNetwork(), CSDisconnectVCPacket.class);
    PacketUtils.registerPacket(getNetwork(), SCVoiceServerUnavailablePacket.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketReconnectAfterDown.class);
    addListener(new Class[] { FlagRegisterListener.class });
    ExtendedUtils.registerExtended(EntityPlayer.class, VoiceChatExtendedEntityProperties.class, "VOICECHAT_EEP", new ExtendedProperty[] { ExtendedProperty.PERSISTANT, ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palapass.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.packet.client.SCPacketPalapassCopyText;
import fr.paladium.palapass.common.network.packet.client.SCPacketPalapassNotification;
import fr.paladium.palapass.common.network.packet.client.SCPacketPalapassOpenGui;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassBuyNextStep;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassBuyPremium;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassClaimReward;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassGiveItem;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    ExtendedUtils.registerExtended(EntityPlayer.class, PalapassPlayer.class, "palapass_PPass", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
    initNetwork("palapass");
    SimpleNetworkWrapper network = getNetwork();
    PacketUtils.registerPacket(network, SCPacketPalapassCopyText.class);
    PacketUtils.registerPacket(network, SCPacketPalapassOpenGui.class);
    PacketUtils.registerPacket(network, SCPacketPalapassNotification.class);
    PacketUtils.registerPacket(network, CSPacketPalapassClaimReward.class);
    PacketUtils.registerPacket(network, CSPacketPalapassGiveItem.class);
    PacketUtils.registerPacket(network, CSPacketPalapassBuyNextStep.class);
    PacketUtils.registerPacket(network, CSPacketPalapassBuyPremium.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
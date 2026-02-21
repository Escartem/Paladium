package fr.paladium.announcement.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.paladium.announcement.common.data.AnnouncementPlayer;
import fr.paladium.announcement.common.network.SCAnnouncementPacket;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AnnouncementCommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    registerPackets();
    registerExtendedProperties();
  }
  
  private void registerPackets() {
    initNetwork("announcement");
    SimpleNetworkWrapper network = getNetwork();
    PacketUtils.registerPacket(network, SCAnnouncementPacket.class);
  }
  
  private void registerExtendedProperties() {
    ExtendedUtils.registerExtended(EntityPlayer.class, AnnouncementPlayer.class, "announcement_AnnouncementPlayer", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\AnnouncementCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
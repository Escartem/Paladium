package fr.paladium.palavanillagui.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palavanillagui.common.guihandler.GuiRegistry;
import fr.paladium.palavanillagui.common.packet.CSPacketRenameItemAnvil;
import fr.paladium.palavanillagui.common.packet.CSRequestShortcuts;
import fr.paladium.palavanillagui.common.packet.SCRequestShortcutsReply;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    initNetwork("vanillagui");
    PacketUtils.registerPacket(getNetwork(), CSRequestShortcuts.class);
    PacketUtils.registerPacket(getNetwork(), SCRequestShortcutsReply.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketRenameItemAnvil.class);
  }
  
  public void onInit(FMLInitializationEvent event) {
    GuiRegistry.init();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
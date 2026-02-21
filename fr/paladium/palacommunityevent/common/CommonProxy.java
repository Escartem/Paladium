package fr.paladium.palacommunityevent.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.paladium.palacommunityevent.PalaCommunityEventMod;
import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.handler.AdventCalendarHandler;
import fr.paladium.palacommunityevent.common.handler.GuiCommunityEventDepositHandler;
import fr.paladium.palacommunityevent.common.handler.utils.GuiHandler;
import fr.paladium.palacommunityevent.common.handler.utils.Handler;
import fr.paladium.palacommunityevent.common.listeners.NPCListener;
import fr.paladium.palacommunityevent.common.network.BBPacketAdventCalendar;
import fr.paladium.palacommunityevent.common.network.SCOpenCommunityEventDepositPacket;
import fr.paladium.palacommunityevent.common.network.SCOpenCommunityEventHomePacket;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy extends AModProxy {
  public static int COMMUNITYEVENT_DEPOSIT;
  
  public static int ADVENT_CALENDAR;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    NetworkRegistry.INSTANCE.registerGuiHandler(PalaCommunityEventMod.getInstance(), (IGuiHandler)new GuiHandler());
    COMMUNITYEVENT_DEPOSIT = GuiHandler.registerHandler((Handler)new GuiCommunityEventDepositHandler());
    ADVENT_CALENDAR = GuiHandler.registerHandler((Handler)new AdventCalendarHandler());
    FMLCommonHandler.instance().bus().register(new NPCListener());
    MinecraftForge.EVENT_BUS.register(new NPCListener());
    ExtendedUtils.registerExtended(EntityPlayer.class, PalaCommunityEventEEP.class, "comnityevnt_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
    initNetwork("palacommunityevent");
    PacketUtils.registerPacket(getNetwork(), SCOpenCommunityEventDepositPacket.class);
    PacketUtils.registerPacket(getNetwork(), SCOpenCommunityEventHomePacket.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketAdventCalendar.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
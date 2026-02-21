package fr.paladium.palatrade.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.paladium.palatrade.PalaTrade;
import fr.paladium.palatrade.common.handler.GuiTradeHandler;
import fr.paladium.palatrade.common.handler.utils.GuiHandler;
import fr.paladium.palatrade.common.handler.utils.Handler;
import fr.paladium.palatrade.common.network.CSCloseTradePacket;
import fr.paladium.palatrade.common.network.CSUpdateFieldsTradePacket;
import fr.paladium.palatrade.common.network.CSValidateTradePacket;
import fr.paladium.palatrade.common.network.SCOpenTradePacket;
import fr.paladium.palatrade.common.network.SCUnvalidateTargetTradePacket;
import fr.paladium.palatrade.common.network.SCUnvalidateTradePacket;
import fr.paladium.palatrade.common.network.SCUpdateFieldsTradePacket;
import fr.paladium.palatrade.common.network.SCUpdateItemsTradePacket;
import fr.paladium.palatrade.common.network.SCValidateTradePacket;
import fr.paladium.palatrade.lib.odin.modules.command.OdinCommandModule;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.utils.proxy.AProxy;

public abstract class CommonProxy extends AProxy {
  public static int TRADE_GUI;
  
  public void loadModules() {
    loadModule("palatrade", OdinCommandModule.class);
    loadModule("palatrade", OdinPacketModule.class);
    OdinPacketModule.getInstance().registerPacket(SCOpenTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(SCUpdateItemsTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(SCUpdateFieldsTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(CSUpdateFieldsTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(CSValidateTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(SCValidateTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(CSCloseTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(SCUnvalidateTradePacket.class);
    OdinPacketModule.getInstance().registerPacket(SCUnvalidateTargetTradePacket.class);
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    NetworkRegistry.INSTANCE.registerGuiHandler(PalaTrade.getInstance(), (IGuiHandler)new GuiHandler());
    TRADE_GUI = GuiHandler.registerHandler((Handler)new GuiTradeHandler());
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
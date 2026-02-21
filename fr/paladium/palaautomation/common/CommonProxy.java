package fr.paladium.palaautomation.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.paladium.blueprint.common.manager.StructureManager;
import fr.paladium.palaautomation.common.block.BlockCrafter;
import fr.paladium.palaautomation.common.block.BlockGiver;
import fr.paladium.palaautomation.common.block.BlockInjector;
import fr.paladium.palaautomation.common.block.BlockReceiver;
import fr.paladium.palaautomation.common.block.pipe.type.BlockEndiumPipe;
import fr.paladium.palaautomation.common.block.pipe.type.BlockPaladiumGreenPipe;
import fr.paladium.palaautomation.common.block.pipe.type.BlockPaladiumPipe;
import fr.paladium.palaautomation.common.blueprint.AutoCrafterBlueprint;
import fr.paladium.palaautomation.common.handler.CrafterGuiHandler;
import fr.paladium.palaautomation.common.handler.GiverGuiHandler;
import fr.paladium.palaautomation.common.handler.ReceiverGuiHandler;
import fr.paladium.palaautomation.common.packet.BBCrafterChangeStatePacket;
import fr.paladium.palaautomation.common.packet.CSPipeMachinePickupItemsPacket;
import fr.paladium.palaautomation.common.packet.CSPipeMachineUpdatePacket;
import fr.paladium.palaautomation.common.registry.AutomationBlockRegistry;
import fr.paladium.palaautomation.common.tab.AutomationTab;
import fr.paladium.palaforgeutils.lib.guihandler.CustomGuiHandler;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import net.minecraft.block.Block;

public abstract class CommonProxy extends AModProxy {
  private static CommonProxy instance;
  
  private AutomationTab tabs;
  
  private CustomGuiHandler guiHandler;
  
  private int giverHandlerId;
  
  private int receiverHandlerId;
  
  private int crafterHandlerId;
  
  public static CommonProxy getInstance() {
    return instance;
  }
  
  public AutomationTab getTabs() {
    return this.tabs;
  }
  
  public CustomGuiHandler getGuiHandler() {
    return this.guiHandler;
  }
  
  public int getGiverHandlerId() {
    return this.giverHandlerId;
  }
  
  public int getReceiverHandlerId() {
    return this.receiverHandlerId;
  }
  
  public int getCrafterHandlerId() {
    return this.crafterHandlerId;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    instance = this;
    this.tabs = new AutomationTab();
    RegistryUtils.block(new Block[] { AutomationBlockRegistry.PIPE_PALADIUM = (Block)new BlockPaladiumPipe(), AutomationBlockRegistry.PIPE_PALADIUM_GREEN = (Block)new BlockPaladiumGreenPipe(), AutomationBlockRegistry.PIPE_PALADIUM_ENDIUM = (Block)new BlockEndiumPipe(), AutomationBlockRegistry.INJECTOR = (Block)new BlockInjector(), AutomationBlockRegistry.CRAFTER = (Block)new BlockCrafter(), AutomationBlockRegistry.GIVER = (Block)new BlockGiver(), AutomationBlockRegistry.RECEIVER = (Block)new BlockReceiver() });
    NetworkRegistry.INSTANCE.registerGuiHandler("palaautomation", (IGuiHandler)(this.guiHandler = new CustomGuiHandler()));
    this.crafterHandlerId = this.guiHandler.registerHandler((GHandler)new CrafterGuiHandler());
    this.giverHandlerId = this.guiHandler.registerHandler((GHandler)new GiverGuiHandler());
    this.receiverHandlerId = this.guiHandler.registerHandler((GHandler)new ReceiverGuiHandler());
    initNetwork("palaautomation");
    SimpleNetworkWrapper network = getNetwork();
    PacketUtils.registerPacket(network, CSPipeMachineUpdatePacket.class);
    PacketUtils.registerPacket(network, CSPipeMachinePickupItemsPacket.class);
    PacketUtils.registerPacket(network, BBCrafterChangeStatePacket.class);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    StructureManager.getInstance().registerBluePrint(AutoCrafterBlueprint.class);
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
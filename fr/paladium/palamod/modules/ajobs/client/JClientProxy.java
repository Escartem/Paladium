package fr.paladium.palamod.modules.ajobs.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.ajobs.client.renders.blocks.TileEntityAlchemistThroneRenderer;
import fr.paladium.palamod.modules.ajobs.client.renders.blocks.TileEntityFarmerThroneRenderer;
import fr.paladium.palamod.modules.ajobs.client.renders.blocks.TileEntityHunterThroneRenderer;
import fr.paladium.palamod.modules.ajobs.client.renders.blocks.TileEntityMinerThroneRenderer;
import fr.paladium.palamod.modules.ajobs.client.renders.inventory.AlchemistThroneRenderInventory;
import fr.paladium.palamod.modules.ajobs.client.renders.inventory.FarmerThroneRenderInventory;
import fr.paladium.palamod.modules.ajobs.client.renders.inventory.HunterThroneRenderInventory;
import fr.paladium.palamod.modules.ajobs.client.renders.inventory.MinerThroneRenderInventory;
import fr.paladium.palamod.modules.ajobs.common.JCommonProxy;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityAlchemistThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityFarmerThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityHunterThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityMinerThrone;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class JClientProxy extends JCommonProxy {
  public static int renderAlchemistThrone;
  
  public static int renderHunterThrone;
  
  public static int renderMinerThrone;
  
  public static int renderFarmerThrone;
  
  public void preInit(FMLPreInitializationEvent event) {
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemistThrone.class, (TileEntitySpecialRenderer)new TileEntityAlchemistThroneRenderer());
    renderAlchemistThrone = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new AlchemistThroneRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHunterThrone.class, (TileEntitySpecialRenderer)new TileEntityHunterThroneRenderer());
    renderHunterThrone = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new HunterThroneRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMinerThrone.class, (TileEntitySpecialRenderer)new TileEntityMinerThroneRenderer());
    renderMinerThrone = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MinerThroneRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFarmerThrone.class, (TileEntitySpecialRenderer)new TileEntityFarmerThroneRenderer());
    renderFarmerThrone = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new FarmerThroneRenderInventory());
  }
  
  public void init(FMLInitializationEvent event) {}
  
  public void postInit(FMLPostInitializationEvent event) {}
  
  public void serverStarting(FMLServerStartingEvent event) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\client\JClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palaautomation.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fr.paladium.palaautomation.client.renderer.tile.TileEntityCrafterRenderer;
import fr.paladium.palaautomation.client.renderer.tile.TileEntityGiverRenderer;
import fr.paladium.palaautomation.client.renderer.tile.TileEntityInjectorRenderer;
import fr.paladium.palaautomation.client.renderer.tile.TileEntityPipeRenderer;
import fr.paladium.palaautomation.client.renderer.tile.TileEntityReceiverRenderer;
import fr.paladium.palaautomation.client.renderer.tile.inventory.CrafterRenderInventory;
import fr.paladium.palaautomation.client.renderer.tile.inventory.GiverRenderInventory;
import fr.paladium.palaautomation.client.renderer.tile.inventory.InjectorRenderInventory;
import fr.paladium.palaautomation.client.renderer.tile.inventory.ReceiverRenderInventory;
import fr.paladium.palaautomation.client.renderer.tile.inventory.pipe.GreenPaladiumPipeRenderInventory;
import fr.paladium.palaautomation.client.renderer.tile.inventory.pipe.MixedEndiumPipeRenderInventory;
import fr.paladium.palaautomation.client.renderer.tile.inventory.pipe.PaladiumPipeRenderInventory;
import fr.paladium.palaautomation.common.CommonProxy;
import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import fr.paladium.palaautomation.common.tile.impl.TileEntityGiver;
import fr.paladium.palaautomation.common.tile.impl.TileEntityInjector;
import fr.paladium.palaautomation.common.tile.impl.TileEntityReceiver;
import fr.paladium.palaautomation.common.tile.pipe.type.TileEntityGreenPaladiumPipe;
import fr.paladium.palaautomation.common.tile.pipe.type.TileEntityMixedEndiumPipe;
import fr.paladium.palaautomation.common.tile.pipe.type.TileEntityPaladiumPipe;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class ClientProxy extends CommonProxy {
  public static int renderPipePaladiumId;
  
  public static int renderPipeGreenPaladiumId;
  
  public static int renderPipeMixedEndiumId;
  
  public static int renderCrafterId;
  
  public static int renderGiverId;
  
  public static int renderInjectorId;
  
  public static int renderReceiverId;
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    renderPipePaladiumId = RenderingRegistry.getNextAvailableRenderId();
    renderPipeGreenPaladiumId = RenderingRegistry.getNextAvailableRenderId();
    renderPipeMixedEndiumId = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPaladiumPipe.class, (TileEntitySpecialRenderer)new TileEntityPipeRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGreenPaladiumPipe.class, (TileEntitySpecialRenderer)new TileEntityPipeRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMixedEndiumPipe.class, (TileEntitySpecialRenderer)new TileEntityPipeRenderer());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new PaladiumPipeRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MixedEndiumPipeRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new GreenPaladiumPipeRenderInventory());
    renderCrafterId = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrafter.class, (TileEntitySpecialRenderer)new TileEntityCrafterRenderer());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new CrafterRenderInventory());
    renderGiverId = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGiver.class, (TileEntitySpecialRenderer)new TileEntityGiverRenderer());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new GiverRenderInventory());
    renderInjectorId = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInjector.class, (TileEntitySpecialRenderer)new TileEntityInjectorRenderer());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new InjectorRenderInventory());
    renderReceiverId = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReceiver.class, (TileEntitySpecialRenderer)new TileEntityReceiverRenderer());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new ReceiverRenderInventory());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
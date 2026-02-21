package fr.paladium.palamod.modules.alchimiste.proxies;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palamod.modules.alchimiste.client.renderer.RenderGlueball;
import fr.paladium.palamod.modules.alchimiste.client.renderer.inventory.TIleEntityPortalControllerRenderer;
import fr.paladium.palamod.modules.alchimiste.client.renderer.inventory.TIleEntityWoodInventoryRenderer;
import fr.paladium.palamod.modules.alchimiste.client.renderer.inventory.TileEntityExtractorInventoryRenderer;
import fr.paladium.palamod.modules.alchimiste.client.renderer.inventory.TileEntityTankInventoryRenderer;
import fr.paladium.palamod.modules.alchimiste.client.renderer.tesr.TileEntityAlchemistPortalControllerRenderer;
import fr.paladium.palamod.modules.alchimiste.client.renderer.tesr.TileEntityExtractorRenderer;
import fr.paladium.palamod.modules.alchimiste.client.renderer.tesr.TileEntityTankRenderer;
import fr.paladium.palamod.modules.alchimiste.common.entities.EntityGlueball;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityAlchemistPortalController;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityExtractor;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityTank;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class ClientProxy extends CommonProxy {
  public static int extractorRenderId;
  
  public static int tanksRenderId;
  
  public static int portalController;
  
  public static int woodAlchimist;
  
  public void regRenders() {
    super.regRenders();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, (TileEntitySpecialRenderer)new TileEntityExtractorRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, (TileEntitySpecialRenderer)new TileEntityTankRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemistPortalController.class, (TileEntitySpecialRenderer)new TileEntityAlchemistPortalControllerRenderer());
    extractorRenderId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityExtractorInventoryRenderer());
    tanksRenderId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityTankInventoryRenderer());
    portalController = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TIleEntityPortalControllerRenderer());
    woodAlchimist = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TIleEntityWoodInventoryRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityGlueball.class, (Render)new RenderGlueball());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\proxies\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.communityevents;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.communityevents.client.render.barrel.TileEntityBarrelInventoryRender;
import fr.paladium.palamod.modules.communityevents.client.render.barrel.TileEntityBarrelRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.arty.TileEntityFeveArtyIInventoryRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.arty.TileEntityFeveArtyRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.dancarok.TileEntityFeveDancarokIInventoryRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.dancarok.TileEntityFeveDancarokRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.ravirok.TileEntityFeveRavirokIInventoryRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.ravirok.TileEntityFeveRavirokRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.tedarok.TileEntityFeveTedarokIInventoryRender;
import fr.paladium.palamod.modules.communityevents.client.render.feve.tedarok.TileEntityFeveTedarokRender;
import fr.paladium.palamod.modules.communityevents.client.render.piratechest.PirateChestItemRenderer;
import fr.paladium.palamod.modules.communityevents.client.render.rainbowskull.TileEntityRainbowSkullInventoryRender;
import fr.paladium.palamod.modules.communityevents.client.render.rainbowskull.TileEntityRainbowSkullRender;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityBarrelWood;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveArty;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveDancarok;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveRavirok;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveTedarok;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityRainbowSkull;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
  public static int renderRainbowSkull;
  
  public static int renderBarrel;
  
  public static int renderFeveArty;
  
  public static int renderFeveDancarok;
  
  public static int renderFeveTedarok;
  
  public static int renderFeveRavirok;
  
  public void preInit(FMLPreInitializationEvent e) {
    super.preInit(e);
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRainbowSkull.class, (TileEntitySpecialRenderer)new TileEntityRainbowSkullRender());
    renderRainbowSkull = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityRainbowSkullInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrelWood.class, (TileEntitySpecialRenderer)new TileEntityBarrelRender());
    renderBarrel = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityBarrelInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFeveArty.class, (TileEntitySpecialRenderer)new TileEntityFeveArtyRender());
    renderFeveArty = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityFeveArtyIInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFeveDancarok.class, (TileEntitySpecialRenderer)new TileEntityFeveDancarokRender());
    renderFeveDancarok = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityFeveDancarokIInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFeveTedarok.class, (TileEntitySpecialRenderer)new TileEntityFeveTedarokRender());
    renderFeveTedarok = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityFeveTedarokIInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFeveRavirok.class, (TileEntitySpecialRenderer)new TileEntityFeveRavirokRender());
    renderFeveRavirok = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityFeveRavirokIInventoryRender());
  }
  
  public void registerRenders() {
    super.registerRenders();
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PIRATE_CHEST, (IItemRenderer)new PirateChestItemRenderer());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palawarzoneevent.module.warzone.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.paladium.palawarzoneevent.module.warzone.client.renderer.TileEntityWarzoneSignRenderer;
import fr.paladium.palawarzoneevent.module.warzone.common.WarzoneCommonProxy;
import fr.paladium.palawarzoneevent.module.warzone.common.block.WarzoneBlocks;
import fr.paladium.palawarzoneevent.module.warzone.common.block.tileentity.TileEntityWarzoneSign;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class WarzoneClientProxy extends WarzoneCommonProxy {
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    TileEntityWarzoneSignRenderer tileRenderer = new TileEntityWarzoneSignRenderer();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWarzoneSign.class, (TileEntitySpecialRenderer)tileRenderer);
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(WarzoneBlocks.WARZONE_SIGN), (IItemRenderer)tileRenderer);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\client\WarzoneClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
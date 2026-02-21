package fr.paladium.palaforgeutils.internal.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.internal.common.CommonProxy;
import fr.paladium.palaforgeutils.lib.lang.CustomLanguageManager;
import fr.paladium.palaforgeutils.lib.potion.entity.CustomEntityPotion;
import fr.paladium.palaforgeutils.lib.potion.render.RenderCustomEntityPotion;
import fr.paladium.palaforgeutils.lib.resource.FileResourcePack;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.tooltip.CustomTooltipListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { FMLClientScheduler.class });
    RenderingRegistry.registerEntityRenderingHandler(CustomEntityPotion.class, (Render)new RenderCustomEntityPotion());
    FileResourcePack.register();
    if (Minecraft.func_71410_x().func_110442_L() instanceof IReloadableResourceManager)
      ((IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L()).func_110542_a((IResourceManagerReloadListener)new CustomLanguageManager()); 
    addListener(new Class[] { CustomTooltipListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\internal\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
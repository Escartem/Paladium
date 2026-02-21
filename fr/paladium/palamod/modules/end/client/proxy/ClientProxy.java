package fr.paladium.palamod.modules.end.client.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palamod.modules.end.client.render.RenderEventEndDragon;
import fr.paladium.palamod.modules.end.common.entity.EntityEventEndDragon;
import fr.paladium.palamod.modules.end.common.proxy.CommonProxy;
import fr.paladium.palamod.modules.trixium.utils.old.OldTrixiumHeadPosition;
import net.minecraft.client.renderer.entity.Render;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    RenderingRegistry.registerEntityRenderingHandler(EntityEventEndDragon.class, (Render)new RenderEventEndDragon());
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    OldTrixiumHeadPosition.startChecks();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\client\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
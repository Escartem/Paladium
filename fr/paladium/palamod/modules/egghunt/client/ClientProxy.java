package fr.paladium.palamod.modules.egghunt.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palamod.modules.egghunt.client.render.RenderCustomDragon;
import fr.paladium.palamod.modules.egghunt.client.render.RenderEgg;
import fr.paladium.palamod.modules.egghunt.common.CommonProxy;
import fr.paladium.palamod.modules.egghunt.common.entity.EntityCustomDragon;
import net.minecraft.client.renderer.entity.Render;

public class ClientProxy extends CommonProxy {
  public void init() {
    super.init();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderEgg());
    RenderingRegistry.registerEntityRenderingHandler(EntityCustomDragon.class, (Render)new RenderCustomDragon());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
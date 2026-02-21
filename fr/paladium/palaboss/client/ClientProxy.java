package fr.paladium.palaboss.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaboss.client.model.ModelDemoEntity;
import fr.paladium.palaboss.client.render.RenderDemoEntity;
import fr.paladium.palaboss.common.CommonProxy;
import fr.paladium.palaboss.common.entity.impl.DemoEntity;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    if (ForgeEnv.isDev())
      RenderingRegistry.registerEntityRenderingHandler(DemoEntity.class, (Render)new RenderDemoEntity(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelDemoEntity())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
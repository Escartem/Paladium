package fr.paladium.palawarzoneevent.module.largage.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palawarzoneevent.module.largage.client.listener.ClientEventListener;
import fr.paladium.palawarzoneevent.module.largage.client.model.EntityLargageModel;
import fr.paladium.palawarzoneevent.module.largage.client.renderer.EntityLargageRenderer;
import fr.paladium.palawarzoneevent.module.largage.common.LargageCommonProxy;
import fr.paladium.palawarzoneevent.module.largage.common.entity.EntityLargage;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LargageClientProxy extends LargageCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    RenderingRegistry.registerEntityRenderingHandler(EntityLargage.class, (Render)new EntityLargageRenderer(RenderManager.field_78727_a, (AnimatedGeoModel)new EntityLargageModel()));
    addListener(new Class[] { ClientEventListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\client\LargageClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
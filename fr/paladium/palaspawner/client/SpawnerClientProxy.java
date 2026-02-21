package fr.paladium.palaspawner.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaspawner.client.listener.SpawnerRenderListener;
import fr.paladium.palaspawner.client.render.entity.RenderSpawner;
import fr.paladium.palaspawner.common.SpawnerCommonProxy;
import fr.paladium.palaspawner.common.entity.EntitySpawner;
import net.minecraft.client.renderer.entity.Render;

public class SpawnerClientProxy extends SpawnerCommonProxy {
  public static int ghostBlockRenderId;
  
  private static SpawnerClientProxy instance;
  
  public static SpawnerClientProxy getInstance() {
    return instance;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
    super.onPreInit(event);
    registerRenders();
    addListener(new Class[] { SpawnerRenderListener.class });
  }
  
  private void registerRenders() {
    ghostBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerEntityRenderingHandler(EntitySpawner.class, (Render)new RenderSpawner());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\client\SpawnerClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
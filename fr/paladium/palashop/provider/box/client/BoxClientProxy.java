package fr.paladium.palashop.provider.box.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.box.client.render.entity.RenderEntityBox;
import fr.paladium.palashop.provider.box.common.BoxCommonProxy;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import net.minecraft.client.renderer.entity.Render;

public class BoxClientProxy extends BoxCommonProxy {
  @ProviderListener
  public void onModPreInit(ModPreInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initRender();
  }
  
  private void initRender() {
    RenderingRegistry.registerEntityRenderingHandler(EntityBox.class, (Render)new RenderEntityBox());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\BoxClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
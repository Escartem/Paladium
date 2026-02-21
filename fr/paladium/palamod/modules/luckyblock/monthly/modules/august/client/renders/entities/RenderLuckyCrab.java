package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.entities;

import fr.paladium.palamod.modules.hunter.models.ModelCrab;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLuckyCrab extends RenderLiving {
  private static final ResourceLocation RESOURCE = new ResourceLocation("palamod", "textures/entity/lucky_crab.png");
  
  public RenderLuckyCrab() {
    super((ModelBase)new ModelCrab(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return RESOURCE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\entities\RenderLuckyCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
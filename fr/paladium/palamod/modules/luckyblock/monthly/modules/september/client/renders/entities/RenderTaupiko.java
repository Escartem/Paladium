package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities.ModelTaupiko;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTaupiko extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/taupiko.png");
  
  public RenderTaupiko() {
    super((ModelBase)new ModelTaupiko(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\entities\RenderTaupiko.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
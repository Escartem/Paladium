package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.entities;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.entities.ModelSlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSlayer extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/slayer.png");
  
  public RenderSlayer() {
    super((ModelBase)new ModelSlayer(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\renders\entities\RenderSlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
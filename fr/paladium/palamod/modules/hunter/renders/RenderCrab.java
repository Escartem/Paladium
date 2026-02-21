package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.models.ModelCrab;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCrab extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/crab.png");
  
  public RenderCrab() {
    super((ModelBase)new ModelCrab(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
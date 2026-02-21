package fr.paladium.palamod.modules.luckyblock.renders.easter;

import fr.paladium.palamod.modules.luckyblock.renders.models.easter.ModelGoldenChicken;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGoldenChicken extends RenderChicken {
  public final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/golden_chicken.png");
  
  public RenderGoldenChicken() {
    super((ModelBase)new ModelGoldenChicken(), 0.3F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\easter\RenderGoldenChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
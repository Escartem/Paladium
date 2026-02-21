package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.models.ModelMegaCreeper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMegaCreeper extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/mega_creeper.png");
  
  public RenderMegaCreeper() {
    super((ModelBase)new ModelMegaCreeper(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderMegaCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
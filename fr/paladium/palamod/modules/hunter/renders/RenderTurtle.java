package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.models.ModelTurtle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTurtle extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/turtle.png");
  
  private ResourceLocation texture0 = new ResourceLocation("palamod", "textures/entity/turtle0.png");
  
  public RenderTurtle() {
    super((ModelBase)new ModelTurtle(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    if (entity.func_70005_c_().equalsIgnoreCase("carapuce"))
      return this.texture0; 
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
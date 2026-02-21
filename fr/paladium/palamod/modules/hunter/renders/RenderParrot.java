package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.entites.EntityParrot;
import fr.paladium.palamod.modules.hunter.models.ModelParrot;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderParrot extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/parrot/parrot_0.png");
  
  public RenderParrot() {
    super((ModelBase)new ModelParrot(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    EntityParrot parrot = (EntityParrot)entity;
    return new ResourceLocation("palamod", "textures/entity/parrot/parrot_" + parrot.type + ".png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderParrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
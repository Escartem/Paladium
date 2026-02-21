package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import fr.paladium.palamod.modules.hunter.models.ModelElephant;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderElephant extends RenderLiving {
  public RenderElephant() {
    super((ModelBase)new ModelElephant(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    EntityElephant elephant = (EntityElephant)entity;
    String texture = "textures/entity/elephant/";
    if (elephant.func_70005_c_().equalsIgnoreCase("elmer"))
      texture = "textures/entity/elephant/elmer/"; 
    return new ResourceLocation("palamod", texture + (
        (elephant.func_70654_ax() == 0) ? "child" : ((elephant.func_70654_ax() == 1) ? "ado" : "adult")) + "/elephant_" + elephant
        .getColor() + (elephant.isTamed() ? "_tamed" : "") + ".png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderElephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
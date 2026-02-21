package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.models.ModelJellyFish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderJellyFish extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/jelly_fish/0.png");
  
  public RenderJellyFish() {
    super((ModelBase)new ModelJellyFish(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    if (entity instanceof EntityJellyFish) {
      EntityJellyFish jellyFish = (EntityJellyFish)entity;
      return new ResourceLocation("palamod", "textures/entity/jelly_fish/" + jellyFish
          .getType() + ".png");
    } 
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderJellyFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
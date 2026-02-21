package fr.paladium.palamod.modules.luckyblock.renders.easter;

import fr.paladium.palamod.modules.luckyblock.renders.models.easter.ModelGiantFish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGiantFish extends RenderLiving {
  private static final ResourceLocation TEXTURE = new ResourceLocation("palamod", "textures/entity/giant_fish.png");
  
  public RenderGiantFish() {
    super((ModelBase)new ModelGiantFish(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return TEXTURE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\easter\RenderGiantFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
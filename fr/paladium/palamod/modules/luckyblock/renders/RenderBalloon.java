package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.ModelBalloon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBalloon extends RenderLiving {
  public final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/ghost.png");
  
  public RenderBalloon() {
    super((ModelBase)new ModelBalloon(), 0.2F);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderBalloon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.renders.christmas;

import fr.paladium.palamod.modules.luckyblock.renders.models.christmas.ModelChristmasSnowGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderChristmasSnowGolem extends RenderLiving {
  public static final ResourceLocation TEXTURE = new ResourceLocation("palamod", "textures/entity/christmas_snow_golem.png");
  
  public RenderChristmasSnowGolem() {
    super((ModelBase)new ModelChristmasSnowGolem(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return TEXTURE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\christmas\RenderChristmasSnowGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
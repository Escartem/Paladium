package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.ModelDove;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDove extends RenderLiving {
  public RenderDove() {
    super((ModelBase)new ModelDove(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return new ResourceLocation("palamod", "textures/entity/dove.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderDove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
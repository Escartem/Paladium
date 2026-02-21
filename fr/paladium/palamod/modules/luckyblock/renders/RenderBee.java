package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.ModelBee;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBee extends RenderLiving {
  public RenderBee() {
    super((ModelBase)new ModelBee(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return new ResourceLocation("palamod", "textures/entity/bee.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
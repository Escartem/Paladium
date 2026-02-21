package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.entities;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.entities.ModelCaptainFlint;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCaptainFlint extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/flint.png");
  
  public RenderCaptainFlint() {
    super((ModelBase)new ModelCaptainFlint(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\entities\RenderCaptainFlint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
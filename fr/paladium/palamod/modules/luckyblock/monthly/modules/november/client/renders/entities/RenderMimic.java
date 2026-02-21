package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.entities;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.entities.ModelMimic;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMimic extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/models/fake_corrupted_chest.png");
  
  public RenderMimic() {
    super((ModelBase)new ModelMimic(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\renders\entities\RenderMimic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
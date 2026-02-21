package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.entites.EntityPanda;
import fr.paladium.palamod.modules.hunter.models.ModelPanda;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPanda extends RenderLiving {
  public RenderPanda() {
    super((ModelBase)new ModelPanda(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    EntityPanda panda = (EntityPanda)entity;
    return new ResourceLocation("palamod", "textures/entity/panda/panda_" + panda.getType() + (
        (panda.func_70638_az() != null) ? "_agressive" : "") + ".png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderPanda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
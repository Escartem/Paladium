package fr.paladium.palamod.modules.spellsv2.render;

import fr.paladium.palamod.modules.spellsv2.models.ModelGhost;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityGhost extends RenderLiving {
  public final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/ghost.png");
  
  public RenderEntityGhost() {
    super((ModelBase)new ModelGhost(), 0.2F);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\render\RenderEntityGhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
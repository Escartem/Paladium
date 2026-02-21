package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.ModelInvisible;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;

public class RenderInvisible extends RenderLiving {
  public RenderInvisible() {
    super((ModelBase)new ModelInvisible(), 1.0F);
  }
  
  protected ResourceLocation getEntityTexture(EntityArrow p_110775_1_) {
    return null;
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityArrow)p_110775_1_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderInvisible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
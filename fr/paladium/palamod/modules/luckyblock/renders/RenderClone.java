package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.entity.black.EntityClone;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderClone extends RenderBiped {
  private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");
  
  public RenderClone() {
    super(new ModelBiped(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((AbstractClientPlayer)((EntityClone)p_110775_1_).func_70902_q());
  }
  
  protected ResourceLocation getEntityTexture(AbstractClientPlayer p_110775_1_) {
    return (p_110775_1_ == null) ? steveTextures : p_110775_1_.func_110306_p();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
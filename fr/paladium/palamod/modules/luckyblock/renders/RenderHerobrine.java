package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.entity.EntityHerobrine;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderHerobrine extends RenderLiving {
  private static ResourceLocation herobrineTexture = new ResourceLocation("palamod:textures/entity/herobrine.png");
  
  public RenderHerobrine() {
    super((ModelBase)new ModelBiped(0.0F), 0.5F);
  }
  
  protected ResourceLocation getEntityTexture(EntityHerobrine entity) {
    return herobrineTexture;
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityHerobrine)p_110775_1_);
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderHerobrine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
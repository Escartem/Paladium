package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderWorker extends RendererLivingEntity {
  public RenderWorker() {
    super((ModelBase)new ModelBiped(0.0F), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return AbstractClientPlayer.field_110314_b;
  }
  
  protected void func_77036_a(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
    func_110777_b((Entity)entity);
    this.field_77045_g.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\entities\RenderWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
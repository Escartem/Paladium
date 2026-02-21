package fr.paladium.palamod.modules.luckyblock.renders.june;

import fr.paladium.palamod.modules.luckyblock.entity.june.EntityShark;
import fr.paladium.palamod.modules.luckyblock.renders.models.june.ModelShark;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderShark extends RenderLiving {
  public RenderShark() {
    super((ModelBase)new ModelShark(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return new ResourceLocation("palamod", "textures/entity/shark.png");
  }
  
  protected void func_77036_a(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
    p_77036_6_ = 0.0F;
    if (entity instanceof EntityShark) {
      EntityShark entityShark = (EntityShark)entity;
      if (entityShark.isShowOnlyFin())
        p_77036_6_ = 1.0F; 
    } 
    func_110777_b((Entity)entity);
    this.field_77045_g.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\june\RenderShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
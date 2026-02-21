package fr.paladium.palamod.modules.luckyblock.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderChristmasCow extends RenderCow {
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/christmas_cow.png");
  
  private static final ResourceLocation texture1 = new ResourceLocation("palamod", "textures/entity/christmas_cow_1.png");
  
  public RenderChristmasCow() {
    super((ModelBase)new ModelCow(), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(EntityCow cow) {
    return (cow.field_70173_aa % 20 < 10) ? texture : texture1;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return func_110775_a((EntityCow)entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderChristmasCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
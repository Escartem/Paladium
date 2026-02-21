package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class StrayRenderer extends RenderBiped {
  public StrayRenderer() {
    super((ModelBiped)new ModelSkeleton(), 0.4F);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return texture;
  }
  
  public static final ResourceLocation texture = new ResourceLocation("textures/entity/skeleton/stray.png");
  
  private static final ResourceLocation textureOverlay = new ResourceLocation("textures/entity/skeleton/stray_overlay.png");
  
  protected ModelBiped field_82437_k;
  
  protected ModelBiped field_82435_l;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\StrayRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
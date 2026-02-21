package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class HuskRenderer extends RenderBiped {
  public HuskRenderer() {
    super((ModelBiped)new ModelZombie(), 0.4F);
    this.scale = 1.1F;
  }
  
  protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
    if (par1EntityLiving instanceof fr.paladium.palamod.modules.back2future.entities.EntityHusk)
      GL11.glScalef(this.scale, this.scale, this.scale); 
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return texture;
  }
  
  public static final ResourceLocation texture = new ResourceLocation("textures/entity/zombie/husk.png");
  
  private float scale;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\HuskRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
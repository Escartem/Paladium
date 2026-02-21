package fr.paladium.palamod.modules.homefinder.client.render;

import fr.paladium.palamod.modules.homefinder.common.entities.EntityFakePlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFakePlayer extends RendererLivingEntity {
  public RenderFakePlayer() {
    super((ModelBase)new ModelBiped(0.0F), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return ((EntityFakePlayer)entity).getPlayerSkin();
  }
  
  protected void func_77036_a(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
    func_110777_b((Entity)entity);
    if (((EntityFakePlayer)entity).isInGhostMode()) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      this.field_77045_g.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
      GL11.glDisable(3042);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glPopMatrix();
      GL11.glDepthMask(true);
    } else {
      this.field_77045_g.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\client\render\RenderFakePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
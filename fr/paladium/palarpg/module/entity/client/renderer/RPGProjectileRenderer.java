package fr.paladium.palarpg.module.entity.client.renderer;

import com.eliotlash.mclib.utils.Interpolations;
import fr.paladium.palarpg.module.entity.EntityModule;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.opengl.GlStateManager;

public class RPGProjectileRenderer extends Render implements IGeoRenderer<RPGProjectile> {
  public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (!(entity instanceof RPGProjectile))
      return; 
    RPGProjectile projectile = (RPGProjectile)entity;
    LindwormModel<RPGProjectile> lindwormModel = projectile.getLindwormModel();
    if (lindwormModel == null) {
      EntityModule.logger.error("LindwormModel is null for " + projectile.getClass().getName(), new Object[0]);
      return;
    } 
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y + entity.field_70131_O / 2.0D, z);
    float yaw = Math.abs(Interpolations.lerpYaw(projectile.field_70126_B, projectile.field_70177_z, partialTicks) - 360.0F);
    float pitch = Interpolations.lerp(projectile.field_70127_C, projectile.field_70125_A, partialTicks) + 180.0F;
    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
    GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(-pitch, 1.0F, 0.0F, 0.0F);
    LindwormRenderer.renderModel(entity, lindwormModel);
    GlStateManager.popMatrix();
  }
  
  public GeoModelProvider<?> getGeoModelProvider() {
    return null;
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return null;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\renderer\RPGProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
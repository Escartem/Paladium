package fr.paladium.palaboss.client.render;

import fr.paladium.palaboss.common.entity.impl.DemoEntity;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDemoEntity extends GeoEntityRenderer<DemoEntity> {
  public RenderDemoEntity(RenderManager renderManager, AnimatedGeoModel<DemoEntity> modelProvider) {
    super(renderManager, modelProvider);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    GL11.glPushMatrix();
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    super.func_76986_a(entity, x, y, z, yaw, pitch);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
  
  public Color getRenderColor(Entity animatable, float partialTicks) {
    Resource.of(func_110775_a(animatable)).blocking().bindTextureOnly();
    return super.getRenderColor(animatable, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\client\render\RenderDemoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
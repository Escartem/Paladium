package fr.paladium.palajobs.client.render.boss;

import fr.paladium.lib.apollon.utils.AnimatedResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderJobBoss<T extends EntityLivingBase & IAnimatable> extends GeoEntityRenderer<T> {
  public RenderJobBoss(RenderManager renderManager, AnimatedGeoModel<T> modelProvider) {
    super(renderManager, modelProvider);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    BossStatus.func_82824_a((IBossDisplayData)entity, true);
    super.func_76986_a(entity, x, y, z, yaw, pitch);
  }
  
  public Color getRenderColor(Entity animatable, float partialTicks) {
    AnimatedResourceLocation.from(func_110775_a(animatable)).bind(animatable.field_70173_aa);
    return super.getRenderColor(animatable, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\render\boss\RenderJobBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
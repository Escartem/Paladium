package fr.paladium.palawither.client.render.entity.projectile;

import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.Collections;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.util.opengl.GlStateManager;

public class RenderLindwormWitherProjectile extends Render {
  private final LindwormModel<LindwormAnimatable> model;
  
  public RenderLindwormWitherProjectile(@NonNull ResourceLocation model, ResourceLocation animation, @NonNull ResourceLocation texture) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    GeoModel geoModel = LindwormLoader.loadModel(model);
    AnimationFile animationFile = (animation == null) ? null : LindwormLoader.loadAnimation(animation);
    Resource resource = Resource.of(texture).nearest();
    this.model = new LindwormModel(geoModel, animationFile, resource, (lindwormModel, entity) -> (new LindwormAnimatable(lindwormModel, entity)).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (!(entity instanceof EntityWitherProjectile))
      return; 
    EntityWitherProjectile projectile = (EntityWitherProjectile)entity;
    if (projectile.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    GL11.glRotatef(-projectile.field_70177_z, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-projectile.field_70125_A, 1.0F, 0.0F, 0.0F);
    GlStateManager.pushMatrix();
    GlStateManager.translate(0.0F, 0.01F, 0.0F);
    AnimationEvent<LindwormAnimatable> animEvent = new AnimationEvent(this.model.getAnimatable((Entity)projectile), 0.0F, 0.0F, partialTicks, false, Collections.singletonList(new EntityModelData()));
    LindwormRenderer.renderModel((Entity)projectile, this.model, animEvent);
    GlStateManager.popMatrix();
    GlStateManager.popMatrix();
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\entity\projectile\RenderLindwormWitherProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
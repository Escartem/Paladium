package fr.paladium.palawither.client.render.entity;

import com.eliotlash.mclib.utils.Interpolations;
import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.Collections;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.util.math.MathHelper;
import software.bernie.geckolib3.util.opengl.GlStateManager;

public class RenderLindwormWither extends Render {
  private static final LindwormTransformProperty EMPTY_TRANSFORM = new LindwormTransformProperty();
  
  private final LindwormModel<LindwormAnimatable> model;
  
  public LindwormModel<LindwormAnimatable> getModel() {
    return this.model;
  }
  
  public RenderLindwormWither(@NonNull ResourceLocation model, @NonNull ResourceLocation animation, @NonNull ResourceLocation texture) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (animation == null)
      throw new NullPointerException("animation is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    GeoModel geoModel = LindwormLoader.loadModel(model);
    AnimationFile animationFile = LindwormLoader.loadAnimation(animation);
    Resource resource = Resource.of(texture).nearest();
    this.model = new LindwormModel(geoModel, animationFile, resource, (lindwormModel, entity) -> (new LindwormAnimatable(lindwormModel, entity)).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (!(entity instanceof EntityWitherBase))
      return; 
    EntityWitherBase wither = (EntityWitherBase)entity;
    if (wither.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    float yaw = Interpolations.lerpYaw(wither.field_70126_B, wither.field_70177_z, partialTicks);
    float preYaw = Interpolations.lerpYaw(wither.field_70758_at, wither.func_70079_am(), partialTicks);
    float netHeadYaw = preYaw - yaw;
    float headPitch = Interpolations.lerp(wither.field_70127_C, wither.field_70125_A, partialTicks);
    float ticks = wither.field_70173_aa + partialTicks;
    applyRotations((EntityLivingBase)wither, ticks, yaw, partialTicks);
    float limbSwing = 0.0F;
    float limbSwingAmount = 0.0F;
    if (wither.func_70089_S()) {
      limbSwingAmount = Interpolations.lerp(wither.field_70722_aY, wither.field_70721_aZ, partialTicks);
      limbSwing = wither.field_70754_ba - wither.field_70721_aZ * (1.0F - partialTicks);
      if (limbSwingAmount > 1.0F)
        limbSwingAmount = 1.0F; 
    } 
    EntityModelData entityModelData = new EntityModelData();
    entityModelData.headPitch = -headPitch;
    entityModelData.netHeadYaw = -netHeadYaw;
    GlStateManager.pushMatrix();
    GlStateManager.translate(0.0F, 0.01F, 0.0F);
    AnimationEvent<LindwormAnimatable> animEvent = new AnimationEvent(this.model.getAnimatable((Entity)wither), limbSwing, limbSwingAmount, partialTicks, (limbSwingAmount <= -0.15F || limbSwingAmount >= 0.15F), Collections.singletonList(entityModelData));
    LindwormRenderer.renderModel((Entity)wither, this.model, EMPTY_TRANSFORM, bone -> !"root2".equals(bone.name), animEvent);
    if (wither.field_70737_aN > 0 || wither.field_70725_aQ > 0) {
      GL11.glDisable(3553);
      GL11.glDisable(3008);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthFunc(514);
      LindwormRenderer.renderModel((Entity)wither, this.model, new Color(wither.func_70013_c(partialTicks), 0.0F, 0.0F, 0.4F), animEvent);
      GL11.glDepthFunc(515);
      GL11.glDisable(3042);
      GL11.glEnable(3008);
      GL11.glEnable(3553);
    } 
    if (wither.isArmored()) {
      float opacity = (float)((Math.sin(6.283185307179586D * (System.currentTimeMillis() % 4000L) / 2000.0D) + 1.0D) / 2.0D) + 0.5F;
      LindwormRenderer.renderModel((Entity)wither, this.model, EMPTY_TRANSFORM, bone -> "root2".equals(bone.name), Color.WHITE.copyAlpha(opacity), true, animEvent);
    } 
    GlStateManager.popMatrix();
    GlStateManager.popMatrix();
  }
  
  protected void applyRotations(EntityLivingBase entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
    if (!entityLiving.func_70608_bn())
      GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F); 
    if (entityLiving.field_70725_aQ > 0) {
      float f = (entityLiving.field_70725_aQ + partialTicks - 1.0F) / 20.0F * 1.6F;
      f = MathHelper.sqrt(f);
      if (f > 1.0F)
        f = 1.0F; 
      GlStateManager.rotate(f * 90.0F, 0.0F, 0.0F, 1.0F);
    } 
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\entity\RenderLindwormWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
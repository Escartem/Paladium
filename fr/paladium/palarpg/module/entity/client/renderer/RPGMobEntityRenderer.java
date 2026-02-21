package fr.paladium.palarpg.module.entity.client.renderer;

import com.eliotlash.mclib.utils.Interpolations;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.zephyrui.lib.color.Color;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.math.MathHelper;
import software.bernie.geckolib3.util.opengl.GlStateManager;

public class RPGMobEntityRenderer extends Render implements IGeoRenderer<RPGMobEntity> {
  public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (entity instanceof RPGMobEntity) {
      RPGMobEntity rpgEntity = (RPGMobEntity)entity;
      if (rpgEntity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
        return; 
      GlStateManager.pushMatrix();
      GlStateManager.translate(x, y, z);
      if (rpgEntity.getScaleAnimator() != null) {
        rpgEntity.getScaleAnimator().update();
        float scale = rpgEntity.getScaleAnimator().getValue();
        GlStateManager.scale(scale, scale, scale);
      } 
      boolean shouldSit = (rpgEntity.field_70154_o != null && rpgEntity.field_70154_o.shouldRiderSit());
      EntityModelData entityModelData = new EntityModelData();
      entityModelData.isSitting = shouldSit;
      entityModelData.isChild = rpgEntity.func_70631_g_();
      float f = Interpolations.lerpYaw(rpgEntity.field_70126_B, rpgEntity.field_70177_z, partialTicks);
      float f1 = Interpolations.lerpYaw(rpgEntity.field_70758_at, rpgEntity.func_70079_am(), partialTicks);
      float netHeadYaw = f1 - f;
      if (shouldSit && rpgEntity.field_70154_o instanceof EntityLivingBase) {
        EntityLivingBase livingentity = (EntityLivingBase)entity.field_70154_o;
        f = Interpolations.lerpYaw(livingentity.field_70760_ar, livingentity.field_70761_aq, partialTicks);
        netHeadYaw = f1 - f;
        float f3 = MathHelper.wrapDegrees(netHeadYaw);
        if (f3 < -85.0F)
          f3 = -85.0F; 
        if (f3 >= 85.0F)
          f3 = 85.0F; 
        f = f1 - f3;
        if (f3 * f3 > 2500.0F)
          f += f3 * 0.2F; 
        netHeadYaw = f1 - f;
      } 
      float headPitch = Interpolations.lerp(rpgEntity.field_70127_C, rpgEntity.field_70125_A, partialTicks);
      float f7 = handleRotationFloat((EntityLivingBase)rpgEntity, partialTicks);
      applyRotations((EntityLivingBase)rpgEntity, f7, f, partialTicks);
      float limbSwingAmount = 0.0F;
      float limbSwing = 0.0F;
      if (!shouldSit && rpgEntity.func_70089_S()) {
        limbSwingAmount = Interpolations.lerp(rpgEntity.field_70722_aY, rpgEntity.field_70721_aZ, partialTicks);
        limbSwing = rpgEntity.field_70754_ba - rpgEntity.field_70721_aZ * (1.0F - partialTicks);
        if (rpgEntity.func_70631_g_())
          limbSwing *= 3.0F; 
        if (limbSwingAmount > 1.0F)
          limbSwingAmount = 1.0F; 
      } 
      entityModelData.headPitch = -headPitch;
      entityModelData.netHeadYaw = -netHeadYaw;
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 0.01F, 0.0F);
      float brightness = rpgEntity.func_70013_c(partialTicks);
      AnimationEvent<RPGMobEntity> animEvent = new AnimationEvent(rpgEntity, limbSwing, limbSwingAmount, partialTicks, (limbSwingAmount <= -0.15F || limbSwingAmount >= 0.15F), Collections.singletonList(entityModelData));
      LindwormRenderer.renderModel((Entity)rpgEntity, rpgEntity.getLindwormModel(), animEvent);
      if (rpgEntity.isBoosted()) {
        float time = (entity.field_70173_aa + partialTicks) * 0.05F;
        float pulse = (float)(0.30000001192092896D + 0.10000000149011612D * Math.sin((time * 3.0F)));
        GL11.glDisable(3553);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthFunc(514);
        LindwormRenderer.renderModel((Entity)rpgEntity, rpgEntity.getLindwormModel(), new LindwormTransformProperty(), Color.RAINBOW().copyAlpha(pulse), true, animEvent);
        GL11.glDepthFunc(515);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(3553);
      } 
      if (rpgEntity.field_70737_aN > 0 || rpgEntity.field_70725_aQ > 0 || (rpgEntity.getAnimated() != null && rpgEntity.getAnimated().isDeathAnimation())) {
        GL11.glDisable(3553);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthFunc(514);
        LindwormRenderer.renderModel((Entity)rpgEntity, rpgEntity.getLindwormModel(), new Color(brightness, 0.0F, 0.0F, 0.4F), animEvent);
        GL11.glDepthFunc(515);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(3553);
      } 
      GlStateManager.popMatrix();
      GlStateManager.popMatrix();
    } 
  }
  
  protected float handleRotationFloat(EntityLivingBase livingBase, float partialTicks) {
    return livingBase.field_70173_aa + partialTicks;
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
  
  public GeoModelProvider<?> getGeoModelProvider() {
    return null;
  }
  
  public ResourceLocation getTextureLocation(Entity arg0) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\renderer\RPGMobEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
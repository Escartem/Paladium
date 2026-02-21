package fr.paladium.palarpg.module.equipment.client.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.accessor.client.model.ModelRendererAccessor;
import fr.paladium.palarpg.module.equipment.common.item.RPGArmorType;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGArmorItemData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
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

public class RPGArmorRenderListener {
  private static final Cache<String, LindwormModel<LindwormAnimatable>> MODEL_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  @SubscribeEvent
  public void onRenderArmor(RenderPlayerEvent.SetArmorModel event) {
    if (event.stack != null && event.result == -1 && event.stack.func_77973_b() instanceof RPGItemArmor) {
      event.result = -2;
      RPGItemArmor armor = (RPGItemArmor)event.stack.func_77973_b();
      RPGArmorType armorType = armor.getType();
      RPGArmorItemData data = armor.getItemData();
      LindwormModel<LindwormAnimatable> armorModel = (LindwormModel<LindwormAnimatable>)MODEL_CACHE.getIfPresent(armor.getId());
      if (armorModel == null) {
        GeoModel geoModel = LindwormLoader.loadModel(data.getModelFile());
        armorModel = new LindwormModel(geoModel, LindwormAnimatable::new);
        if (data.getAnimationFile() != null) {
          AnimationFile animation = LindwormLoader.loadAnimation(data.getAnimationFile());
          armorModel.setAnimationFile(animation).setAnimatableBuilder((model, entity) -> new LindwormAnimatable(model, entity, 0.0F, new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
        } 
        MODEL_CACHE.put(armor.getId(), armorModel);
      } 
      boolean isTexture2DEnabled = GL11.glIsEnabled(3553);
      if (!isTexture2DEnabled)
        GL11.glEnable(3553); 
      for (RPGArmorRendererAnchor anchor : armorType.getAnchors()) {
        GL11.glPushMatrix();
        patchArmorTranslations(event.entity, anchor, event.renderer, event.partialRenderTick);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(data.getModelTexture());
        LindwormRenderer.renderModel(event.entity, armorModel, new LindwormTransformProperty(), bone -> bone.name.equalsIgnoreCase(anchor.name().toLowerCase()));
        GL11.glPopMatrix();
      } 
      if (!isTexture2DEnabled)
        GL11.glDisable(3553); 
    } 
  }
  
  private static void patchArmorTranslations(Entity entity, RPGArmorRendererAnchor anchor, RenderPlayer playerRenderer, float partialTicks) {
    ModelRenderer modelRenderer = anchor.getModel(playerRenderer.field_77109_a);
    if (modelRenderer == null)
      return; 
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    if (modelRenderer instanceof ModelRendererAccessor) {
      List<ModelRenderer> parents = new ArrayList<>();
      ModelRenderer m = modelRenderer;
      while (m instanceof ModelRendererAccessor) {
        ModelRenderer p = ((ModelRendererAccessor)m).getParent();
        if (p == null)
          break; 
        parents.add(0, p);
        m = p;
      } 
      for (ModelRenderer pr : parents) {
        GL11.glTranslatef(-pr.field_82906_o, -pr.field_82908_p, pr.field_82907_q);
        GL11.glTranslatef(-pr.field_78800_c / 16.0F, -pr.field_78797_d / 16.0F, pr.field_78798_e / 16.0F);
        GL11.glRotated(Math.toDegrees(pr.field_78808_h), 0.0D, 0.0D, 1.0D);
        GL11.glRotated(-Math.toDegrees(pr.field_78796_g), 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-Math.toDegrees(pr.field_78795_f), 1.0D, 0.0D, 0.0D);
        GL11.glTranslatef(pr.field_78800_c / 16.0F, pr.field_78797_d / 16.0F, -pr.field_78798_e / 16.0F);
      } 
    } 
    GL11.glTranslatef(-modelRenderer.field_82906_o, -modelRenderer.field_82908_p, modelRenderer.field_82907_q);
    GL11.glTranslatef(-modelRenderer.field_78800_c / 16.0F, -modelRenderer.field_78797_d / 16.0F, modelRenderer.field_78798_e / 16.0F);
    GL11.glRotated(Math.toDegrees(modelRenderer.field_78808_h), 0.0D, 0.0D, 1.0D);
    GL11.glRotated(-Math.toDegrees(modelRenderer.field_78796_g), 0.0D, 1.0D, 0.0D);
    GL11.glRotated(-Math.toDegrees(modelRenderer.field_78795_f), 1.0D, 0.0D, 0.0D);
    GL11.glTranslatef(modelRenderer.field_78800_c / 16.0F, modelRenderer.field_78797_d / 16.0F, -modelRenderer.field_78798_e / 16.0F);
    GL11.glTranslatef(0.0F, -1.5F, 0.0F);
    if (entity.func_70093_af())
      GL11.glTranslatef(-anchor.getSneakTranslationX() / 16.0F, -anchor.getSneakTranslationY() / 16.0F, anchor.getSneakTranslationZ() / 16.0F); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\listener\RPGArmorRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
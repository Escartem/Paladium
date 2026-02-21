package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.EntityAncientHammerEffect;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.LindwormBridge;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class EntityAncientHammerEffectRenderer extends Render {
  private static final String PATH = "textures/items/ancient_hammer/effect/";
  
  private static final Map<LegendaryStone.Effect, AncientHammerAnimationData> ANIMATION_DATA = new HashMap<>();
  
  private static final ResourceBuilder BUILDER = ResourceBuilder.create().async().nearest();
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    if (!(entity instanceof EntityAncientHammerEffect) || entity.field_70128_L)
      return; 
    EntityAncientHammerEffect entityEffect = (EntityAncientHammerEffect)entity;
    if (entityEffect.getEffectType() == null)
      return; 
    LegendaryStone.Effect effectType = entityEffect.getEffectType();
    AncientHammerAnimationData modelData = getAnimationData(effectType);
    if (modelData == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(x, y + 0.15D, z);
    GL11.glRotated((-entity.field_70177_z + 180.0F), 0.0D, 1.0D, 0.0D);
    LindwormRenderer.renderModel(entity, modelData.getHammerModel());
    if (modelData.getPlayerModel() != null) {
      ResourceLocation resourceLocation = AbstractClientPlayer.field_110314_b;
      resourceLocation = AbstractClientPlayer.func_110311_f(entityEffect.getPlayer());
      downloadImageSkin(resourceLocation, entityEffect.getPlayer());
      BUILDER.of(resourceLocation).nearest().bind(() -> LindwormRenderer.renderModel(entity, modelData.getPlayerModel()));
    } 
    GL11.glPopMatrix();
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
  
  private void downloadImageSkin(ResourceLocation texture, String name) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(texture);
    if (object == null) {
      object = new ThreadDownloadImageData(null, String.format("https://minotar.net/skin/%s", new Object[] { StringUtils.func_76338_a(name) }), AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload());
      texturemanager.func_110579_a(texture, (ITextureObject)object);
    } 
  }
  
  private static LindwormModel<LindwormAnimatable> loadModel(String modelLocation, Resource texture) {
    GeoModel model = LindwormBridge.MODEL_LOADER.loadModel((ResourceLocation)MCResource.of("palamod", modelLocation + "/model.json"));
    if (model == null)
      return null; 
    AnimationFile animationFile = LindwormBridge.ANIMATION_LOADER.loadAllAnimations(LindwormBridge.MOLANG_PARSER, (ResourceLocation)MCResource.of("palamod", modelLocation + "/animation.json"));
    if (animationFile == null)
      return null; 
    return new LindwormModel(model, animationFile, texture, fr.paladium.palamod.modules.paladium.common.items.sword.ancient.client.renderer.animation.EntityHammerEffectAnimatable::new);
  }
  
  private static AncientHammerAnimationData getAnimationData(LegendaryStone.Effect effect) {
    if (!ANIMATION_DATA.containsKey(effect))
      ANIMATION_DATA.put(effect, new AncientHammerAnimationData(
            loadModel("textures/items/ancient_hammer/effect/" + effect.name().toLowerCase() + "/anim", Resource.of((ResourceLocation)MCResource.of("palamod", "textures/items/ancient_hammer/effect/" + effect.name().toLowerCase() + "/anim/texture.png")).nearest()), 
            loadModel("textures/items/ancient_hammer/effect/" + effect.name().toLowerCase() + "/player", null))); 
    return ANIMATION_DATA.get(effect);
  }
  
  private static class AncientHammerAnimationData {
    private final LindwormModel<LindwormAnimatable> hammerModel;
    
    private final LindwormModel<LindwormAnimatable> playerModel;
    
    public AncientHammerAnimationData(LindwormModel<LindwormAnimatable> hammerModel, LindwormModel<LindwormAnimatable> playerModel) {
      this.hammerModel = hammerModel;
      this.playerModel = playerModel;
    }
    
    public LindwormModel<LindwormAnimatable> getHammerModel() {
      return this.hammerModel;
    }
    
    public LindwormModel<LindwormAnimatable> getPlayerModel() {
      return this.playerModel;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\EntityAncientHammerEffectRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
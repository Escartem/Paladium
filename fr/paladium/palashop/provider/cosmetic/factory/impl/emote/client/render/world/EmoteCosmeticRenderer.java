package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.world;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.apache.commons.lang3.tuple.Pair;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.snapshot.BoneSnapshot;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class EmoteCosmeticRenderer {
  private static final Map<ModelRenderer, ModelRendererTransform> MODEL_CACHE = new HashMap<>();
  
  private static final List<String> RENDERING_LIST = new ArrayList<>();
  
  public static void render(Entity entity, @NonNull ModelBiped model) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (!(entity instanceof net.minecraft.entity.player.EntityPlayer) || !isRendering(entity))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get(entity);
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(EmoteCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    for (ICosmetic cosmetic : equippedCosmetic.getCosmetics()) {
      if (cosmetic instanceof EmoteCosmeticClient) {
        EmoteCosmeticClient emote = (EmoteCosmeticClient)cosmetic;
        if (emote.isLoaded()) {
          LindwormAnimatable animatable = (LindwormAnimatable)emote.getEmoteModel().getAnimatable(entity);
          if (animatable != null && animatable.getCurrentAnimation() != null)
            if (((EmoteCosmetic.EmoteCosmeticProperties)emote.getProperties()).getCancel().shouldCancel(entity)) {
              animatable.stopAnimation();
              if (emote.hasEffectModel() && emote.getEffectModel().isAnimated()) {
                LindwormAnimatable effectAnimatable = (LindwormAnimatable)emote.getEffectModel().getAnimatable(entity);
                if (effectAnimatable != null)
                  effectAnimatable.stopAnimation(); 
              } 
            } else {
              emote.getEmoteModel().setLivingAnimations(entity);
              AnimationData animationData = animatable.getFactory().getOrCreateAnimationData(Integer.valueOf(entity.func_145782_y()));
              for (Map.Entry<String, Pair<IBone, BoneSnapshot>> entry : (Iterable<Map.Entry<String, Pair<IBone, BoneSnapshot>>>)animationData.getBoneSnapshotCollection().entrySet()) {
                BoneSnapshot bone = (BoneSnapshot)((Pair)entry.getValue()).getRight();
                ModelRenderer[] models = getModel(model, bone);
                for (ModelRenderer modelPart : models)
                  transform(modelPart, bone); 
              } 
              break;
            }  
        } 
      } 
    } 
  }
  
  @NonNull
  private static ModelRenderer[] getModel(@NonNull ModelBiped model, @NonNull BoneSnapshot bone) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (bone == null)
      throw new NullPointerException("bone is marked non-null but is null"); 
    switch (bone.name) {
      case "head":
        return new ModelRenderer[] { model.field_78116_c, model.field_78114_d };
      case "body":
        return new ModelRenderer[] { model.field_78115_e };
      case "left_arm":
        return new ModelRenderer[] { model.field_78113_g };
      case "right_arm":
        return new ModelRenderer[] { model.field_78112_f };
      case "left_leg":
        return new ModelRenderer[] { model.field_78124_i };
      case "right_leg":
        return new ModelRenderer[] { model.field_78123_h };
    } 
    return new ModelRenderer[0];
  }
  
  public static void transform(@NonNull ModelRenderer model, @NonNull BoneSnapshot bone) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (bone == null)
      throw new NullPointerException("bone is marked non-null but is null"); 
    cacheTransform(model);
    float rotationX = bone.isCurrentlyRunningRotationAnimation ? -bone.rotationValueX : model.field_78795_f;
    float rotationY = bone.isCurrentlyRunningRotationAnimation ? -bone.rotationValueY : model.field_78796_g;
    float rotationZ = bone.isCurrentlyRunningRotationAnimation ? bone.rotationValueZ : model.field_78808_h;
    float positionX = bone.isCurrentlyRunningPositionAnimation ? (bone.positionOffsetX / 16.0F) : model.field_82906_o;
    float positionY = bone.isCurrentlyRunningPositionAnimation ? (-bone.positionOffsetY / 16.0F) : model.field_82908_p;
    float positionZ = bone.isCurrentlyRunningPositionAnimation ? (bone.positionOffsetZ / 16.0F) : model.field_82907_q;
    (new ModelRendererTransform(rotationX, rotationY, rotationZ, positionX, positionY, positionZ)).apply(model);
  }
  
  public static void reset() {
    Map<ModelRenderer, ModelRendererTransform> modelCache = MODEL_CACHE;
    if (modelCache == null)
      return; 
    for (Map.Entry<ModelRenderer, ModelRendererTransform> entry : modelCache.entrySet())
      ((ModelRendererTransform)entry.getValue()).apply(entry.getKey()); 
  }
  
  public static ModelRendererTransform getTransform(@NonNull ModelRenderer model) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    return MODEL_CACHE.get(model);
  }
  
  public static void cacheTransform(@NonNull ModelRenderer model) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    MODEL_CACHE.putIfAbsent(model, new ModelRendererTransform(model));
  }
  
  public static void addRendering(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    RENDERING_LIST.add(UUIDUtils.toString(entity));
  }
  
  public static boolean isRendering(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return RENDERING_LIST.contains(UUIDUtils.toString(entity));
  }
  
  public static boolean removeRendering(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return RENDERING_LIST.remove(UUIDUtils.toString(entity));
  }
  
  public static class ModelRendererTransform {
    private final float rotationX;
    
    private final float rotationY;
    
    private final float rotationZ;
    
    private final float positionX;
    
    private final float positionY;
    
    private final float positionZ;
    
    public ModelRendererTransform(float rotationX, float rotationY, float rotationZ, float positionX, float positionY, float positionZ) {
      this.rotationX = rotationX;
      this.rotationY = rotationY;
      this.rotationZ = rotationZ;
      this.positionX = positionX;
      this.positionY = positionY;
      this.positionZ = positionZ;
    }
    
    public float getRotationX() {
      return this.rotationX;
    }
    
    public float getRotationY() {
      return this.rotationY;
    }
    
    public float getRotationZ() {
      return this.rotationZ;
    }
    
    public float getPositionX() {
      return this.positionX;
    }
    
    public float getPositionY() {
      return this.positionY;
    }
    
    public float getPositionZ() {
      return this.positionZ;
    }
    
    public ModelRendererTransform(@NonNull ModelRenderer model) {
      if (model == null)
        throw new NullPointerException("model is marked non-null but is null"); 
      this.rotationX = model.field_78795_f;
      this.rotationY = model.field_78796_g;
      this.rotationZ = model.field_78808_h;
      this.positionX = model.field_82906_o;
      this.positionY = model.field_82908_p;
      this.positionZ = model.field_82907_q;
    }
    
    public void apply(@NonNull ModelRenderer model) {
      if (model == null)
        throw new NullPointerException("model is marked non-null but is null"); 
      model.field_78795_f = this.rotationX;
      model.field_78796_g = this.rotationY;
      model.field_78808_h = this.rotationZ;
      model.field_82906_o = this.positionX;
      model.field_82908_p = this.positionY;
      model.field_82907_q = this.positionZ;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\render\world\EmoteCosmeticRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
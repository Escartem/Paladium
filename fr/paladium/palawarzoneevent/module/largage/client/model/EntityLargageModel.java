package fr.paladium.palawarzoneevent.module.largage.client.model;

import fr.paladium.palawarzoneevent.module.largage.common.entity.EntityLargage;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class EntityLargageModel extends AnimatedTickingGeoModel<EntityLargage> {
  private static final ResourceLocation MODEL = new ResourceLocation("palawarzoneevent", "geo/largage.geo.json");
  
  private static final ResourceLocation ANIMATION = new ResourceLocation("palawarzoneevent", "animations/largage.animation.json");
  
  private static final ResourceLocation TEXTURE_0 = new ResourceLocation("palawarzoneevent", "textures/entity/largage/largage_0.png");
  
  private static final ResourceLocation TEXTURE_1 = new ResourceLocation("palawarzoneevent", "textures/entity/largage/largage_1.png");
  
  private static final ResourceLocation TEXTURE_2 = new ResourceLocation("palawarzoneevent", "textures/entity/largage/largage_2.png");
  
  public ResourceLocation getAnimationFileLocation(EntityLargage entity) {
    return ANIMATION;
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return MODEL;
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    if (entity instanceof EntityLargage) {
      EntityLargage largage = (EntityLargage)entity;
      float healthPercentage = largage.func_110143_aJ() / largage.func_110138_aP();
      if (healthPercentage > 0.66F)
        return TEXTURE_0; 
      if (healthPercentage > 0.33F)
        return TEXTURE_1; 
      if (healthPercentage >= 0.0F)
        return TEXTURE_2; 
    } 
    return TEXTURE_0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\client\model\EntityLargageModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
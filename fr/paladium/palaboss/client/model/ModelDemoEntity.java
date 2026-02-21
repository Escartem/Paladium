package fr.paladium.palaboss.client.model;

import fr.paladium.palaboss.common.entity.impl.DemoEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelDemoEntity extends AnimatedTickingGeoModel<DemoEntity> {
  private final ResourceLocation animation = new ResourceLocation("palaboss", "animations/demo_entity.animation.json");
  
  private final ResourceLocation model = new ResourceLocation("palaboss", "geo/demo_entity.geo.json");
  
  private final ResourceLocation texture = new ResourceLocation("palaboss", "textures/entity/demo_entity.png");
  
  public ResourceLocation getAnimationFileLocation(DemoEntity enitty) {
    return this.animation;
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return this.model;
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\client\model\ModelDemoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
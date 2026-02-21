package fr.paladium.palajobs.client.model.boss;

import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerBoss;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelJobFarmerBoss extends AnimatedTickingGeoModel<EntityJobFarmerBoss> {
  private final ResourceLocation animation = new ResourceLocation("palajobs", "animations/palajobs/boss_farmer.animation.json");
  
  private final ResourceLocation model = new ResourceLocation("palajobs", "geo/palajobs/boss_farmer.geo.json");
  
  private final ResourceLocation texture = new ResourceLocation("palajobs", "textures/entity/boss_farmer.png");
  
  public ResourceLocation getAnimationFileLocation(EntityJobFarmerBoss entity) {
    return this.animation;
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return this.model;
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\model\boss\ModelJobFarmerBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
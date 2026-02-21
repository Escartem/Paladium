package fr.paladium.palajobs.client.model.boss;

import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerPlantBoss;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelJobFarmerPlantBoss extends AnimatedTickingGeoModel<EntityJobFarmerPlantBoss> {
  private final ResourceLocation animation = new ResourceLocation("palajobs", "animations/palajobs/boss_farmer_plant.animation.json");
  
  private final ResourceLocation model = new ResourceLocation("palajobs", "geo/palajobs/boss_farmer_plant.geo.json");
  
  private final ResourceLocation texture = new ResourceLocation("palajobs", "textures/entity/boss_farmer_plant.png");
  
  public ResourceLocation getAnimationFileLocation(EntityJobFarmerPlantBoss entity) {
    return this.animation;
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return this.model;
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\model\boss\ModelJobFarmerPlantBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
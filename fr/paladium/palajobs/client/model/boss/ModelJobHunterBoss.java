package fr.paladium.palajobs.client.model.boss;

import fr.paladium.palajobs.core.entity.boss.EntityJobHunterBoss;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelJobHunterBoss extends AnimatedTickingGeoModel<EntityJobHunterBoss> {
  private final ResourceLocation animation = new ResourceLocation("palajobs", "animations/palajobs/boss_hunter.animation.json");
  
  private final ResourceLocation model = new ResourceLocation("palajobs", "geo/palajobs/boss_hunter.geo.json");
  
  private final ResourceLocation texture = new ResourceLocation("palajobs", "textures/entity/boss_hunter.png");
  
  public ResourceLocation getAnimationFileLocation(EntityJobHunterBoss entity) {
    return this.animation;
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return this.model;
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\model\boss\ModelJobHunterBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityBodyGuard;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelBodyGuard extends AnimatedTickingGeoModel<EntityBodyGuard> {
  public static final String ANIMATION_FILE_PATH = "animations/luckyblock/bodyguard.animation.json";
  
  public static final String MODEL_FILE_PATH = "geo/luckyblock/bodyguard.geo.json";
  
  public static final String TEXTURE_FILE_PATH = "textures/entity/bodyguard.png";
  
  public ResourceLocation getAnimationFileLocation(EntityBodyGuard entity) {
    return new ResourceLocation("palamod", "animations/luckyblock/bodyguard.animation.json");
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return new ResourceLocation("palamod", "geo/luckyblock/bodyguard.geo.json");
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return new ResourceLocation("palamod", "textures/entity/bodyguard.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\models\entities\ModelBodyGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
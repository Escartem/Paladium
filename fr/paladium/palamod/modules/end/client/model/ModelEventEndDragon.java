package fr.paladium.palamod.modules.end.client.model;

import fr.paladium.palamod.modules.end.common.entity.EntityEventEndDragon;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelEventEndDragon extends AnimatedTickingGeoModel<EntityEventEndDragon> {
  public ResourceLocation getAnimationFileLocation(EntityEventEndDragon entity) {
    return new ResourceLocation("palamod", "animations/mohiras.animation.json");
  }
  
  public ResourceLocation getModelLocation(Entity arg0) {
    return new ResourceLocation("palamod", "geo/mohiras.geo.json");
  }
  
  public ResourceLocation getTextureLocation(Entity arg0) {
    return new ResourceLocation("palamod", "textures/entity/mohiras.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\client\model\ModelEventEndDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
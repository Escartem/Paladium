package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.entity;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntitySupersonicRocket;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class SupersonicRocketModel extends AnimatedTickingGeoModel<EntitySupersonicRocket> {
  public ResourceLocation getAnimationFileLocation(EntitySupersonicRocket entity) {
    return new ResourceLocation("palamod", "animations/hypersonic_rocket.animation.json");
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return new ResourceLocation("palamod", "geo/hypersonic_rocket.geo.json");
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return new ResourceLocation("palamod", "textures/entities/hypersonic_rocket.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\entity\SupersonicRocketModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.pet.client.models.entities;

import fr.paladium.pet.client.ui.utils.PetUIUtils;
import fr.paladium.pet.common.entity.EntityDummyPet;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class PetModel extends AnimatedTickingGeoModel<EntityDummyPet> {
  private static final String PETS_FOLDER = "pets";
  
  public ResourceLocation getAnimationFileLocation(EntityDummyPet entity) {
    return PetUIUtils.getGeckoAnimationFileLocation("pets", entity.getSkinId());
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return PetUIUtils.getGeckoModelLocation("pets", ((EntityDummyPet)entity).getSkinId());
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return PetUIUtils.getGeckoTextureLocation("pets", ((EntityDummyPet)entity).getSkinId());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\models\entities\PetModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
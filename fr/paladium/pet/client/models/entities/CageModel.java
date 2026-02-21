package fr.paladium.pet.client.models.entities;

import fr.paladium.pet.client.ui.utils.PetUIUtils;
import fr.paladium.pet.common.entity.EntityPetCage;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class CageModel extends AnimatedTickingGeoModel<EntityPetCage> {
  private static final String CAGE_FOLDER = "cage";
  
  private static final String CAGE_MODEL = "cage";
  
  public ResourceLocation getAnimationFileLocation(EntityPetCage entity) {
    return PetUIUtils.getGeckoAnimationFileLocation("cage", "cage");
  }
  
  public ResourceLocation getModelLocation(Entity entity) {
    return PetUIUtils.getGeckoModelLocation("cage", "cage");
  }
  
  public ResourceLocation getTextureLocation(Entity entity) {
    return PetUIUtils.getGeckoTextureLocation("blocks", "cage");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\models\entities\CageModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
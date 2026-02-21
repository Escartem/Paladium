package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.pet.common.PetCommonProxy;

public class SkinData {
  private final String skinId;
  
  public String getSkinId() {
    return this.skinId;
  }
  
  public SkinData(String pet) {
    this.skinId = pet;
  }
  
  public static SkinData defaultData() {
    String pet = PetCommonProxy.DEFAULT_PETS.get(0);
    return new SkinData(pet);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\SkinData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
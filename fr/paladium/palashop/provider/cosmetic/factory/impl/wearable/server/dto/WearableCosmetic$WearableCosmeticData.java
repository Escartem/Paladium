package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticData;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.part.GeckoCosmeticData;
import lombok.NonNull;

public class WearableCosmeticData extends CosmeticData {
  private GeckoCosmeticData model;
  
  public void setModel(GeckoCosmeticData model) {
    this.model = model;
  }
  
  public String toString() {
    return "WearableCosmetic.WearableCosmeticData(model=" + getModel() + ")";
  }
  
  public GeckoCosmeticData getModel() {
    return this.model;
  }
  
  public WearableCosmeticData(@NonNull GeckoCosmeticData model, @NonNull String thumbnail, @NonNull String thumbnailAnimation) {
    super(thumbnail, thumbnailAnimation);
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (thumbnail == null)
      throw new NullPointerException("thumbnail is marked non-null but is null"); 
    if (thumbnailAnimation == null)
      throw new NullPointerException("thumbnailAnimation is marked non-null but is null"); 
    this.model = model;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\server\dto\WearableCosmetic$WearableCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.CosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class WearableCosmeticClient extends CosmeticClient<WearableCosmetic.WearableCosmeticProperties> {
  private WearableCosmetic.WearableCosmeticType type;
  
  private LindwormModel<LindwormAnimatable> model;
  
  public String toString() {
    return "WearableCosmeticClient(type=" + getType() + ", model=" + getModel() + ")";
  }
  
  public WearableCosmetic.WearableCosmeticType getType() {
    return this.type;
  }
  
  public LindwormModel<LindwormAnimatable> getModel() {
    return this.model;
  }
  
  public WearableCosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  public WearableCosmeticClient receive(@NonNull WearableCosmetic.WearableCosmeticType type, @NonNull WearableCosmetic.WearableCosmeticProperties properties) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    this.type = type;
    receive((CosmeticProperties)properties);
    return this;
  }
  
  public WearableCosmeticClient load(LindwormModel<LindwormAnimatable> model, Resource thumbnail) {
    this.model = model;
    load(thumbnail);
    return this;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)WearableCosmeticFactory.getInstance();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\dto\WearableCosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
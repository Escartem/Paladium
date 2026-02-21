package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.dto;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.CosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto.SprayCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;

public class SprayCosmeticClient extends CosmeticClient<SprayCosmetic.SprayCosmeticProperties> {
  private Resource texture;
  
  public String toString() {
    return "SprayCosmeticClient(texture=" + getTexture() + ")";
  }
  
  public Resource getTexture() {
    return this.texture;
  }
  
  public SprayCosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  public SprayCosmeticClient load(@NonNull Resource texture) {
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    this.texture = texture;
    super.load(texture);
    return this;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)SprayCosmeticFactory.getInstance();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\client\dto\SprayCosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
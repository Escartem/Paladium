package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.dto;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.CosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;

public class CloakCosmeticClient<CosmeticProperties> extends CosmeticClient {
  private Resource texture;
  
  public String toString() {
    return "CloakCosmeticClient(texture=" + getTexture() + ")";
  }
  
  public Resource getTexture() {
    return this.texture;
  }
  
  public CloakCosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  public CloakCosmeticClient load(@NonNull Resource texture, @NonNull Resource thumbnail) {
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    if (thumbnail == null)
      throw new NullPointerException("thumbnail is marked non-null but is null"); 
    this.texture = texture;
    load(thumbnail);
    return this;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)CloakCosmeticFactory.getInstance();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\dto\CloakCosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
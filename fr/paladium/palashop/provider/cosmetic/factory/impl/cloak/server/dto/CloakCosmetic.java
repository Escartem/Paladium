package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.Cosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.impl.TextureCosmeticData;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import lombok.NonNull;

public class CloakCosmetic extends Cosmetic<CosmeticProperties> {
  private final TextureCosmeticData data;
  
  public String toString() {
    return "CloakCosmetic(data=" + getData() + ")";
  }
  
  public TextureCosmeticData getData() {
    return this.data;
  }
  
  public CloakCosmetic(@NonNull String id, @NonNull CosmeticProperties properties, @NonNull TextureCosmeticData data) {
    super(id, properties);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.data = data;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)CloakCosmeticFactory.getInstance();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\server\dto\CloakCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
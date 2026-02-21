package fr.paladium.palashop.provider.cosmetic.api.request;

public class CosmeticMutationRequest {
  private final String uuid;
  
  private final String cosmeticId;
  
  private final String cosmeticFactory;
  
  public CosmeticMutationRequest(String uuid, String cosmeticId, String cosmeticFactory) {
    this.uuid = uuid;
    this.cosmeticId = cosmeticId;
    this.cosmeticFactory = cosmeticFactory;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getCosmeticId() {
    return this.cosmeticId;
  }
  
  public String getCosmeticFactory() {
    return this.cosmeticFactory;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\api\request\CosmeticMutationRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
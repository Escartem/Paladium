package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;

public class KillCosmeticProperties extends CosmeticProperties {
  private String message;
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String toString() {
    return "KillMessageCosmetic.KillCosmeticProperties(message=" + getMessage() + ")";
  }
  
  public KillCosmeticProperties() {}
  
  public KillCosmeticProperties(String message) {
    this.message = message;
  }
  
  public String getMessage() {
    return this.message;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\server\dto\KillMessageCosmetic$KillCosmeticProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
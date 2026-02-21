package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;

public class SprayCosmeticProperties extends CosmeticProperties {
  private int width;
  
  private int height;
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  public String toString() {
    return "SprayCosmetic.SprayCosmeticProperties(width=" + getWidth() + ", height=" + getHeight() + ")";
  }
  
  public SprayCosmeticProperties() {
    this.width = 1;
    this.height = 1;
  }
  
  public SprayCosmeticProperties(int width, int height) {
    this.width = 1;
    this.height = 1;
    this.width = width;
    this.height = height;
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public int getHeight() {
    return this.height;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\server\dto\SprayCosmetic$SprayCosmeticProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
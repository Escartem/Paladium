package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto;

public class SprayCosmeticData {
  private String texture;
  
  private String textureAnimation;
  
  public void setTexture(String texture) {
    this.texture = texture;
  }
  
  public void setTextureAnimation(String textureAnimation) {
    this.textureAnimation = textureAnimation;
  }
  
  public String toString() {
    return "SprayCosmetic.SprayCosmeticData(texture=" + getTexture() + ", textureAnimation=" + getTextureAnimation() + ")";
  }
  
  public SprayCosmeticData(String texture, String textureAnimation) {
    this.texture = texture;
    this.textureAnimation = textureAnimation;
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public String getTextureAnimation() {
    return this.textureAnimation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\server\dto\SprayCosmetic$SprayCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
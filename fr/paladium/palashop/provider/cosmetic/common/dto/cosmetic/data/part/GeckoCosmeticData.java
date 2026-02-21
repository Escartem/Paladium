package fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.part;

public class GeckoCosmeticData {
  private String model;
  
  private String animation;
  
  private String texture;
  
  private String textureAnimation;
  
  public void setModel(String model) {
    this.model = model;
  }
  
  public void setAnimation(String animation) {
    this.animation = animation;
  }
  
  public void setTexture(String texture) {
    this.texture = texture;
  }
  
  public void setTextureAnimation(String textureAnimation) {
    this.textureAnimation = textureAnimation;
  }
  
  public String toString() {
    return "GeckoCosmeticData(model=" + getModel() + ", animation=" + getAnimation() + ", texture=" + getTexture() + ", textureAnimation=" + getTextureAnimation() + ")";
  }
  
  public GeckoCosmeticData(String model, String animation, String texture, String textureAnimation) {
    this.model = model;
    this.animation = animation;
    this.texture = texture;
    this.textureAnimation = textureAnimation;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public String getAnimation() {
    return this.animation;
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public String getTextureAnimation() {
    return this.textureAnimation;
  }
  
  public boolean exists() {
    return (this.model != null && !this.model.isEmpty());
  }
  
  public boolean hasAnimation() {
    return (this.animation != null && !this.animation.isEmpty());
  }
  
  public boolean hasTexture() {
    return (this.texture != null && !this.texture.isEmpty());
  }
  
  public boolean hasTextureAnimation() {
    return (this.textureAnimation != null && !this.textureAnimation.isEmpty());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\cosmetic\data\part\GeckoCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
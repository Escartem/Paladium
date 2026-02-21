package fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.impl;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticData;
import lombok.NonNull;

public class TextureCosmeticData extends CosmeticData {
  private String texture;
  
  private String textureAnimation;
  
  public void setTexture(String texture) {
    this.texture = texture;
  }
  
  public void setTextureAnimation(String textureAnimation) {
    this.textureAnimation = textureAnimation;
  }
  
  public String toString() {
    return "TextureCosmeticData(texture=" + getTexture() + ", textureAnimation=" + getTextureAnimation() + ")";
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public String getTextureAnimation() {
    return this.textureAnimation;
  }
  
  public TextureCosmeticData(@NonNull String thumbnail, @NonNull String thumbnailAnimation, @NonNull String texture, String textureAnimation) {
    super(thumbnail, thumbnailAnimation);
    if (thumbnail == null)
      throw new NullPointerException("thumbnail is marked non-null but is null"); 
    if (thumbnailAnimation == null)
      throw new NullPointerException("thumbnailAnimation is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    this.texture = texture;
    this.textureAnimation = textureAnimation;
  }
  
  public boolean hasTextureAnimation() {
    return (this.textureAnimation != null && !this.textureAnimation.isEmpty());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\cosmetic\data\impl\TextureCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
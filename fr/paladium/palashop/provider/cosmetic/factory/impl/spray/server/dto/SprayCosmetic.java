package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.Cosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import lombok.NonNull;

public class SprayCosmetic extends Cosmetic<SprayCosmetic.SprayCosmeticProperties> {
  private final SprayCosmeticData data;
  
  public String toString() {
    return "SprayCosmetic(data=" + getData() + ")";
  }
  
  public SprayCosmeticData getData() {
    return this.data;
  }
  
  public SprayCosmetic(@NonNull String id, @NonNull SprayCosmeticProperties properties, @NonNull SprayCosmeticData data) {
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
    return (CosmeticFactory)SprayCosmeticFactory.getInstance();
  }
  
  public static class SprayCosmeticData {
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
  
  public static class SprayCosmeticProperties extends CosmeticProperties {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\server\dto\SprayCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
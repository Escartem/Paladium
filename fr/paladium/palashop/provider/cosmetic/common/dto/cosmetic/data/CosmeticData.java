package fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data;

public class CosmeticData {
  private String thumbnail;
  
  private String thumbnailAnimation;
  
  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }
  
  public void setThumbnailAnimation(String thumbnailAnimation) {
    this.thumbnailAnimation = thumbnailAnimation;
  }
  
  public String toString() {
    return "CosmeticData(thumbnail=" + getThumbnail() + ", thumbnailAnimation=" + getThumbnailAnimation() + ")";
  }
  
  public CosmeticData(String thumbnail, String thumbnailAnimation) {
    this.thumbnail = thumbnail;
    this.thumbnailAnimation = thumbnailAnimation;
  }
  
  public String getThumbnail() {
    return this.thumbnail;
  }
  
  public String getThumbnailAnimation() {
    return this.thumbnailAnimation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\cosmetic\data\CosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
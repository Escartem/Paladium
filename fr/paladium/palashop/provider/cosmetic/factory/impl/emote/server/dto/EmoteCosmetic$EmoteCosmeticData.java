package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.part.GeckoCosmeticData;
import lombok.NonNull;

public class EmoteCosmeticData {
  private String animation;
  
  private GeckoCosmeticData effect;
  
  public void setAnimation(String animation) {
    this.animation = animation;
  }
  
  public void setEffect(GeckoCosmeticData effect) {
    this.effect = effect;
  }
  
  public String toString() {
    return "EmoteCosmetic.EmoteCosmeticData(animation=" + getAnimation() + ", effect=" + getEffect() + ")";
  }
  
  public String getAnimation() {
    return this.animation;
  }
  
  public GeckoCosmeticData getEffect() {
    return this.effect;
  }
  
  public EmoteCosmeticData(@NonNull String animation, @NonNull GeckoCosmeticData effect) {
    if (animation == null)
      throw new NullPointerException("animation is marked non-null but is null"); 
    if (effect == null)
      throw new NullPointerException("effect is marked non-null but is null"); 
    this.animation = animation;
    this.effect = effect;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\server\dto\EmoteCosmetic$EmoteCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
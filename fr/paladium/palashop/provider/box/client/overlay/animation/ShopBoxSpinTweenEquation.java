package fr.paladium.palashop.provider.box.client.overlay.animation;

import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;

public final class ShopBoxSpinTweenEquation extends TweenEquation {
  public float compute(float t) {
    if (t < 0.2F) {
      float f = t / 0.2F;
      return f * f * 0.5F;
    } 
    float normalizedT = (t - 0.2F) / 0.8F;
    float easeOut = 1.0F - (float)Math.pow((1.0F - normalizedT), 4.0D);
    return 0.5F + easeOut * 0.5F;
  }
  
  public String toString() {
    return "Box.SPIN";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\overlay\animation\ShopBoxSpinTweenEquation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
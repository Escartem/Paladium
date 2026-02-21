package fr.paladium.palamod.modules.luckyblock.gui;

import fr.paladium.lib.aurelienribon.tweenengine.equations.Back;

final class null extends Back {
  public final float compute(float t) {
    float s = 0.5F;
    if ((t *= 2.0F) < 1.0F)
      return 0.5F * t * t * (((s *= 1.525F) + 1.0F) * t - s); 
    return 0.5F * ((t -= 2.0F) * t * (((s *= 1.525F) + 1.0F) * t + s) + 2.0F);
  }
  
  public String toString() {
    return "Back.Custom.INOUT";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\UILuckyBlock$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
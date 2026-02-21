package fr.paladium.palamod.modules.luckyblock.gui.animations;

import fr.paladium.zephyrui.lib.animation.tweenengine.TweenAccessor;

public class TexturedIconAccessor implements TweenAccessor<TexturedIcon> {
  public static final int POS_X = 1;
  
  public static final int POS_Y = 2;
  
  public int getValues(TexturedIcon target, int tweenType, float[] returnValues) {
    switch (tweenType) {
      case 1:
        returnValues[0] = target.getPosX();
        break;
      case 2:
        returnValues[0] = target.getPosY();
        break;
    } 
    return 1;
  }
  
  public void setValues(TexturedIcon target, int tweenType, float[] newValues) {
    switch (tweenType) {
      case 1:
        target.setPosX((int)newValues[0]);
        break;
      case 2:
        target.setPosY((int)newValues[0]);
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\animations\TexturedIconAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
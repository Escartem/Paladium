package fr.paladium.palajobs.client.ui.lvltokens.accessors;

import fr.paladium.lib.aurelienribon.tweenengine.TweenAccessor;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardHiddenReward;

public class HiddenRewardAccessor implements TweenAccessor<ButtonJobRewardHiddenReward> {
  public static final int ANIMATION_VALUE = 0;
  
  public static final int POS_X = 1;
  
  public static final int POS_Y = 2;
  
  public static final int SCALE = 3;
  
  public int getValues(ButtonJobRewardHiddenReward target, int tweenType, float[] returnValues) {
    switch (tweenType) {
      case 0:
        returnValues[0] = target.getAnimationValue();
        break;
      case 1:
        returnValues[0] = (float)target.getX();
        break;
      case 2:
        returnValues[0] = (float)target.getY();
        break;
      case 3:
        returnValues[0] = target.getScale();
        break;
    } 
    return 1;
  }
  
  public void setValues(ButtonJobRewardHiddenReward target, int tweenType, float[] newValues) {
    switch (tweenType) {
      case 0:
        target.setAnimationValue(newValues[0]);
        break;
      case 1:
        target.setX(newValues[0]);
        break;
      case 2:
        target.setY(newValues[0]);
        break;
      case 3:
        target.setScale(newValues[0]);
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\accessors\HiddenRewardAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
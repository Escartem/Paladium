package fr.paladium.palamod.modules.factions.utils;

import fr.paladium.factions.core.utils.ControlResult;
import java.util.function.DoubleConsumer;

final class null extends ControlResult<Double> {
  public void onCallback() {
    if (!hasSucceeded()) {
      result.accept(1.0D);
      return;
    } 
    double tmpMultiplier = ((Double)getResult()).doubleValue();
    if (tmpMultiplier < 1.0D)
      tmpMultiplier = 1.0D; 
    result.accept(tmpMultiplier);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\faction\\utils\JobXpUtils$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
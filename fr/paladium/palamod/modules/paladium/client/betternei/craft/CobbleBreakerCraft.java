package fr.paladium.palamod.modules.paladium.client.betternei.craft;

import fr.paladium.betternei.api.craft.Craft;

public class CobbleBreakerCraft extends Craft {
  public void setPercentage(float percentage) {
    this.percentage = percentage;
  }
  
  private float percentage = 0.0F;
  
  public float getPercentage() {
    return this.percentage;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\craft\CobbleBreakerCraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
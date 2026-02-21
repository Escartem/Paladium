package fr.paladium.palamod.modules.paladium.client.render;

import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

class AncientHammerAnimationData {
  private final LindwormModel<LindwormAnimatable> hammerModel;
  
  private final LindwormModel<LindwormAnimatable> playerModel;
  
  public AncientHammerAnimationData(LindwormModel<LindwormAnimatable> hammerModel, LindwormModel<LindwormAnimatable> playerModel) {
    this.hammerModel = hammerModel;
    this.playerModel = playerModel;
  }
  
  public LindwormModel<LindwormAnimatable> getHammerModel() {
    return this.hammerModel;
  }
  
  public LindwormModel<LindwormAnimatable> getPlayerModel() {
    return this.playerModel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\EntityAncientHammerEffectRenderer$AncientHammerAnimationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.pillage.common;

import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;

public interface BaseEffect {
  String getName();
  
  String getDescription();
  
  default TypeEffects getTypeEffect() {
    return TypeEffects.UNASSIGNED;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\BaseEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
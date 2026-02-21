package fr.paladium.palamod.modules.alchimiste.common.init;

import fr.paladium.palamod.modules.alchimiste.common.potions.PotionLevitation;

public class PotionMod {
  public static void register() {
    PotionLevitation.loadEffects();
    PotionLevitation.register();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\PotionMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
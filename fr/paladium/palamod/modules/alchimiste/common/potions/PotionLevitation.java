package fr.paladium.palamod.modules.alchimiste.common.potions;

import java.awt.Color;
import net.minecraft.potion.Potion;

public class PotionLevitation extends Potion {
  public static PotionLevitation potionLevitation;
  
  public static int levitationId;
  
  protected PotionLevitation(int id, boolean isBadEffectOrNot, int color, String name) {
    super(id, isBadEffectOrNot, color);
    func_76390_b("potion." + name);
  }
  
  public PotionLevitation setIconIndex(int x, int y) {
    super.func_76399_b(x, y);
    return this;
  }
  
  public static void loadEffects() {
    potionLevitation = (new PotionLevitation(levitationId, false, Color.red.getRGB(), "levitation")).setIconIndex(4, 2);
  }
  
  public static void register() {
    field_76425_a[potionLevitation.func_76396_c()] = potionLevitation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\potions\PotionLevitation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
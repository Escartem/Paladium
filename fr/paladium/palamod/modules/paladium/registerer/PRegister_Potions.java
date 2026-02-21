package fr.paladium.palamod.modules.paladium.registerer;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palamod.modules.paladium.common.potion.PotionFeatherFalling;
import fr.paladium.palamod.modules.paladium.common.potion.PotionFireImbue;
import fr.paladium.palamod.modules.paladium.common.potion.PotionFrost;
import fr.paladium.palamod.modules.paladium.common.potion.PotionGravity;
import fr.paladium.palamod.modules.paladium.common.potion.PotionPoisonImbue;
import fr.paladium.palamod.modules.paladium.common.potion.PotionSwitchArrow;
import fr.paladium.palamod.modules.paladium.common.potion.PotionWitherImbue;

public class PRegister_Potions {
  public static PotionWitherImbue potionWither;
  
  public static PotionFireImbue potionFire;
  
  public static PotionPoisonImbue potionPoison;
  
  public static PotionGravity potionGravity;
  
  public static PotionFrost potionFrost;
  
  public static PotionSwitchArrow potionSwitchArrow;
  
  public static PotionFeatherFalling potionFeatherFalling;
  
  public static void init() {
    potionWither = (PotionWitherImbue)(new PotionWitherImbue(32, false, 0)).func_76399_b(0, 0).func_76390_b("potion.imbuewither");
    potionFire = (PotionFireImbue)(new PotionFireImbue(33, false, 0)).func_76399_b(0, 0).func_76390_b("potion.imbuefire");
    potionPoison = (PotionPoisonImbue)(new PotionPoisonImbue(34, false, 0)).func_76399_b(0, 0).func_76390_b("potion.imbuepoison");
    potionGravity = (PotionGravity)(new PotionGravity(35, false, 0)).func_76399_b(0, 0).func_76390_b("potion.gravity");
    potionFrost = (PotionFrost)(new PotionFrost(36, false, 0)).func_76399_b(0, 0).func_76390_b("potion.frost");
    RegistryUtils.potion(new APotion[] { (APotion)(potionSwitchArrow = new PotionSwitchArrow()) });
    RegistryUtils.potion(new APotion[] { (APotion)(potionFeatherFalling = new PotionFeatherFalling()) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Potions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
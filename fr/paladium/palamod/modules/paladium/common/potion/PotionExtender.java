package fr.paladium.palamod.modules.paladium.common.potion;

import fr.paladium.palamod.Constants;
import java.lang.reflect.Field;
import net.minecraft.potion.Potion;

public class PotionExtender {
  public static void preInit() {
    extendPotionArray();
  }
  
  private static void extendPotionArray() {
    Potion[] potionTypes = null;
    for (Field f : Potion.class.getDeclaredFields()) {
      f.setAccessible(true);
      try {
        if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
          Field modfield = Field.class.getDeclaredField("modifiers");
          modfield.setAccessible(true);
          modfield.setInt(f, f.getModifiers() & 0xFFFFFFEF);
          potionTypes = (Potion[])f.get((Object)null);
          Potion[] newPotionTypes = new Potion[256];
          System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
          f.set((Object)null, newPotionTypes);
        } 
      } catch (Exception e) {
        Constants.logger.error("Severe error, please report this to the mod author : ", e);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\potion\PotionExtender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
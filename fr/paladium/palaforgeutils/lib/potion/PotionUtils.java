package fr.paladium.palaforgeutils.lib.potion;

import fr.paladium.palaforgeutils.lib.potion.potion.CustomPotion;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class PotionUtils {
  private static Map<Integer, APotion> potions = new HashMap<>();
  
  static {
    extendDefaultPotionArray();
  }
  
  public static void registerPotionEffect(APotion potion) {
    int id = getAvailableId();
    if (id == -1) {
      extendPotionArray();
      registerPotionEffect(potion);
      return;
    } 
    potion.setPotionId(id);
    potions.put(Integer.valueOf(id), potion);
    Potion.field_76425_a[id] = (Potion)new CustomPotion(id, potion);
    for (Field f : PotionHelper.class.getDeclaredFields()) {
      try {
        if (f.getName().equals("field_77925_n")) {
          f.setAccessible(true);
          Field field = Field.class.getDeclaredField("modifiers");
          field.setAccessible(true);
          field.setInt(f, f.getModifiers() & 0xFFFFFFEF);
          HashMap<Integer, Integer> map = (HashMap)f.get((Object)null);
          map.put(Integer.valueOf(id), Integer.valueOf(potion.getColor().getRGB()));
          f.set((Object)null, map);
          break;
        } 
      } catch (Exception exception) {}
    } 
  }
  
  public static APotion getPotionById(int id) {
    return potions.get(Integer.valueOf(id));
  }
  
  public static int getAvailableId() {
    for (int i = 100; i < Potion.field_76425_a.length; i++) {
      if (Potion.field_76425_a[i] == null)
        return i; 
    } 
    return -1;
  }
  
  private static void extendPotionArray() {
    for (Field f : Potion.class.getDeclaredFields()) {
      try {
        if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
          f.setAccessible(true);
          Field field = Field.class.getDeclaredField("modifiers");
          field.setAccessible(true);
          field.setInt(f, f.getModifiers() & 0xFFFFFFEF);
          Potion[] potionTypes = (Potion[])f.get((Object)null);
          Potion[] newPotionTypes = new Potion[potionTypes.length + 1];
          System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
          f.set((Object)null, newPotionTypes);
          break;
        } 
      } catch (Exception exception) {}
    } 
  }
  
  private static void extendDefaultPotionArray() {
    for (Field f : Potion.class.getDeclaredFields()) {
      try {
        if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
          f.setAccessible(true);
          Field field = Field.class.getDeclaredField("modifiers");
          field.setAccessible(true);
          field.setInt(f, f.getModifiers() & 0xFFFFFFEF);
          Potion[] potionTypes = (Potion[])f.get((Object)null);
          Potion[] newPotionTypes = new Potion[101];
          System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
          f.set((Object)null, newPotionTypes);
          break;
        } 
      } catch (Exception exception) {}
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\PotionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
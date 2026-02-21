package fr.paladium.palaforgeutils.lib.extended;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;

public class ExtendedUtils {
  private static Map<String, ExtendedData> extended = new HashMap<>();
  
  public static void registerExtended(Class<? extends Entity> entity, Class<? extends ExtendedEntityProperties> extended, String propName, ExtendedProperty... properties) {
    ExtendedData data = new ExtendedData(propName, entity, properties, extended);
    ExtendedUtils.extended.put(propName, data);
  }
  
  public static Map<String, ExtendedData> getExtended() {
    return extended;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\extended\ExtendedUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
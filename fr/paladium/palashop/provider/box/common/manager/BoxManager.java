package fr.paladium.palashop.provider.box.common.manager;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.item.Item;

public class BoxManager {
  private static final Map<String, BoxData> BOXES = new HashMap<>();
  
  public static void register(@NonNull BoxData box) {
    if (box == null)
      throw new NullPointerException("box is marked non-null but is null"); 
    BOXES.put(box.getId(), box.build());
    if (box.getItem() != null)
      RegistryUtils.item(new Item[] { box.getItem() }); 
  }
  
  @NonNull
  public static Optional<BoxData> getBox(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return Optional.ofNullable(BOXES.get(id));
  }
  
  @NonNull
  public static Map<String, BoxData> getBoxes() {
    return BOXES;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\manager\BoxManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
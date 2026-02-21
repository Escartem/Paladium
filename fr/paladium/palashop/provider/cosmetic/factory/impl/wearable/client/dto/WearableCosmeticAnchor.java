package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto;

import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public enum WearableCosmeticAnchor {
  NONE,
  HEAD(0.0F, 1.0F, 0.0F),
  BODY,
  LEFT_ARM,
  RIGHT_ARM,
  LEFT_LEG(0.0F, -3.0F, 3.9F),
  RIGHT_LEG(0.0F, -3.0F, 3.9F);
  
  WearableCosmeticAnchor(float sneakTranslationX, float sneakTranslationY, float sneakTranslationZ) {
    this.sneakTranslationX = sneakTranslationX;
    this.sneakTranslationY = sneakTranslationY;
    this.sneakTranslationZ = sneakTranslationZ;
  }
  
  private float sneakTranslationX;
  
  private float sneakTranslationY;
  
  private float sneakTranslationZ;
  
  private static final Map<String, WearableCosmeticAnchor> valuesByName;
  
  private static final Map<WearableCosmetic.WearableCosmeticType, WearableCosmeticAnchor> valuesByType;
  
  public float getSneakTranslationX() {
    return this.sneakTranslationX;
  }
  
  public float getSneakTranslationY() {
    return this.sneakTranslationY;
  }
  
  public float getSneakTranslationZ() {
    return this.sneakTranslationZ;
  }
  
  public final ModelRenderer getModel(@NonNull ModelBiped model) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    switch (this) {
      case HEAD:
        return model.field_78116_c;
      case BODY:
        return model.field_78115_e;
      case LEFT_ARM:
        return model.field_78113_g;
      case RIGHT_ARM:
        return model.field_78112_f;
      case LEFT_LEG:
        return model.field_78124_i;
      case RIGHT_LEG:
        return model.field_78123_h;
    } 
    return null;
  }
  
  static {
    valuesByName = new HashMap<>();
    valuesByType = new EnumMap<>(WearableCosmetic.WearableCosmeticType.class);
    for (WearableCosmeticAnchor anchor : values())
      valuesByName.put(anchor.name(), anchor); 
    valuesByType.put(WearableCosmetic.WearableCosmeticType.HEAD, HEAD);
    valuesByType.put(WearableCosmetic.WearableCosmeticType.BODY, BODY);
    valuesByType.put(WearableCosmetic.WearableCosmeticType.SUIT, NONE);
  }
  
  public static final WearableCosmeticAnchor from(@NonNull WearableCosmetic.WearableCosmeticType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return valuesByType.getOrDefault(type, NONE);
  }
  
  public static final WearableCosmeticAnchor from(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return valuesByName.getOrDefault(name.toLowerCase(), NONE);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\dto\WearableCosmeticAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palarpg.module.equipment.client.constant;

import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.EnumMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RPGItemRarityConstant {
  private static final Map<RPGItemRarity, String> NAME_MAP = new EnumMap<>(RPGItemRarity.class);
  
  private static final Map<RPGItemRarity, Color> COLOR_MAP = new EnumMap<>(RPGItemRarity.class);
  
  private static final Map<RPGItemRarity, Resource> RESOURCE_ICON_MAP = new EnumMap<>(RPGItemRarity.class);
  
  private static final Map<RPGItemRarity, Resource> RESOURCE_TAG_MAP = new EnumMap<>(RPGItemRarity.class);
  
  private static final Map<RPGItemRarity, Resource> RESOURCE_TOOLTIP_TAG_MAP = new EnumMap<>(RPGItemRarity.class);
  
  static {
    NAME_MAP.put(RPGItemRarity.UNKNOWN, "Inconnu");
    NAME_MAP.put(RPGItemRarity.COMMON, "Commun");
    NAME_MAP.put(RPGItemRarity.RARE, "Rare");
    NAME_MAP.put(RPGItemRarity.EPIC, "Épique");
    NAME_MAP.put(RPGItemRarity.LEGENDARY, "Légendaire");
    COLOR_MAP.put(RPGItemRarity.UNKNOWN, new Color(114, 114, 114));
    COLOR_MAP.put(RPGItemRarity.COMMON, new Color(1, 232, 89));
    COLOR_MAP.put(RPGItemRarity.RARE, new Color(1, 142, 230));
    COLOR_MAP.put(RPGItemRarity.EPIC, new Color(147, 2, 231));
    COLOR_MAP.put(RPGItemRarity.LEGENDARY, new Color(227, 0, 57));
    RESOURCE_ICON_MAP.put(RPGItemRarity.UNKNOWN, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/icon/unknown.png")).linear());
    RESOURCE_ICON_MAP.put(RPGItemRarity.COMMON, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/icon/common.png")).linear());
    RESOURCE_ICON_MAP.put(RPGItemRarity.RARE, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/icon/rare.png")).linear());
    RESOURCE_ICON_MAP.put(RPGItemRarity.EPIC, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/icon/epic.png")).linear());
    RESOURCE_ICON_MAP.put(RPGItemRarity.LEGENDARY, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/icon/legendary.png")).linear());
    RESOURCE_TAG_MAP.put(RPGItemRarity.UNKNOWN, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tag/unknown.png")).nearest());
    RESOURCE_TAG_MAP.put(RPGItemRarity.COMMON, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tag/common.png")).nearest());
    RESOURCE_TAG_MAP.put(RPGItemRarity.RARE, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tag/rare.png")).nearest());
    RESOURCE_TAG_MAP.put(RPGItemRarity.EPIC, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tag/epic.png")).nearest());
    RESOURCE_TAG_MAP.put(RPGItemRarity.LEGENDARY, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tag/legendary.png")).nearest());
    RESOURCE_TOOLTIP_TAG_MAP.put(RPGItemRarity.UNKNOWN, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tooltiptag/unknown.png")).nearest());
    RESOURCE_TOOLTIP_TAG_MAP.put(RPGItemRarity.COMMON, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tooltiptag/common.png")).nearest());
    RESOURCE_TOOLTIP_TAG_MAP.put(RPGItemRarity.RARE, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tooltiptag/rare.png")).nearest());
    RESOURCE_TOOLTIP_TAG_MAP.put(RPGItemRarity.EPIC, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tooltiptag/epic.png")).nearest());
    RESOURCE_TOOLTIP_TAG_MAP.put(RPGItemRarity.LEGENDARY, Resource.of(new ResourceLocation("palarpg", "textures/item/rarity/tooltiptag/legendary.png")).nearest());
  }
  
  public static String getName(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (stack.func_77973_b() == null)
      return getName(RPGItemRarity.UNKNOWN); 
    return getName(stack.func_77973_b());
  }
  
  public static String getName(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (item instanceof IRPGItem)
      return getName((IRPGItem)item); 
    return getName(RPGItemRarity.UNKNOWN);
  }
  
  public static String getName(@NonNull IRPGItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    return getName(item.getRPGRarity());
  }
  
  public static String getName(@NonNull RPGItemRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return NAME_MAP.get(rarity);
  }
  
  public static Color getColor(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (stack.func_77973_b() == null)
      return getColor(RPGItemRarity.UNKNOWN); 
    return getColor(stack.func_77973_b());
  }
  
  public static Color getColor(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (item instanceof IRPGItem)
      return getColor((IRPGItem)item); 
    return getColor(RPGItemRarity.UNKNOWN);
  }
  
  public static Color getColor(@NonNull IRPGItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    return getColor(item.getRPGRarity());
  }
  
  public static Color getColor(@NonNull RPGItemRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return COLOR_MAP.get(rarity);
  }
  
  public static Resource getIcon(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (stack.func_77973_b() == null)
      return getIcon(RPGItemRarity.UNKNOWN); 
    return getIcon(stack.func_77973_b());
  }
  
  public static Resource getIcon(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (item instanceof IRPGItem)
      return getIcon((IRPGItem)item); 
    return getIcon(RPGItemRarity.UNKNOWN);
  }
  
  public static Resource getIcon(@NonNull IRPGItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    return getIcon(item.getRPGRarity());
  }
  
  public static Resource getIcon(@NonNull RPGItemRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return RESOURCE_ICON_MAP.get(rarity);
  }
  
  public static Resource getTag(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (stack.func_77973_b() == null)
      return getTag(RPGItemRarity.UNKNOWN); 
    return getTag(stack.func_77973_b());
  }
  
  public static Resource getTag(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (item instanceof IRPGItem)
      return getTag((IRPGItem)item); 
    return getTag(RPGItemRarity.UNKNOWN);
  }
  
  public static Resource getTag(@NonNull IRPGItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    return getTag(item.getRPGRarity());
  }
  
  public static Resource getTag(@NonNull RPGItemRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return RESOURCE_TAG_MAP.get(rarity);
  }
  
  public static Resource getTooltipTag(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (stack.func_77973_b() == null)
      return getTooltipTag(RPGItemRarity.UNKNOWN); 
    return getTooltipTag(stack.func_77973_b());
  }
  
  public static Resource getTooltipTag(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (item instanceof IRPGItem)
      return getTooltipTag((IRPGItem)item); 
    return getTooltipTag(RPGItemRarity.UNKNOWN);
  }
  
  public static Resource getTooltipTag(@NonNull IRPGItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    return getTooltipTag(item.getRPGRarity());
  }
  
  public static Resource getTooltipTag(@NonNull RPGItemRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return RESOURCE_TOOLTIP_TAG_MAP.get(rarity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\constant\RPGItemRarityConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palarpg.module.stat.client.constant;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.EnumMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class RPGStatConstant {
  private static final Map<EnumStats, Color> COLOR_MAP = new EnumMap<>(EnumStats.class);
  
  private static final Map<EnumStats, Resource> RESOURCE_ICON_MAP = new EnumMap<>(EnumStats.class);
  
  private static final Map<EnumStats, Resource> GRAYSCALE_RESOURCE_ICON_MAP = new EnumMap<>(EnumStats.class);
  
  static {
    COLOR_MAP.put(EnumStats.DAMAGE, Color.decode("#FF8800"));
    COLOR_MAP.put(EnumStats.FRIGORIA_DAMAGE, Color.decode("#008232"));
    COLOR_MAP.put(EnumStats.TALIKUS_DAMAGE, Color.decode("#BB37F9"));
    COLOR_MAP.put(EnumStats.NIMBRIA_DAMAGE, Color.decode("#E8B923"));
    COLOR_MAP.put(EnumStats.VITALYS_DAMAGE, Color.decode("#E13429"));
    COLOR_MAP.put(EnumStats.MANELIOS_DAMAGE, Color.decode("#9EE1FF"));
    COLOR_MAP.put(EnumStats.RESISTANCE, Color.decode("#CACCD2"));
    COLOR_MAP.put(EnumStats.FRIGORIA_RESISTANCE, Color.decode("#008232"));
    COLOR_MAP.put(EnumStats.TALIKUS_RESISTANCE, Color.decode("#BB37F9"));
    COLOR_MAP.put(EnumStats.NIMBRIA_RESISTANCE, Color.decode("#E8B923"));
    COLOR_MAP.put(EnumStats.VITALYS_RESISTANCE, Color.decode("#E13429"));
    COLOR_MAP.put(EnumStats.MANELIOS_RESISTANCE, Color.decode("#9EE1FF"));
    COLOR_MAP.put(EnumStats.MAX_HEALTH, Color.decode("#FF3C3C"));
    COLOR_MAP.put(EnumStats.HEALTH_REGENERATION, Color.decode("#FFD23C"));
    COLOR_MAP.put(EnumStats.HEALTH_REGENERATION_SPEED, Color.decode("#FFD23C"));
    COLOR_MAP.put(EnumStats.BONUS_LOOT, Color.decode("#FFEC1A"));
    COLOR_MAP.put(EnumStats.BONUS_LOOT_ANCIENT, Color.decode("#FFEC1A"));
    COLOR_MAP.put(EnumStats.SPEED, Color.decode("#86E9FF"));
    COLOR_MAP.put(EnumStats.DODGE, Color.decode("#86E9FF"));
    COLOR_MAP.put(EnumStats.CRITICAL_CHANCE, Color.decode("#00E357"));
    COLOR_MAP.put(EnumStats.CRITICAL_DAMAGE, Color.decode("#FF4C00"));
    RESOURCE_ICON_MAP.put(EnumStats.DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/damage.png")));
    RESOURCE_ICON_MAP.put(EnumStats.FRIGORIA_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/frigoria_damage.png")));
    RESOURCE_ICON_MAP.put(EnumStats.TALIKUS_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/talikus_damage.png")));
    RESOURCE_ICON_MAP.put(EnumStats.NIMBRIA_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/nimbria_damage.png")));
    RESOURCE_ICON_MAP.put(EnumStats.VITALYS_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/vitalys_damage.png")));
    RESOURCE_ICON_MAP.put(EnumStats.MANELIOS_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/manelios_damage.png")));
    RESOURCE_ICON_MAP.put(EnumStats.RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/resistance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.FRIGORIA_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/frigoria_resistance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.TALIKUS_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/talikus_resistance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.NIMBRIA_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/nimbria_resistance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.VITALYS_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/vitalys_resistance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.MANELIOS_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/manelios_resistance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.MAX_HEALTH, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/max_health.png")));
    RESOURCE_ICON_MAP.put(EnumStats.HEALTH_REGENERATION, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/health_regeneration.png")));
    RESOURCE_ICON_MAP.put(EnumStats.HEALTH_REGENERATION_SPEED, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/health_regeneration_speed.png")));
    RESOURCE_ICON_MAP.put(EnumStats.BONUS_LOOT, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/bonus_loot.png")));
    RESOURCE_ICON_MAP.put(EnumStats.BONUS_LOOT_ANCIENT, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/bonus_loot_ancient.png")));
    RESOURCE_ICON_MAP.put(EnumStats.SPEED, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/speed.png")));
    RESOURCE_ICON_MAP.put(EnumStats.DODGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/dodge.png")));
    RESOURCE_ICON_MAP.put(EnumStats.CRITICAL_CHANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/critical_chance.png")));
    RESOURCE_ICON_MAP.put(EnumStats.CRITICAL_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/critical_damage.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/damage_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.FRIGORIA_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/frigoria_damage_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.TALIKUS_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/talikus_damage_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.NIMBRIA_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/nimbria_damage_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.VITALYS_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/vitalys_damage_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.MANELIOS_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/manelios_damage_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/resistance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.FRIGORIA_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/frigoria_resistance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.TALIKUS_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/talikus_resistance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.NIMBRIA_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/nimbria_resistance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.VITALYS_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/vitalys_resistance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.MANELIOS_RESISTANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/manelios_resistance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.MAX_HEALTH, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/max_health_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.HEALTH_REGENERATION, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/health_regeneration_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.HEALTH_REGENERATION_SPEED, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/health_regeneration_speed_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.BONUS_LOOT, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/bonus_loot_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.BONUS_LOOT_ANCIENT, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/bonus_loot_ancient_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.SPEED, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/speed_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.DODGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/dodge_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.CRITICAL_CHANCE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/critical_chance_grayscale.png")));
    GRAYSCALE_RESOURCE_ICON_MAP.put(EnumStats.CRITICAL_DAMAGE, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/stats/icon/critical_damage_grayscale.png")));
  }
  
  public static Color getColor(@NonNull EnumStats stats) {
    if (stats == null)
      throw new NullPointerException("stats is marked non-null but is null"); 
    return COLOR_MAP.get(stats);
  }
  
  public static Resource getIcon(@NonNull EnumStats stats) {
    if (stats == null)
      throw new NullPointerException("stats is marked non-null but is null"); 
    return RESOURCE_ICON_MAP.get(stats);
  }
  
  public static Resource getGrayscaleIcon(@NonNull EnumStats stats) {
    if (stats == null)
      throw new NullPointerException("stats is marked non-null but is null"); 
    return GRAYSCALE_RESOURCE_ICON_MAP.get(stats);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\client\constant\RPGStatConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
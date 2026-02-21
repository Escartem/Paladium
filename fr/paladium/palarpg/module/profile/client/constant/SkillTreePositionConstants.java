package fr.paladium.palarpg.module.profile.client.constant;

import fr.paladium.palarpg.module.profile.common.skilltree.RPGSkillTreePosition;
import fr.paladium.zephyrui.lib.color.Color;
import java.util.EnumMap;
import java.util.Map;
import lombok.NonNull;

public class SkillTreePositionConstants {
  private static final Map<RPGSkillTreePosition, Color> COLOR_MAP = new EnumMap<>(RPGSkillTreePosition.class);
  
  private static final Map<RPGSkillTreePosition, Color> SHADOW_COLOR_MAP = new EnumMap<>(RPGSkillTreePosition.class);
  
  static {
    COLOR_MAP.put(RPGSkillTreePosition.UP, new Color(255, 230, 230));
    COLOR_MAP.put(RPGSkillTreePosition.RIGHT, new Color(255, 248, 221));
    COLOR_MAP.put(RPGSkillTreePosition.DOWN, new Color(227, 255, 231));
    COLOR_MAP.put(RPGSkillTreePosition.LEFT, new Color(229, 247, 255));
    SHADOW_COLOR_MAP.put(RPGSkillTreePosition.UP, new Color(163, 99, 99));
    SHADOW_COLOR_MAP.put(RPGSkillTreePosition.RIGHT, new Color(163, 161, 99));
    SHADOW_COLOR_MAP.put(RPGSkillTreePosition.DOWN, new Color(99, 163, 108));
    SHADOW_COLOR_MAP.put(RPGSkillTreePosition.LEFT, new Color(99, 138, 163));
  }
  
  @NonNull
  public static Color getFontColor(@NonNull RPGSkillTreePosition position) {
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
    return COLOR_MAP.get(position);
  }
  
  @NonNull
  public static Color getFontShadowColor(@NonNull RPGSkillTreePosition position) {
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
    return SHADOW_COLOR_MAP.get(position);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\client\constant\SkillTreePositionConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
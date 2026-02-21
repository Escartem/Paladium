package fr.paladium.palarpg.module.equipment.common.item;

import fr.paladium.palarpg.module.equipment.client.listener.RPGArmorRendererAnchor;

public enum RPGArmorType {
  HELMET(new RPGArmorRendererAnchor[] { RPGArmorRendererAnchor.HEAD }),
  CHESTPLATE(new RPGArmorRendererAnchor[] { RPGArmorRendererAnchor.BODY, RPGArmorRendererAnchor.LEFT_ARM, RPGArmorRendererAnchor.RIGHT_ARM }),
  LEGGINGS(new RPGArmorRendererAnchor[] { RPGArmorRendererAnchor.LEFT_LEG, RPGArmorRendererAnchor.RIGHT_LEG }),
  BOOTS(new RPGArmorRendererAnchor[] { RPGArmorRendererAnchor.LEFT_FOOT, RPGArmorRendererAnchor.RIGHT_FOOT });
  
  public static final RPGArmorType[] values;
  
  private final RPGArmorRendererAnchor[] anchors;
  
  static {
    values = values();
  }
  
  public RPGArmorRendererAnchor[] getAnchors() {
    return this.anchors;
  }
  
  RPGArmorType(RPGArmorRendererAnchor... anchors) {
    this.anchors = anchors;
  }
  
  public static RPGArmorType fromOrdinal(int ordinal) {
    return values[ordinal];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\RPGArmorType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
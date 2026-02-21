package fr.paladium.palarpg.module.equipment.common.item;

import fr.paladium.zephyrui.lib.color.Color;

public enum RPGItemRarity {
  UNKNOWN(Color.decode("#727272")),
  COMMON(Color.decode("#00E357")),
  RARE(Color.decode("#008CE3")),
  EPIC(Color.decode("#9000E3")),
  LEGENDARY(Color.decode("#E30039"));
  
  RPGItemRarity(Color color) {
    this.color = color;
  }
  
  private final Color color;
  
  public Color getColor() {
    return this.color;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\RPGItemRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
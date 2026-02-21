package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.lib.apollon.utils.Color;

public enum SkillColorInfo {
  PASSIVE(Color.decode("#2DC7FF"), Color.decode("#112E38")),
  ACTIVE(Color.decode("#5ED42A"), Color.decode("#212A23")),
  COOLDOWN_ACTIVE(Color.decode("#DF611A"), Color.decode("#2A221F")),
  COOLDOWN_PASSIVE(COOLDOWN_ACTIVE.borderColor, COOLDOWN_ACTIVE.backgroundColor),
  LOCKED(Color.decode("#FFFFFF"), new Color(68, 68, 68, 30)),
  NONE(Color.decode("#FFFFFF"), new Color(68, 68, 68, 30));
  
  private final Color borderColor;
  
  private final Color backgroundColor;
  
  public Color getBorderColor() {
    return this.borderColor;
  }
  
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  SkillColorInfo(Color borderColor, Color backgroundColor) {
    this.borderColor = borderColor;
    this.backgroundColor = backgroundColor;
  }
  
  public boolean isActive() {
    return (this == ACTIVE || this == COOLDOWN_ACTIVE);
  }
  
  public boolean isPassive() {
    return (this == PASSIVE || this == COOLDOWN_PASSIVE);
  }
  
  public boolean isCooldown() {
    return (this == COOLDOWN_ACTIVE || this == COOLDOWN_PASSIVE);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\SlotClientData$SkillColorInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
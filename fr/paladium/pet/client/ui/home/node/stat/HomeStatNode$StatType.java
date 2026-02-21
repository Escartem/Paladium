package fr.paladium.pet.client.ui.home.node.stat;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.pet.common.constant.PetTranslateEnum;

public enum StatType {
  HAPPINESS("Bonheur", "textures/ui/home/happiness_logo.png", 
    
    Color.decode("#FC64C9"), Color.decode("#9B3077")),
  EXPERIENCE("Expérience", "textures/ui/home/experience_logo.png", 
    
    Color.decode("#5ED42A"), Color.decode("#3B8818"));
  
  private final String name;
  
  private final String texture;
  
  private final Color progressColor;
  
  private final Color progressUnderColor;
  
  public String getTexture() {
    return this.texture;
  }
  
  public Color getProgressColor() {
    return this.progressColor;
  }
  
  public Color getProgressUnderColor() {
    return this.progressUnderColor;
  }
  
  StatType(String name, String texture, Color progressColor, Color progressUnderColor) {
    this.name = name;
    this.texture = texture;
    this.progressColor = progressColor;
    this.progressUnderColor = progressUnderColor;
  }
  
  public String getName() {
    if (equals(EXPERIENCE))
      return PetTranslateEnum.GUI_NODE_STATS_EXPERIENCE.text(); 
    return PetTranslateEnum.GUI_NODE_STATS_HAPPINESS.text();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\stat\HomeStatNode$StatType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
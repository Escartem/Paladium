package fr.paladium.palajobs.core.tokens.rewards;

public enum EnumLvlTokenRewardRarity {
  COMMON("Commun", 70, "common.png"),
  RARE("Rare", 25, "rare.png"),
  EPIC("Epic", 5, "epic.png");
  
  public String name;
  
  public int percent;
  
  public String icon;
  
  EnumLvlTokenRewardRarity(String name, int percent, String icon) {
    this.name = name;
    this.percent = percent;
    this.icon = icon;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tokens\rewards\EnumLvlTokenRewardRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
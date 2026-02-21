package fr.paladium.palajobs.core.tokens.rewards;

public enum EnumLvlTokenRewardCategory {
  MONEY("money.png"),
  XP_JOBS("jobs_xp.png"),
  XP("xp.png"),
  ITEM("item.png");
  
  public String name;
  
  public int percent;
  
  public String icon;
  
  EnumLvlTokenRewardCategory(String icon) {
    this.icon = icon;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tokens\rewards\EnumLvlTokenRewardCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui;

public enum SuperCraftingTableResources {
  PATH("palamod:textures/gui/LuckyBlock/supercraftingtable/"),
  SLOT_LONG(PATH.getTexture() + "slot_long"),
  SLOT(PATH.getTexture() + "slot"),
  SLOT_OUTPUT(PATH.getTexture() + "slot_output"),
  BACKGROUND(PATH.getTexture() + "background"),
  FOREGROUND(PATH.getTexture() + "foreground");
  
  private String texture;
  
  private boolean hasHover;
  
  SuperCraftingTableResources(String texture) {
    this.texture = texture;
  }
  
  SuperCraftingTableResources(String texture, boolean hasHover) {
    this.texture = texture;
    this.hasHover = hasHover;
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public String getTextureHover() {
    return this.texture + "_hover";
  }
  
  public void setTexture(String texture) {
    this.texture = texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtabl\\ui\SuperCraftingTableResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
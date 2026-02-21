package fr.paladium.palamod.modules.alchimiste.common.utils;

public enum EnumPortalType {
  AMETHYSTE(0),
  TITANE(1),
  PALADIUM(2),
  ENDIUM(3);
  
  int metaType;
  
  EnumPortalType(int metaType) {
    this.metaType = metaType;
  }
  
  public int getMetaType() {
    return this.metaType;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\EnumPortalType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
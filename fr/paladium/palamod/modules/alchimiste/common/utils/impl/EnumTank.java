package fr.paladium.palamod.modules.alchimiste.common.utils.impl;

public enum EnumTank {
  GOLD(35000),
  AMETHYSTE(60000),
  TITANE(95000),
  PALADIUM(135000);
  
  int maxSeve;
  
  EnumTank(int maxSeve) {
    this.maxSeve = maxSeve;
  }
  
  public int getMaxSeve() {
    return this.maxSeve;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\impl\EnumTank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
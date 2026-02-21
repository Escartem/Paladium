package fr.paladium.palamod.modules.alchimiste.common.utils.impl;

public enum EnumGlueball {
  GREEN("green"),
  BLUE("blue"),
  YELLOW("yellow"),
  RED("red"),
  ORANGE("orange"),
  GRAY("gray"),
  PURPLE("purple"),
  GREEN_FLASH("green_flash"),
  GREEN_DARK("green_dark"),
  CYAN("cyan");
  
  String name;
  
  EnumGlueball(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\impl\EnumGlueball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.back2future.lib;

import java.awt.Color;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.StatCollector;

public enum EnumColour {
  BLACK("Black", MapColor.field_151646_E),
  RED("Red", MapColor.field_151645_D),
  GREEN("Green", MapColor.field_151651_C),
  BROWN("Brown", MapColor.field_151650_B),
  BLUE("Blue", MapColor.field_151649_A),
  PURPLE("Purple", MapColor.field_151678_z),
  CYAN("Cyan", MapColor.field_151679_y),
  LIGHT_GREY("LightGray", MapColor.field_151680_x),
  GREY("Gray", MapColor.field_151670_w),
  PINK("Pink", MapColor.field_151671_v),
  LIME("Lime", MapColor.field_151672_u),
  YELLOW("Yellow", MapColor.field_151673_t),
  LIGHT_BLUE("LightBlue", MapColor.field_151674_s),
  MAGENTA("Magenta", MapColor.field_151675_r),
  ORANGE("Orange", MapColor.field_151676_q),
  WHITE("White", MapColor.field_151666_j),
  CORSAIR("Corsair", MapColor.field_151646_E),
  SEPTEMBER("September", MapColor.field_151646_E);
  
  final String dye;
  
  final String name;
  
  final MapColor mapColour;
  
  EnumColour(String name, MapColor mapColour) {
    this.dye = "dye" + name;
    this.name = name;
    this.mapColour = mapColour;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getMojangName() {
    if (this == LIGHT_GREY)
      return "silver"; 
    return this.name.substring(0, 1).toLowerCase() + this.name.substring(1);
  }
  
  public String getOreName() {
    return this.dye;
  }
  
  public MapColor getMapColour() {
    return this.mapColour;
  }
  
  public Color getColour() {
    int i = getDamage();
    if (i >= 16)
      i = 15; 
    return new Color(EntitySheep.field_70898_d[i][0], EntitySheep.field_70898_d[i][1], EntitySheep.field_70898_d[i][2]);
  }
  
  public int getDamage() {
    if (this == CORSAIR)
      return 16; 
    if (this == SEPTEMBER)
      return 17; 
    return BlockColored.func_150031_c(ordinal());
  }
  
  public int getRGB() {
    return getColour().getRGB();
  }
  
  public int getDarker() {
    return getColour().darker().getRGB();
  }
  
  public int getBrighter() {
    return getColour().brighter().getRGB();
  }
  
  public String getTranslatedName() {
    return StatCollector.func_74838_a(getUnlocalisedName());
  }
  
  public String getUnlocalisedName() {
    if (this == CORSAIR)
      return "colour.palamod.corsair"; 
    if (this == SEPTEMBER)
      return "colour.palamod.september"; 
    return "colour.palamod." + this.dye;
  }
  
  public static EnumColour fromDamage(int meta) {
    if (meta == 16)
      return CORSAIR; 
    if (meta == 17)
      return SEPTEMBER; 
    meta = BlockColored.func_150031_c(meta);
    return values()[Math.min(Math.max(0, meta), (values()).length - 1)];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\lib\EnumColour.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.paladium.common.materials;

import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class PArmorMaterial {
  public static ItemArmor.ArmorMaterial amethyst;
  
  public static ItemArmor.ArmorMaterial titane;
  
  public static ItemArmor.ArmorMaterial paladium;
  
  public static ItemArmor.ArmorMaterial tournament;
  
  public static ItemArmor.ArmorMaterial pig;
  
  public static ItemArmor.ArmorMaterial paladiumGreen;
  
  public static ItemArmor.ArmorMaterial mixedEndium;
  
  public static ItemArmor.ArmorMaterial endium;
  
  public static ItemArmor.ArmorMaterial ancient;
  
  public static ItemArmor.ArmorMaterial sonic;
  
  public PArmorMaterial() {
    amethyst = EnumHelper.addArmorMaterial("amethyst_armor", 170, new int[] { 9, 10, 9, 8 }, 44);
    titane = EnumHelper.addArmorMaterial("titane_armor", 200, new int[] { 9, 11, 10, 8 }, 35);
    paladium = EnumHelper.addArmorMaterial("paladium_armor", 260, new int[] { 10, 13, 11, 9 }, 15);
    tournament = EnumHelper.addArmorMaterial("tournament_armor", 260, new int[] { 10, 13, 11, 9 }, 15);
    pig = EnumHelper.addArmorMaterial("pig_armor", 260, new int[] { 10, 13, 11, 9 }, 15);
    paladiumGreen = EnumHelper.addArmorMaterial("paladium_green_armor", 300, new int[] { 10, 13, 11, 9 }, 15);
    mixedEndium = EnumHelper.addArmorMaterial("mixed_endium_armor", 350, new int[] { 10, 14, 11, 9 }, 15);
    endium = EnumHelper.addArmorMaterial("endium_armor", 400, new int[] { 11, 14, 11, 10 }, 15);
    ancient = EnumHelper.addArmorMaterial("ancient_armor", 400, new int[] { 11, 14, 11, 10 }, 15);
    sonic = EnumHelper.addArmorMaterial("sonic_armor", 385, new int[] { 8, 9, 8, 7 }, 15);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\materials\PArmorMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
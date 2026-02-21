package fr.paladium.palarpg.module.dungeon.common.utils;

public class RomanNumberFormatter {
  public static String format(int number) {
    switch (number) {
      case 0:
        return "0";
      case 1:
        return "I";
      case 2:
        return "II";
      case 3:
        return "III";
      case 4:
        return "IV";
      case 5:
        return "V";
      case 6:
        return "VI";
      case 7:
        return "VII";
      case 8:
        return "VIII";
      case 9:
        return "IX";
      case 10:
        return "X";
    } 
    return String.valueOf(number);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\commo\\utils\RomanNumberFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
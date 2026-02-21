package fr.paladium.palamod.modules.luckyblock.monthly.utils;

public class ChatColorUtils {
  public static String color(String input) {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (currentChar == '&' && i + 1 < input.length()) {
        char nextChar = input.charAt(i + 1);
        String colorCode = getColorCode(nextChar);
        output.append(colorCode);
        i++;
      } else {
        output.append(currentChar);
      } 
    } 
    return output.toString();
  }
  
  private static String getColorCode(char code) {
    switch (code) {
      case '0':
        return "§0";
      case '1':
        return "§1";
      case '2':
        return "§2";
      case '3':
        return "§3";
      case '4':
        return "§4";
      case '5':
        return "§5";
      case '6':
        return "§6";
      case '7':
        return "§7";
      case '8':
        return "§8";
      case '9':
        return "§9";
      case 'A':
      case 'a':
        return "§a";
      case 'B':
      case 'b':
        return "§b";
      case 'C':
      case 'c':
        return "§c";
      case 'D':
      case 'd':
        return "§d";
      case 'E':
      case 'e':
        return "§e";
      case 'F':
      case 'f':
        return "§f";
      case 'K':
      case 'k':
        return "§k";
      case 'L':
      case 'l':
        return "§l";
      case 'M':
      case 'm':
        return "§m";
      case 'N':
      case 'n':
        return "§n";
      case 'O':
      case 'o':
        return "§o";
      case 'R':
      case 'r':
        return "§r";
    } 
    return "§e";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\ChatColorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
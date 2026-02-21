package fr.paladium.palapass.common.utils;

import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.item.ItemStack;

public class ConfigItemUtils {
  private static final Pattern PATTERN = Pattern.compile("-([0-9]+)\\.json$");
  
  public static ItemStack getItemStackFromString(String str) {
    if (str != null && Base64.getDecoder() != null)
      return ItemStackSerializer.read(new String(Base64.getDecoder().decode(str))); 
    return null;
  }
  
  public static int parsePathConfigToMonth(String path) {
    int month = -1;
    Matcher matcher = PATTERN.matcher(path);
    if (matcher.find()) {
      month = Integer.parseInt(matcher.group(1));
    } else {
      month = -1;
    } 
    if (month == -1)
      throw new RuntimeException("La configuration " + path + " n'a pas un nom valide."); 
    return month;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\commo\\utils\ConfigItemUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
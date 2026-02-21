package fr.paladium.palamod.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.regex.Pattern;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

class Primitive extends JsonToNBT.Any {
  private static final Pattern DOUBLE = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");
  
  private static final Pattern FLOAT = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
  
  private static final Pattern BYTE = Pattern.compile("[-+]?[0-9]+[b|B]");
  
  private static final Pattern LONG = Pattern.compile("[-+]?[0-9]+[l|L]");
  
  private static final Pattern SHORT = Pattern.compile("[-+]?[0-9]+[s|S]");
  
  private static final Pattern INTEGER = Pattern.compile("[-+]?[0-9]+");
  
  private static final Pattern DOUBLE_UNTYPED = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
  
  private static final Splitter SPLITTER = Splitter.on(',').omitEmptyStrings();
  
  protected String jsonValue;
  
  public Primitive(String jsonIn, String valueIn) {
    this.json = jsonIn;
    this.jsonValue = valueIn;
  }
  
  public NBTBase parse() throws NBTException {
    try {
      if (DOUBLE.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagDouble(
            Double.parseDouble(this.jsonValue.substring(0, this.jsonValue.length() - 1))); 
      if (FLOAT.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagFloat(
            Float.parseFloat(this.jsonValue.substring(0, this.jsonValue.length() - 1))); 
      if (BYTE.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagByte(
            Byte.parseByte(this.jsonValue.substring(0, this.jsonValue.length() - 1))); 
      if (LONG.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagLong(
            Long.parseLong(this.jsonValue.substring(0, this.jsonValue.length() - 1))); 
      if (SHORT.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagShort(
            Short.parseShort(this.jsonValue.substring(0, this.jsonValue.length() - 1))); 
      if (INTEGER.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagInt(Integer.parseInt(this.jsonValue)); 
      if (DOUBLE_UNTYPED.matcher(this.jsonValue).matches())
        return (NBTBase)new NBTTagDouble(Double.parseDouble(this.jsonValue)); 
      if ("true".equalsIgnoreCase(this.jsonValue) || "false".equalsIgnoreCase(this.jsonValue))
        return (NBTBase)new NBTTagByte((byte)(Boolean.parseBoolean(this.jsonValue) ? 1 : 0)); 
    } catch (NumberFormatException var6) {
      this.jsonValue = this.jsonValue.replaceAll("\\\\\"", "\"");
      return (NBTBase)new NBTTagString(this.jsonValue);
    } 
    if (this.jsonValue.startsWith("[") && this.jsonValue.endsWith("]")) {
      String s = this.jsonValue.substring(1, this.jsonValue.length() - 1);
      String[] astring = (String[])Iterables.toArray(SPLITTER.split(s), String.class);
      try {
        int[] aint = new int[astring.length];
        for (int j = 0; j < astring.length; j++)
          aint[j] = Integer.parseInt(astring[j].trim()); 
        return (NBTBase)new NBTTagIntArray(aint);
      } catch (NumberFormatException var5) {
        return (NBTBase)new NBTTagString(this.jsonValue);
      } 
    } 
    if (this.jsonValue.startsWith("\"") && this.jsonValue.endsWith("\""))
      this.jsonValue = this.jsonValue.substring(1, this.jsonValue.length() - 1); 
    this.jsonValue = this.jsonValue.replaceAll("\\\\\"", "\"");
    StringBuilder stringbuilder = new StringBuilder();
    for (int i = 0; i < this.jsonValue.length(); i++) {
      if (i < this.jsonValue.length() - 1 && this.jsonValue.charAt(i) == '\\' && this.jsonValue
        .charAt(i + 1) == '\\') {
        stringbuilder.append('\\');
        i++;
      } else {
        stringbuilder.append(this.jsonValue.charAt(i));
      } 
    } 
    return (NBTBase)new NBTTagString(stringbuilder.toString());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\JsonToNBT$Primitive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
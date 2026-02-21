package fr.paladium.palamod.modules.enderchest.serial.nbt.utils;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;

public class NBTListUtil {
  public static int getInt(NBTTagList list, int index) {
    List<NBTBase> tagList = (List)ObfuscationReflectionHelper.getPrivateValue(NBTTagList.class, list, new String[] { "tagList", "field_74747_a" });
    if (index >= 0 && index < list.func_74745_c()) {
      NBTBase nbtbase = tagList.get(index);
      return (nbtbase.func_74732_a() == 3) ? ((NBTTagInt)nbtbase).func_150287_d() : 0;
    } 
    return 0;
  }
  
  public static byte getByte(NBTTagList list, int index) {
    List<NBTBase> tagList = (List)ObfuscationReflectionHelper.getPrivateValue(NBTTagList.class, list, new String[] { "tagList", "field_74747_a" });
    if (index >= 0 && index < list.func_74745_c()) {
      NBTBase nbtbase = tagList.get(index);
      return (nbtbase.func_74732_a() == 1) ? ((NBTTagByte)nbtbase).func_150290_f() : 0;
    } 
    return 0;
  }
  
  public static long getLong(NBTTagList list, int index) {
    List<NBTBase> tagList = (List)ObfuscationReflectionHelper.getPrivateValue(NBTTagList.class, list, new String[] { "tagList", "field_74747_a" });
    if (index >= 0 && index < list.func_74745_c()) {
      NBTBase nbtbase = tagList.get(index);
      return (nbtbase.func_74732_a() == 4) ? ((NBTTagLong)nbtbase).func_150291_c() : 0L;
    } 
    return 0L;
  }
  
  public static short getShort(NBTTagList list, int index) {
    List<NBTBase> tagList = (List)ObfuscationReflectionHelper.getPrivateValue(NBTTagList.class, list, new String[] { "tagList", "field_74747_a" });
    if (index >= 0 && index < list.func_74745_c()) {
      NBTBase nbtbase = tagList.get(index);
      return (nbtbase.func_74732_a() == 2) ? ((NBTTagShort)nbtbase).func_150289_e() : 0;
    } 
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nb\\utils\NBTListUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
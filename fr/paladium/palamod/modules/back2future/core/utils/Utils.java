package fr.paladium.palamod.modules.back2future.core.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {
  public static String getUnlocalisedName(String name) {
    return "palamod." + name;
  }
  
  public static String getBlockTexture(String name) {
    return "palamod:" + name;
  }
  
  public static String getItemTexture(String name) {
    return "palamod:" + name;
  }
  
  public static ResourceLocation getResource(String path) {
    return new ResourceLocation(path);
  }
  
  public static String getConainerName(String name) {
    return "container.palamod." + name;
  }
  
  public static <T> T getTileEntity(IBlockAccess world, int x, int y, int z, Class<T> cls) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!cls.isInstance(tile))
      return null; 
    return (T)tile;
  }
  
  public static List<String> getOreNames(ItemStack stack) {
    List<String> list = new ArrayList<>();
    for (int id : OreDictionary.getOreIDs(stack))
      list.add(OreDictionary.getOreName(id)); 
    return list;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
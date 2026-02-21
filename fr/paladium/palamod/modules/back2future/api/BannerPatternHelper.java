package fr.paladium.palamod.modules.back2future.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class BannerPatternHelper {
  public static void addPattern(String name, String id, ItemStack craftingItem) throws ClassNotFoundException {
    EnumHelper.addEnum(Class.forName("fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner$EnumBannerPattern"), name
        
        .toUpperCase(), new Class[] { String.class, String.class, ItemStack.class }, new Object[] { name, id, craftingItem });
  }
  
  public static void addPattern(String name, String id, String craftingTop, String craftingMid, String craftingBot) throws ClassNotFoundException {
    EnumHelper.addEnum(Class.forName("fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner$EnumBannerPattern"), name
        
        .toUpperCase(), new Class[] { String.class, String.class, String.class, String.class, String.class }, new Object[] { name, id, craftingTop, craftingMid, craftingBot });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\api\BannerPatternHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.paladium.common.materials;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class PToolMaterial {
  public static Item.ToolMaterial amethyst;
  
  public static Item.ToolMaterial titane;
  
  public static Item.ToolMaterial paladium;
  
  public static Item.ToolMaterial paladiumGreen;
  
  public static Item.ToolMaterial endium;
  
  public static Item.ToolMaterial luckyCorrupted;
  
  public PToolMaterial() {
    amethyst = EnumHelper.addToolMaterial("amethyst_tool", 3, 1999, 20.0F, 3.0F, 20);
    titane = EnumHelper.addToolMaterial("titane_tool", 3, 2999, 23.0F, 3.5F, 20);
    paladium = EnumHelper.addToolMaterial("paladium_tool", 3, 4999, 28.0F, 6.0F, 15);
    paladiumGreen = EnumHelper.addToolMaterial("paladium_green_tool", 3, 4999, 35.0F, 6.0F, 15);
    endium = EnumHelper.addToolMaterial("endium_tool", 4, 4999, 40.0F, 7.3F, 25);
    luckyCorrupted = EnumHelper.addToolMaterial("lucky_corrupted_tool", 4, 4999, 40.0F, 0.1F, 25);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\materials\PToolMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
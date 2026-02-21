package fr.paladium.palamod.modules.back2future.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public enum EnumBannerPattern {
  BASE("base", "b"),
  SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
  SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
  SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
  SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
  STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
  STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
  STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
  STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
  STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
  STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
  STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
  STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
  STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
  CROSS("cross", "cr", "# #", " # ", "# #"),
  STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
  TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
  TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
  TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "),
  TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
  DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
  DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
  DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
  DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
  CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
  RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
  HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
  HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
  HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
  HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
  BORDER("border", "bo", "###", "# #", "###"),
  CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.field_150395_bd)),
  CREEPER("creeper", "cre", new ItemStack(Items.field_151144_bL, 1, 4)),
  GRADIENT("gradient", "gra", "# #", " # ", " # "),
  GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
  BRICKS("bricks", "bri", new ItemStack(Blocks.field_150336_V)),
  SKULL("skull", "sku", new ItemStack(Items.field_151144_bL, 1, 1)),
  FLOWER("flower", "flo", new ItemStack((Block)Blocks.field_150328_O, 1, 8)),
  MOJANG("mojang", "moj", new ItemStack(Items.field_151153_ao, 1, 1)),
  FUZE("fuze", "fuz", new ItemStack(ItemsRegister.PALADIUM_CORE));
  
  private final String patternName;
  
  private final String patternID;
  
  private final String[] craftingLayers;
  
  private ItemStack patternCraftingStack;
  
  EnumBannerPattern(String name, String id) {
    this.craftingLayers = new String[3];
    this.patternName = name;
    this.patternID = id;
    this.patternCraftingStack = null;
  }
  
  EnumBannerPattern(String name, String id, ItemStack craftingItem) {
    this.patternCraftingStack = craftingItem;
  }
  
  EnumBannerPattern(String name, String id, String craftingTop, String craftingMid, String craftingBot) {
    this.craftingLayers[0] = craftingTop;
    this.craftingLayers[1] = craftingMid;
    this.craftingLayers[2] = craftingBot;
  }
  
  @SideOnly(Side.CLIENT)
  public String getPatternName() {
    return this.patternName;
  }
  
  public String getPatternID() {
    return this.patternID;
  }
  
  public String[] getCraftingLayers() {
    return this.craftingLayers;
  }
  
  public boolean hasValidCrafting() {
    return (this.patternCraftingStack != null || this.craftingLayers[0] != null);
  }
  
  public boolean hasCraftingStack() {
    return (this.patternCraftingStack != null);
  }
  
  public ItemStack getCraftingStack() {
    return this.patternCraftingStack;
  }
  
  @SideOnly(Side.CLIENT)
  public static EnumBannerPattern getPatternByID(String id) {
    for (EnumBannerPattern pattern : values()) {
      if (pattern.patternID.equals(id))
        return pattern; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\tileentities\TileEntityBanner$EnumBannerPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
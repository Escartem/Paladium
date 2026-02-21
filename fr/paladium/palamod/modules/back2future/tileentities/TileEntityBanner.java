package fr.paladium.palamod.modules.back2future.tileentities;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.items.block.ItemBanner;
import fr.paladium.palamod.modules.back2future.lib.EnumColour;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBanner extends TileEntity {
  private int baseColor;
  
  private NBTTagList patterns;
  
  private boolean field_175119_g;
  
  private List<EnumBannerPattern> patternList;
  
  private List<EnumColour> colorList;
  
  private String field_175121_j;
  
  public boolean isStanding;
  
  public void setItemValues(ItemStack stack) {
    this.patterns = null;
    if (stack.func_77942_o() && stack.func_77978_p().func_150297_b("BlockEntityTag", 10)) {
      NBTTagCompound nbttagcompound = stack.func_77978_p().func_74775_l("BlockEntityTag");
      if (nbttagcompound.func_74764_b("Patterns"))
        this.patterns = (NBTTagList)nbttagcompound.func_150295_c("Patterns", 10).func_74737_b(); 
      if (nbttagcompound.func_150297_b("Base", 99)) {
        this.baseColor = nbttagcompound.func_74762_e("Base");
      } else {
        this.baseColor = stack.func_77960_j() & 0xF;
      } 
    } else {
      this.baseColor = stack.func_77960_j() & 0xF;
    } 
    if (stack.func_77960_j() == 16) {
      this.baseColor = 16;
    } else if (stack.func_77960_j() == 17) {
      this.baseColor = 17;
    } 
    this.patternList = null;
    this.colorList = null;
    this.field_175121_j = "";
    this.field_175119_g = true;
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("Base", this.baseColor);
    nbt.func_74757_a("IsStanding", this.isStanding);
    if (this.patterns != null)
      nbt.func_74782_a("Patterns", (NBTBase)this.patterns); 
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.baseColor = nbt.func_74762_e("Base");
    this.isStanding = nbt.func_74767_n("IsStanding");
    this.patterns = nbt.func_150295_c("Patterns", 10);
    this.patternList = null;
    this.colorList = null;
    this.field_175121_j = null;
    this.field_175119_g = true;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    if (pkt.func_148853_f() == 0)
      func_145839_a(pkt.func_148857_g()); 
  }
  
  public int getBaseColor() {
    return this.baseColor;
  }
  
  public static int getBaseColor(ItemStack stack) {
    NBTTagCompound nbttagcompound = ItemBanner.getSubTag(stack, "BlockEntityTag", false);
    return (nbttagcompound != null && nbttagcompound.func_74764_b("Base")) ? nbttagcompound
      .func_74762_e("Base") : stack
      .func_77960_j();
  }
  
  public static int getPatterns(ItemStack stack) {
    NBTTagCompound nbttagcompound = ItemBanner.getSubTag(stack, "BlockEntityTag", false);
    return (nbttagcompound != null && nbttagcompound.func_74764_b("Patterns")) ? nbttagcompound
      .func_150295_c("Patterns", 10).func_74745_c() : 0;
  }
  
  @SideOnly(Side.CLIENT)
  public List<EnumBannerPattern> getPatternList() {
    initializeBannerData();
    return this.patternList;
  }
  
  @SideOnly(Side.CLIENT)
  public List<EnumColour> getColorList() {
    initializeBannerData();
    return this.colorList;
  }
  
  @SideOnly(Side.CLIENT)
  public String func_175116_e() {
    initializeBannerData();
    return this.field_175121_j;
  }
  
  public static void removeBannerData(ItemStack stack) {
    NBTTagCompound nbttagcompound = ItemBanner.getSubTag(stack, "BlockEntityTag", false);
    if (nbttagcompound != null && nbttagcompound.func_150297_b("Patterns", 9)) {
      NBTTagList nbttaglist = nbttagcompound.func_150295_c("Patterns", 10);
      if (nbttaglist.func_74745_c() > 0) {
        nbttaglist.func_74744_a(nbttaglist.func_74745_c() - 1);
        if (nbttaglist.func_74745_c() == 0) {
          stack.func_77978_p().func_82580_o("BlockEntityTag");
          if (stack.func_77978_p().func_82582_d())
            stack.func_77982_d((NBTTagCompound)null); 
        } 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  private void initializeBannerData() {
    if (this.patternList == null || this.colorList == null || this.field_175121_j == null)
      if (!this.field_175119_g) {
        this.field_175121_j = "";
      } else {
        this.patternList = Lists.newArrayList();
        this.colorList = Lists.newArrayList();
        this.patternList.add(EnumBannerPattern.BASE);
        this.colorList.add(EnumColour.fromDamage(this.baseColor));
        this.field_175121_j = "b" + this.baseColor;
        if (this.patterns != null)
          for (int i = 0; i < this.patterns.func_74745_c(); i++) {
            NBTTagCompound nbttagcompound = this.patterns.func_150305_b(i);
            EnumBannerPattern pattern = EnumBannerPattern.getPatternByID(nbttagcompound.func_74779_i("Pattern"));
            if (pattern != null) {
              this.patternList.add(pattern);
              int j = nbttagcompound.func_74762_e("Color");
              this.colorList.add(EnumColour.fromDamage(j));
              this.field_175121_j += pattern.getPatternID() + j;
            } 
          }  
      }  
  }
  
  public ItemStack createStack() {
    ItemStack stack = new ItemStack(ModBlocks.banner, 1, getBaseColor());
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    nbt.func_82580_o("x");
    nbt.func_82580_o("y");
    nbt.func_82580_o("z");
    nbt.func_82580_o("id");
    stack.func_77983_a("BlockEntityTag", (NBTBase)nbt);
    return stack;
  }
  
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
    CURLY_BORDER("curly_border", "cbo", (String)new ItemStack(Blocks.field_150395_bd)),
    CREEPER("creeper", "cre", (String)new ItemStack(Items.field_151144_bL, 1, 4)),
    GRADIENT("gradient", "gra", "# #", " # ", " # "),
    GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
    BRICKS("bricks", "bri", (String)new ItemStack(Blocks.field_150336_V)),
    SKULL("skull", "sku", (String)new ItemStack(Items.field_151144_bL, 1, 1)),
    FLOWER("flower", "flo", (String)new ItemStack((Block)Blocks.field_150328_O, 1, 8)),
    MOJANG("mojang", "moj", (String)new ItemStack(Items.field_151153_ao, 1, 1)),
    FUZE("fuze", "fuz", (String)new ItemStack(ItemsRegister.PALADIUM_CORE));
    
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\tileentities\TileEntityBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
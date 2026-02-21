package fr.paladium.palamod.modules.back2future.items.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.lib.EnumColour;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBanner extends ItemBlock {
  public ItemBanner(Block block) {
    super(block);
    func_77656_e(0);
    func_77625_d(16);
    func_77627_a(true);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (world.func_147439_a(x, y, z) == Blocks.field_150383_bp) {
      int meta = world.func_72805_g(x, y, z);
      if (meta > 0) {
        stack.func_77982_d(null);
        world.func_72921_c(x, y, z, meta - 1, 3);
        return true;
      } 
    } 
    if (side == 0)
      return false; 
    if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a())
      return false; 
    if (side == 1)
      y++; 
    if (side == 2)
      z--; 
    if (side == 3)
      z++; 
    if (side == 4)
      x--; 
    if (side == 5)
      x++; 
    if (!player.func_82247_a(x, y, z, side, stack))
      return false; 
    if (!this.field_150939_a.func_149742_c(world, x, y, z))
      return false; 
    if (side == 1) {
      int meta = MathHelper.func_76128_c(((player.field_70177_z + 180.0F) * 16.0F / 360.0F) + 0.5D) & 0xF;
      world.func_147465_d(x, y, z, this.field_150939_a, meta, 3);
    } else {
      world.func_147465_d(x, y, z, this.field_150939_a, side, 3);
    } 
    stack.field_77994_a--;
    TileEntityBanner banner = (TileEntityBanner)world.func_147438_o(x, y, z);
    if (banner != null) {
      banner.isStanding = (side == 1);
      banner.setItemValues(stack);
    } 
    return true;
  }
  
  public String func_77653_i(ItemStack stack) {
    return 
      StatCollector.func_74838_a("item.banner." + getBaseColor(stack).getMojangName() + ".name");
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    NBTTagCompound nbttagcompound = getSubTag(stack, "BlockEntityTag", false);
    if (nbttagcompound != null && nbttagcompound.func_74764_b("Patterns")) {
      NBTTagList nbttaglist = nbttagcompound.func_150295_c("Patterns", 10);
      for (int i = 0; i < nbttaglist.func_74745_c() && i < 6; i++) {
        NBTTagCompound nbt = nbttaglist.func_150305_b(i);
        EnumColour colour = EnumColour.fromDamage(nbt.func_74762_e("Color"));
        TileEntityBanner.EnumBannerPattern pattern = TileEntityBanner.EnumBannerPattern.getPatternByID(nbt.func_74779_i("Pattern"));
        if (pattern != null)
          tooltip.add(StatCollector.func_74838_a("item.banner." + pattern
                .getPatternName() + "." + colour.getMojangName())); 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int renderPass) {
    if (stack.func_77960_j() == 16 || stack.func_77960_j() == 17)
      return EnumColour.WHITE.getBrighter(); 
    EnumColour EnumColour = getBaseColor(stack);
    return EnumColour.getRGB();
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> subItems) {
    for (EnumColour colour : EnumColour.values())
      subItems.add(new ItemStack(item, 1, colour.getDamage())); 
  }
  
  private EnumColour getBaseColor(ItemStack stack) {
    NBTTagCompound nbttagcompound = getSubTag(stack, "BlockEntityTag", false);
    EnumColour colour = null;
    if (nbttagcompound != null && nbttagcompound.func_74764_b("Base")) {
      colour = EnumColour.fromDamage(nbttagcompound.func_74762_e("Base"));
    } else {
      colour = EnumColour.fromDamage(stack.func_77960_j());
    } 
    return colour;
  }
  
  public static NBTTagCompound getSubTag(ItemStack stack, String key, boolean create) {
    if (stack.field_77990_d != null && stack.field_77990_d.func_150297_b(key, 10))
      return stack.field_77990_d.func_74775_l(key); 
    if (create) {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      stack.func_77983_a(key, (NBTBase)nbttagcompound);
      return nbttagcompound;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\block\ItemBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
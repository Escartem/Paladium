package fr.paladium.palamod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class BaseItem extends Item {
  public static IIcon itemIcon1;
  
  public BaseItem(String texture) {
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    if (func_77658_a().contains("endium"))
      func_77625_d(1); 
  }
  
  public void func_94581_a(IIconRegister p_94581_1_) {
    itemIcon1 = p_94581_1_.func_94245_a("palamod:palastrange");
    super.func_94581_a(p_94581_1_);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77650_f(ItemStack p_77650_1_) {
    NBTTagCompound nbtTag = new NBTTagCompound();
    if (p_77650_1_.func_77942_o()) {
      nbtTag = p_77650_1_.func_77978_p();
      if (nbtTag.func_74767_n("STRANGE"))
        return itemIcon1; 
    } 
    return this.field_77791_bV;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(ItemStack p_77650_1_, int renderpass) {
    NBTTagCompound nbtTag = new NBTTagCompound();
    if (p_77650_1_.func_77942_o()) {
      nbtTag = p_77650_1_.func_77978_p();
      if (nbtTag.func_74767_n("STRANGE"))
        return itemIcon1; 
    } 
    return this.field_77791_bV;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\items\BaseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
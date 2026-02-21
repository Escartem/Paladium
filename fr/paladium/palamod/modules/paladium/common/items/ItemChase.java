package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.utils.IItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemChase extends Item implements IItem {
  private String name;
  
  public ItemChase(String name) {
    this.name = name;
    func_77655_b(name);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77625_d(1);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister) {
    this.field_77791_bV = par1IconRegister.func_94245_a("palamod:" + this.name);
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Item getObject() {
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemChase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
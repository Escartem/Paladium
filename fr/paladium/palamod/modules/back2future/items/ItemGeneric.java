package fr.paladium.palamod.modules.back2future.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemGeneric extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;
  
  private final String[] types;
  
  public ItemGeneric(String... types) {
    this.types = types;
    func_77656_e(0);
    func_77627_a(true);
  }
  
  public String func_77667_c(ItemStack stack) {
    return super.func_77667_c(stack) + "_" + this.types[
        Math.max(Math.min(stack.func_77960_j(), this.types.length - 1), 0)];
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int meta) {
    return this.icons[Math.max(Math.min(meta, this.types.length - 1), 0)];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < this.types.length; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister reg) {
    this.icons = new IIcon[this.types.length];
    for (int i = 0; i < this.types.length; i++)
      this.icons[i] = reg.func_94245_a(func_111208_A() + "_" + this.types[i]); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\ItemGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
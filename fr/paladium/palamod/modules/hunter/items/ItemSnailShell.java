package fr.paladium.palamod.modules.hunter.items;

import fr.paladium.palamod.common.Registry;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemSnailShell extends Item {
  private IIcon[] icons = new IIcon[4];
  
  public ItemSnailShell() {
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
    func_77655_b("snail_shell");
    func_111206_d("palamod:snail_shell");
    func_77625_d(16);
  }
  
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < 4; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public String func_77653_i(ItemStack item) {
    String name = "";
    switch (item.func_77960_j()) {
      case 0:
        name = "Coquille d'escargot";
        break;
      case 1:
        name = "Coquille d'escargot en §7Titane";
        break;
      case 2:
        name = "Coquille d'escargot en §dAméthyste";
        break;
      case 3:
        name = "Coquille d'escargot en §cPaladium";
        break;
    } 
    return name;
  }
  
  public void func_94581_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:snail_shell");
    this.icons[1] = register.func_94245_a("palamod:snail_shell_titane");
    this.icons[2] = register.func_94245_a("palamod:snail_shell_amethyste");
    this.icons[3] = register.func_94245_a("palamod:snail_shell_paladium");
    super.func_94581_a(register);
  }
  
  public IIcon func_77617_a(int damage) {
    return this.icons[damage];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemSnailShell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
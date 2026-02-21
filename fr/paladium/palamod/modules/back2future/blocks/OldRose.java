package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.List;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class OldRose extends BlockFlower implements IConfigurable {
  public OldRose() {
    super(1);
    func_149711_c(0.0F);
    func_149672_a(field_149779_h);
    func_149663_c(Utils.getUnlocalisedName("rose"));
    func_149658_d("palamod:flower_rose");
    func_149647_a(Back2Future.enableRoses ? Back2Future.creativeTab : null);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    list.add(new ItemStack(item));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_149761_L = reg.func_94245_a(func_149641_N());
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRoses;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\OldRose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
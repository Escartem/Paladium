package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockBeetroot extends BlockCrops implements IConfigurable {
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;
  
  public BlockBeetroot() {
    func_149647_a(null);
    func_149658_d("beetroots");
    func_149663_c(Utils.getUnlocalisedName("beetroots"));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta < 7) {
      if (meta == 6)
        meta = 5; 
      return this.icons[meta >> 1];
    } 
    return this.icons[3];
  }
  
  protected Item func_149866_i() {
    return ModItems.beetroot_seeds;
  }
  
  protected Item func_149865_P() {
    return ModItems.beetroot;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.icons = new IIcon[4];
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = reg.func_94245_a(func_149641_N() + "_stage_" + i); 
  }
  
  public boolean isEnabled() {
    return Back2Future.enableBeetroot;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockBeetroot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
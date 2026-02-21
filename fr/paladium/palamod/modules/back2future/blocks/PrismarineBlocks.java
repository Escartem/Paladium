package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class PrismarineBlocks extends BlockGeneric implements IConfigurable {
  public PrismarineBlocks() {
    super(Material.field_151576_e, new String[] { "rough", "bricks", "dark" });
    func_149711_c(1.5F);
    func_149752_b(10.0F);
    func_149658_d("prismarine");
    func_149663_c(Utils.getUnlocalisedName("prismarine_block"));
    func_149647_a(Back2Future.enablePrismarine ? Back2Future.creativeTab : null);
  }
  
  @SideOnly(Side.CLIENT)
  public void setIcon(int index, IIcon icon) {
    if (this.icons == null)
      this.icons = new IIcon[this.types.length]; 
    this.icons[index] = icon;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    if (this.icons == null)
      this.icons = new IIcon[this.types.length]; 
    for (int i = 1; i < this.types.length; i++) {
      if ("".equals(this.types[i])) {
        this.icons[i] = reg.func_94245_a(func_149641_N());
      } else {
        this.icons[i] = reg.func_94245_a(func_149641_N() + "_" + this.types[i]);
      } 
    } 
  }
  
  public boolean isEnabled() {
    return Back2Future.enablePrismarine;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\PrismarineBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
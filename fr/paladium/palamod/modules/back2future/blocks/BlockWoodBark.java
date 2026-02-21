package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.items.block.ItemBlockGeneric;
import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class BlockWoodBark extends BlockWood implements IConfigurable, ModBlocks.ISubBlocksBlock {
  private IIcon[] field_150095_b;
  
  public BlockWoodBark() {
    func_149647_a(Back2Future.creativeTab);
    func_149658_d("log");
    func_149711_c(2.0F).func_149752_b(5.0F).func_149672_a(field_149766_f);
    func_149663_c(Utils.getUnlocalisedName("bark"));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    if (p_149691_2_ < 0 || p_149691_2_ >= this.field_150095_b.length)
      p_149691_2_ = 0; 
    return this.field_150095_b[p_149691_2_];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_150095_b = new IIcon[field_150096_a.length];
    for (int i = 0; i < this.field_150095_b.length; i++)
      this.field_150095_b[i] = p_149651_1_
        .func_94245_a(func_149641_N() + "_" + field_150096_a[i]); 
  }
  
  public boolean isEnabled() {
    return true;
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemBlockGeneric.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockWoodBark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
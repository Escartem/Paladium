package fr.paladium.palamod.modules.pillage.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.pillage.common.items.ItemBlockMultipleTypes;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockCompressedQuartz extends BlockBase {
  IIcon[] icons;
  
  public BlockCompressedQuartz(String unlocalizedName) {
    super(Material.field_151576_e, unlocalizedName);
    func_149711_c(1.0F);
    func_149658_d("palamod:pillage/home_finder/" + unlocalizedName);
  }
  
  public void register() {
    GameRegistry.registerBlock(this, ItemBlockMultipleTypes.class, func_149739_a());
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> subBlocks) {
    byte i;
    for (i = 0; i < 5; i = (byte)(i + 1))
      subBlocks.add(new ItemStack(item, 1, i)); 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.icons = new IIcon[5];
    for (byte i = 0; i < 5; i = (byte)(i + 1))
      this.icons[i] = iconRegister.func_94245_a(func_149641_N() + "_" + i); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta % this.icons.length];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\BlockCompressedQuartz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BaseMetaBlock extends Block {
  public String[] textures;
  
  public IIcon[] icons;
  
  public BaseMetaBlock(Material material, float hardness, String[] textures) {
    super(material);
    func_149711_c(hardness);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    this.textures = textures;
  }
  
  public int func_149692_a(int meta) {
    return meta;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.icons = new IIcon[this.textures.length];
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = iconRegister.func_94245_a("palamod:" + this.textures[i]); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (meta < this.icons.length) ? this.icons[meta] : this.icons[0];
  }
  
  @SideOnly(Side.CLIENT)
  public int getSideTextureIndex(int side) {
    if (side == 0)
      return 2; 
    if (side == 1)
      return 0; 
    return 1;
  }
  
  public void func_149666_a(Item block, CreativeTabs tab, List<ItemStack> list) {
    for (int iter = 0; iter < this.icons.length; iter++)
      list.add(new ItemStack(block, 1, iter)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\BaseMetaBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
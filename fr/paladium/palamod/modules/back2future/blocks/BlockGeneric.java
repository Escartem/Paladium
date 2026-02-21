package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.items.block.ItemBlockGeneric;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockGeneric extends Block implements ModBlocks.ISubBlocksBlock {
  @SideOnly(Side.CLIENT)
  protected IIcon[] icons;
  
  protected final String[] types;
  
  protected int startMeta = 0;
  
  public BlockGeneric(Material material, String... types) {
    super(material);
    this.types = types;
  }
  
  public String getNameFor(int meta) {
    return this.types[Math.max(Math.min(meta, this.types.length - 1), 0)];
  }
  
  public int func_149692_a(int meta) {
    return meta;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = this.startMeta; i < this.types.length; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[Math.max(Math.min(meta, this.types.length - 1), this.startMeta)];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.icons = new IIcon[this.types.length];
    for (int i = 0; i < this.types.length; i++) {
      if ("".equals(this.types[i])) {
        this.icons[i] = reg.func_94245_a(func_149641_N());
      } else {
        this.icons[i] = reg.func_94245_a(func_149641_N() + "_" + this.types[i]);
      } 
    } 
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemBlockGeneric.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
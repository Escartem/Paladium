package fr.paladium.palamod.modules.world.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class BaseBlockLogSeve extends BaseBlockLog {
  public BaseBlockLogSeve(Material material, float hardness, String texture) {
    super(material, hardness, texture);
  }
  
  public BaseBlockLogSeve(Material material, float hardness) {
    super(material, hardness);
  }
  
  public BaseBlockLogSeve(Material material) {
    super(material);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_150167_a = new net.minecraft.util.IIcon[1];
    this.field_150166_b = new net.minecraft.util.IIcon[1];
    for (int i = 0; i < this.field_150167_a.length; i++) {
      this.field_150167_a[i] = reg.func_94245_a(func_149641_N());
      this.field_150166_b[i] = reg.func_94245_a(func_149641_N() + "_top");
    } 
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return super.func_149650_a(p_149650_1_, p_149650_2_, p_149650_3_);
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\blocks\BaseBlockLogSeve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
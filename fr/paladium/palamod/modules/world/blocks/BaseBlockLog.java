package fr.paladium.palamod.modules.world.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BaseBlockLog extends BlockLog {
  public BaseBlockLog(Material material, float hardness, String texture) {
    func_149658_d("palamod:" + texture);
    func_149711_c(hardness);
    init(material);
  }
  
  public BaseBlockLog(Material material, float hardness) {
    func_149711_c(hardness);
    init(material);
  }
  
  public BaseBlockLog(Material material) {
    init(material);
  }
  
  private void init(Material material) {
    func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    setSounds(material);
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150163_b(int p_150163_1_) {
    return this.field_150167_a[0];
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150161_d(int p_150161_1_) {
    return this.field_150166_b[0];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_150167_a = new IIcon[1];
    this.field_150166_b = new IIcon[1];
    for (int i = 0; i < this.field_150167_a.length; i++) {
      this.field_150167_a[i] = reg.func_94245_a(func_149641_N());
      this.field_150166_b[i] = reg.func_94245_a(func_149641_N() + "_top");
    } 
  }
  
  private void setSounds(Material material) {
    if (material == Material.field_151576_e) {
      func_149672_a(field_149769_e);
    } else if (material == Material.field_151573_f) {
      func_149672_a(field_149777_j);
    } else if (material == Material.field_151575_d) {
      func_149672_a(field_149766_f);
    } else if (material == Material.field_151592_s) {
      func_149672_a(field_149778_k);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\blocks\BaseBlockLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
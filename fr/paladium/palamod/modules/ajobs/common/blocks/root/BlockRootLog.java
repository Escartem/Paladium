package fr.paladium.palamod.modules.ajobs.common.blocks.root;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.tab.JobsTab;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockRootLog extends BlockLog {
  public BlockRootLog(String texture) {
    func_149663_c(texture);
    func_149658_d("palajobs:" + texture);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
    func_149711_c(2.0F);
    func_149672_a(field_149766_f);
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
  
  public boolean func_149662_c() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\root\BlockRootLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockAnvilMoney extends BlockAnvil {
  private IIcon top;
  
  public BlockAnvilMoney() {
    func_149663_c("anvil_money");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(5.0F);
    func_149672_a(field_149788_p);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    if (this.field_149833_b == 3 && p_149691_1_ == 1)
      return this.top; 
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149761_L = p_149651_1_.func_94245_a("palamod:anvil/anvil_money_base");
    this.top = p_149651_1_.func_94245_a("palamod:anvil/anvil_money_top");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockAnvilMoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
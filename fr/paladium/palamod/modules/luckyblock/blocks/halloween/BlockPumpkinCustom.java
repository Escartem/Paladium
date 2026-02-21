package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockPumpkinCustom extends BlockPumpkin implements ITooltipWiki {
  @SideOnly(Side.CLIENT)
  private IIcon icons_faces;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_top;
  
  private boolean on;
  
  public int variante;
  
  public BlockPumpkinCustom(boolean on, int variante) {
    super(on);
    this.on = on;
    this.variante = variante;
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("pumpkincustom_" + variante);
    func_149658_d("palamod:pumpkin/pumpkin");
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons_top = register.func_94245_a(func_149641_N() + "_top");
    this.field_149761_L = register.func_94245_a(func_149641_N() + "_side");
    this.icons_faces = register.func_94245_a(func_149641_N() + "_" + this.variante);
    super.func_149651_a(register);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? this.icons_top : ((side == 0) ? this.icons_top : ((meta == 2 && side == 2) ? this.icons_faces : ((meta == 3 && side == 5) ? this.icons_faces : ((meta == 0 && side == 3) ? this.icons_faces : ((meta == 1 && side == 4) ? this.icons_faces : this.field_149761_L)))));
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.1-luckystats-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockPumpkinCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
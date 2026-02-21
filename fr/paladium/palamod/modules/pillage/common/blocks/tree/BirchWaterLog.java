package fr.paladium.palamod.modules.pillage.common.blocks.tree;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BirchWaterLog extends BlockLog implements ITooltipWiki {
  @SideOnly(Side.CLIENT)
  private IIcon top;
  
  @SideOnly(Side.CLIENT)
  private IIcon side;
  
  public BirchWaterLog(String name) {
    func_149663_c(name);
    func_149658_d("palamod:pillage/" + name);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    GameRegistry.registerBlock((Block)this, func_149739_a());
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.side = p_149651_1_.func_94245_a(func_149641_N());
    this.top = p_149651_1_.func_94245_a(func_149641_N() + "_top");
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150163_b(int p_150163_1_) {
    return this.side;
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150161_d(int p_150161_1_) {
    return this.top;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#2.-les-arbres";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\tree\BirchWaterLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
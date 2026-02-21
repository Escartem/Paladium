package fr.paladium.palamod.modules.hunter.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBambooBlock extends Block implements ITooltipWiki {
  private IIcon[] icons = new IIcon[2];
  
  public BlockBambooBlock() {
    super(Material.field_151575_d);
    func_149663_c("bamboo_block");
    func_149658_d("palamod:bamboo_block");
    func_149647_a((CreativeTabs)Registry.HUNTER_TAB);
    func_149711_c(2.0F);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_top");
    super.func_149651_a(register);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side);
    if (dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
      return this.icons[1]; 
    return this.icons[0];
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockBambooBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
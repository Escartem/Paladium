package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDidier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDidier extends Block implements ITooltipWiki {
  private IIcon[] icons;
  
  public BlockDidier() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("didier");
    func_149658_d("palamod:didier");
    func_149711_c(1.0F);
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderDidier;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public void func_149651_a(IIconRegister icon) {
    this.icons = new IIcon[1];
    this.icons[0] = icon.func_94245_a("palamod:fuzeshop");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta];
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityDidier();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockDidier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
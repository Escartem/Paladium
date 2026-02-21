package fr.paladium.palamod.modules.hunter.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityBamboo;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockBamboo extends Block implements ITooltipWiki {
  public BlockBamboo() {
    super(Material.field_151575_d);
    func_149647_a((CreativeTabs)Registry.HUNTER_TAB);
    func_149663_c("bamboo");
    func_149676_a(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
    func_149658_d("palamod:bamboo_block_side");
    func_149675_a(true);
    func_149711_c(1.0F);
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    checkAndDropBlock(world, x, y, z);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rdm) {
    if ((world.func_147439_a(x, y - 1, z) == BlocksRegister.BAMBOO_BLOCK || checkAndDropBlock(world, x, y, z)) && 
      world.func_147437_c(x, y + 1, z)) {
      int offsetY;
      for (offsetY = 1; world.func_147439_a(x, y - offsetY, z) == this; offsetY++);
      if (offsetY < 3) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata >= 15) {
          world.func_147449_b(x, y + 1, z, this);
          world.func_72921_c(x, y, z, 0, 4);
        } else {
          world.func_72921_c(x, y, z, metadata + 1, 4);
        } 
      } 
    } 
  }
  
  private boolean checkAndDropBlock(World world, int x, int y, int z) {
    if (!func_149718_j(world, x, y, z)) {
      func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147468_f(x, y, z);
      return false;
    } 
    return true;
  }
  
  public boolean func_149718_j(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y - 1, z) == this || world.func_147439_a(x, y - 1, z) == Blocks.field_150354_m || world.func_147439_a(x, y - 1, z) == Blocks.field_150349_c || world.func_147439_a(x, y - 1, z) == Blocks.field_150346_d);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderBamboo;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityBamboo();
  }
  
  public int func_149656_h() {
    return 0;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
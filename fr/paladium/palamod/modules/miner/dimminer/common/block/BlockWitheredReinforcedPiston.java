package fr.paladium.palamod.modules.miner.dimminer.common.block;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitheredReinforcedPiston extends Block implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[3];
  
  public BlockWitheredReinforcedPiston() {
    super(Material.field_76233_E);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(5.0F);
    func_149663_c("withered_reinforced_piston");
    func_149658_d("palamod:withered_reinforced_piston");
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_front");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[2] = register.func_94245_a(func_149641_N() + "_side");
    this.field_149761_L = this.icons[0];
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
    int direction = MathHelper.func_76128_c((living.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
    world.func_72921_c(x, y, z, direction, 2);
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    int meta = ba.func_72805_g(x, y, z);
    if (dir == 0)
      return this.icons[2]; 
    if ((dir == 4 && meta == 1) || (dir == 2 && meta == 2) || (dir == 5 && meta == 3) || (dir == 3 && meta == 0))
      return this.icons[0]; 
    if (dir == 1)
      return this.icons[2]; 
    return this.icons[1];
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K && 
      isIndirectlyPowered(world, x, y, z, 5))
      if (world.func_147439_a(x + 1, y, z) == Blocks.field_150343_Z) {
        world.func_147449_b(x + 1, y, z, BlocksRegister.WITHERED_OBSIDIAN);
        world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "tile.piston.out", 0.5F, world.field_73012_v.nextFloat() * 0.25F + 0.6F);
      } else if (world.func_147439_a(x - 1, y, z) == Blocks.field_150343_Z) {
        world.func_147449_b(x - 1, y, z, BlocksRegister.WITHERED_OBSIDIAN);
        world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "tile.piston.out", 0.5F, world.field_73012_v.nextFloat() * 0.25F + 0.6F);
      } else if (world.func_147439_a(x, y, z + 1) == Blocks.field_150343_Z) {
        world.func_147449_b(x, y, z + 1, BlocksRegister.WITHERED_OBSIDIAN);
        world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "tile.piston.out", 0.5F, world.field_73012_v.nextFloat() * 0.25F + 0.6F);
      } else if (world.func_147439_a(x, y, z - 1) == Blocks.field_150343_Z) {
        world.func_147449_b(x, y, z - 1, BlocksRegister.WITHERED_OBSIDIAN);
        world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "tile.piston.out", 0.5F, world.field_73012_v.nextFloat() * 0.25F + 0.6F);
      }  
    super.func_149695_a(world, x, y, z, block);
  }
  
  public boolean isIndirectlyPowered(World world, int x, int y, int z, int p_150072_5_) {
    return (p_150072_5_ != 0 && world.func_94574_k(x, y - 1, z, 0)) ? true : ((p_150072_5_ != 1 && world.func_94574_k(x, y + 1, z, 1)) ? true : ((p_150072_5_ != 2 && world.func_94574_k(x, y, z - 1, 2)) ? true : ((p_150072_5_ != 3 && world.func_94574_k(x, y, z + 1, 3)) ? true : ((p_150072_5_ != 5 && world.func_94574_k(x + 1, y, z, 5)) ? true : ((p_150072_5_ != 4 && world.func_94574_k(x - 1, y, z, 4)) ? true : (world.func_94574_k(x, y, z, 0) ? true : (world.func_94574_k(x, y + 2, z, 1) ? true : (world.func_94574_k(x, y + 1, z - 1, 2) ? true : (world.func_94574_k(x, y + 1, z + 1, 3) ? true : (world.func_94574_k(x - 1, y + 1, z, 4) ? true : world.func_94574_k(x + 1, y + 1, z, 5)))))))))));
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#5.-cobblestone-compressor";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\BlockWitheredReinforcedPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
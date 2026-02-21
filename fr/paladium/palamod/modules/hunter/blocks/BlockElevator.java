package fr.paladium.palamod.modules.hunter.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockElevator extends Block implements ITooltipWiki {
  public static final int[] colors = new int[] { 
      1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 
      4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
  
  public BlockElevator() {
    super(Material.field_151577_b);
    func_149647_a((CreativeTabs)Registry.HUNTER_TAB);
    func_149663_c("elevator_block");
    func_149658_d("palamod:elevator_block");
    func_149752_b(5.0F);
    func_149711_c(1.0F);
  }
  
  public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
    return 15;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
    if (player.func_70694_bm() != null && 
      player.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemDye) {
      int i = MathHelper.func_76125_a(player.func_70694_bm().func_77960_j(), 0, 15);
      world.func_72921_c(x, y, z, i, 1);
      world.func_147471_g(x, y, z);
    } 
    return super.func_149727_a(world, x, y, z, player, meta, hitX, hitY, hitZ);
  }
  
  public int func_149720_d(IBlockAccess access, int x, int y, int z) {
    int metadata = access.func_72805_g(x, y, z);
    return colors[metadata];
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockElevator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
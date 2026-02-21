package fr.paladium.palamod.modules.paladium.common.blocks.decorative;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseBlockStairsHardened extends BlockStairs {
  public BaseBlockStairsHardened(Block base, String unlocalizedName, float resistance, float hardness, String toolClass, int levelTools) {
    super(base, 0);
    func_149647_a((CreativeTabs)Registry.DECORATIVE_TAB);
    func_149663_c(unlocalizedName);
    func_149752_b(resistance);
    func_149711_c(hardness);
    setHarvestLevel(toolClass, levelTools);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    if (world.field_72995_K)
      return false; 
    Block b = world.func_147439_a(x, y, z);
    if (player != null && b != null && player.func_71045_bC() != null) {
      ItemStack is = player.func_71045_bC();
      if (is.func_77973_b().equals(ItemsRegister.MAGICAL_TOOL)) {
        EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Item.func_150898_a(b)));
        if (PFactions.instance.getImpl().hasPermission(player, "BUILD_MAGICAL_TOOL", x, z) != Tristate.FALSE) {
          world.func_72838_d((Entity)item);
          BlockUtils.setBlockToAir(world, x, y, z);
        } 
        return true;
      } 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\decorative\BaseBlockStairsHardened.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.blocks.BaseBlock;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockHardenedObsidian extends BaseBlock implements ITooltipWiki {
  public BlockHardenedObsidian() {
    super(Material.field_151576_e, -1.0F);
    func_149658_d("minecraft:obsidian");
    func_149663_c("hardenedObsidian");
    func_149752_b(100.0F);
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
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-la-construction#1.-les-objets-a-base-dobsidienne";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockHardenedObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
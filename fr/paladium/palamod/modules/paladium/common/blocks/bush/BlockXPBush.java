package fr.paladium.palamod.modules.paladium.common.blocks.bush;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockXPBush extends BaseBlockBush implements ITooltipWiki {
  public BlockXPBush(String unlocalizedName, String[] textureNames) {
    super(unlocalizedName, textureNames);
  }
  
  protected boolean harvest(World world, int x, int y, int z, EntityPlayer player) {
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 12) {
      if (world.field_72995_K)
        return true; 
      world.func_147465_d(x, y, z, (Block)this, meta - 4, 3);
      spawnItemAtPlayer(player, new ItemStack((Item)ItemsRegister.XP_BERRY, 1));
    } 
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\bush\BlockXPBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
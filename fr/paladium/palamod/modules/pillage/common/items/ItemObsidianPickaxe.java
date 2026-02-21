package fr.paladium.palamod.modules.pillage.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemPickaxe;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.ObsiEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemObsidianPickaxe extends BaseItemPickaxe implements ITooltipWiki {
  public ItemObsidianPickaxe(Item.ToolMaterial material, String texture, Item item_repair) {
    super(material, texture, item_repair);
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    Block b = world.func_147439_a(x, y, z);
    if (b instanceof ObsiEffect) {
      ObsiEffect effect = (ObsiEffect)b;
      if (effect.isDirectional) {
        int meta = world.func_72805_g(x, y, z);
        meta++;
        if (!effect.isSpike && meta > 5)
          meta = 0; 
        world.func_72921_c(x, y, z, meta, 2);
      } 
    } 
    return super.func_77648_a(item, player, world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\items\ItemObsidianPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
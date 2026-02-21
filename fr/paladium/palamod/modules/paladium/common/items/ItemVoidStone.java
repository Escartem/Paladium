package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.common.items.BaseItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVoidStone extends BaseItem implements ITooltipWiki {
  public ItemVoidStone(String texture) {
    super(texture);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (world.field_72995_K)
      return itemstack; 
    entityplayer.openGui(PalaMod.instance, PGuiRegistry.GUI_VOIDSTONE, world, 0, 0, 0);
    return itemstack;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemVoidStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
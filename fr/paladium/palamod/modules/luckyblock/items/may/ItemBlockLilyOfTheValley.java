package fr.paladium.palamod.modules.luckyblock.items.may;

import fr.paladium.common.utils.ITooltipInformations;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockLilyOfTheValley extends ItemBlock implements ITooltipInformations {
  public ItemBlockLilyOfTheValley(Block block) {
    super(block);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Fleur du 1er mai.", "Si vous avez ce porte bonheur avec vous,", "les monstres des farmlands seront", "peut être plus généreux." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemBlockLilyOfTheValley.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
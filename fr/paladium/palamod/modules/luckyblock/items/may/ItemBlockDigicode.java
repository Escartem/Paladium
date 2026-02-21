package fr.paladium.palamod.modules.luckyblock.items.may;

import fr.paladium.common.utils.ITooltipInformations;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDigicode extends ItemBlock implements ITooltipInformations {
  public ItemBlockDigicode(Block block) {
    super(block);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Le joueur qui pose le bloc peut définir un code.", "Quand le numéro du code est affiché sur", "le digicode il active la redstone." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemBlockDigicode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
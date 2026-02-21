package fr.paladium.palamod.modules.luckyblock.items.may;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStrangeMetalPiece extends Item implements ITooltipInformations {
  public ItemStrangeMetalPiece() {
    func_77655_b("strange_metal_piece");
    func_111206_d("palamod:strange_metal_piece");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(64);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Donnez 10 exemplaires de cet objet au forgeron.", "Vous pourrez accéder à son atelier", "grâce au Ticket de Juillet." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemStrangeMetalPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
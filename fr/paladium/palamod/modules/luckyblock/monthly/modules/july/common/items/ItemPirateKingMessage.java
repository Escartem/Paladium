package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPirateKingMessage extends Item {
  public static final String NAME = "pirate_king_message";
  
  private static final String[] MESSAGE = new String[] { "&eTu pensais vraiment que ce coffre sur la plage était mon trésor ?", "&eQue mon trésor ne contenait que \"Une pièce\" ? C'est bien mal me connaître ! ", "&eMon trésor se trouve dans une grotte sous mon île du roi des pirates. Explore les fonds marins, ", "&etu trouveras peut être une entrée..." };
  
  public ItemPirateKingMessage() {
    func_77655_b("pirate_king_message");
    func_111206_d("palamod:pirate_king_message");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K) {
      MonthlyUtils.sendMessage(player, MESSAGE);
      return itemStack;
    } 
    return itemStack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemPirateKingMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
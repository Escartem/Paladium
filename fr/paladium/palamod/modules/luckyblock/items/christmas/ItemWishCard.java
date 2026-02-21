package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWishCard extends Item implements ITooltipWiki {
  public ItemWishCard() {
    func_77625_d(64);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("wish_card");
    func_111206_d("palamod:wish_card");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_WISH_CARD, world, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v); 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.-lucky-block-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemWishCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
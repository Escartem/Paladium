package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMulticolorGift extends Item implements ITooltipWiki {
  private static final String GENERIC_NAME = "multicolor_gift";
  
  public ItemMulticolorGift() {
    func_77625_d(64);
    func_77655_b("multicolor_gift");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:multicolor_gift");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      PlayerUtil.addItemStackToInventory(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK), player.field_71071_by);
      PlayerUtil.addItemStackToInventory(new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK), player.field_71071_by);
      PlayerUtil.addItemStackToInventory(new ItemStack((Block)BlocksRegister.BLACK_LUCKY_BLOCK), player.field_71071_by);
      PlayerUtil.consumeInventoryItemWithMeta(player, ItemsRegister.CHRISTMAS_MULTICOLOR_GIFT, 0);
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.-lucky-block-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemMulticolorGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
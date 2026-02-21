package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPresentBag extends Item implements ITooltipWiki {
  public ItemPresentBag() {
    func_77625_d(16);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("present_bag");
    func_111206_d("palamod:present_bag");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      int randomNumber = world.field_73012_v.nextInt(3) + 1;
      PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.PRESENT, randomNumber), player.field_71071_by);
      item.field_77994_a--;
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.-lucky-block-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemPresentBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
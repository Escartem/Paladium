package fr.paladium.palamod.modules.paladium.common.items;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InvalidItem extends Item {
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    super.func_77624_a(item, player, list, flag);
    list.add("§cCet item n'est pas valide.");
    list.add("");
    list.add("§8» §bSi vous pensez qu'il s'agit d'une erreur. Rendez vous sur §6ts.paladium-pvp.fr");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\InvalidItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
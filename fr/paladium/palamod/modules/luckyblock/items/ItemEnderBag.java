package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemEnderBag extends Item {
  public ItemEnderBag() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("ender_bag");
    func_111206_d("palamod:ender_bag");
    func_77625_d(16);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      return stack; 
    player.func_145747_a((IChatComponent)new ChatComponentText("§cCet item est désactivé pour le moment."));
    return stack;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    list.add("");
    list.add("§eCliquez pour ouvrir votre EnderChest");
    super.func_77624_a(item, player, list, flag);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemEnderBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
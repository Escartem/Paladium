package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEasterEgg extends Item {
  public ItemEasterEgg() {
    func_77655_b("easter_egg");
    func_111206_d("palamod:easter_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    MonthlyUtils.sendMessage(player, new String[] { "§eDes codes easter egg ont été cachés sur le spawn de chaque serveur. Sois la première personne à les encoder pour obtenir une belle récompense ! Utilise §b/easteregg consume <code>§e pour tenter ta chance !" });
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
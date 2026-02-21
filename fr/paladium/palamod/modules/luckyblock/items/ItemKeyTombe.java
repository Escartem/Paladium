package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKeyTombe extends Item {
  public ItemKeyTombe() {
    func_77655_b("keytombe");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:keytombe");
    func_77625_d(16);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    (player.field_71071_by.func_70448_g()).field_77994_a--;
    if (PlayerLuckyPassProperties.get(player).getPositionDeath() != null) {
      player.func_70634_a(PlayerLuckyPassProperties.get(player).getPositionDeath().getX(), 
          PlayerLuckyPassProperties.get(player).getPositionDeath().getY(), 
          PlayerLuckyPassProperties.get(player).getPositionDeath().getZ());
      PlayerLuckyPassProperties.get(player).setPositionDeath(null);
    } 
    return item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemKeyTombe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
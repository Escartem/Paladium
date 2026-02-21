package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemMeter extends Item implements ITooltipWiki {
  public static Map<UUID, Vec3> locFirst = new HashMap<>();
  
  public ItemMeter() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("meter");
    func_111206_d("palamod:meter");
    func_77625_d(16);
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (!world.field_72995_K)
      return false; 
    if (!locFirst.containsKey(player.func_110124_au())) {
      locFirst.put(player.func_110124_au(), Vec3.func_72443_a(x, y, z));
      PlayerUtils.sendMessage(player, "§ePremier bloc sélectionné.");
      return true;
    } 
    Vec3 newBlock = Vec3.func_72443_a(x, y, z);
    double diff = newBlock.func_72438_d(locFirst.get(player.func_110124_au()));
    diff = Math.abs(diff);
    locFirst.remove(player.func_110124_au());
    PlayerUtils.sendMessage(player, "§eDistance entre les deux blocs : §b" + (int)diff);
    return true;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    list.add("");
    list.add("§eCliquez sur un bloc, puis");
    list.add("§eun deuxième pour calculer la distance");
    list.add("§eentre les deux.");
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemMeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
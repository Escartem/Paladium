package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers.JulyConfigManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTreasureMap extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Effectue un clic droit en tenant l'objet sur le serveur event pour être téléporté sur l’île du roi des pirates. Consomme l’objet après utilisation.";
  
  public static final String NAME = "treasure_map";
  
  private static final String TELEPORTATION_MESSAGE = "§eVous avez été téléporté sur l'île du roi des pirates !";
  
  private static final String WRONG_SERVER_MESSAGE = "&cUtilises cet objet sur le serveur event pour être téléporté sur l'île du roi des pirates !";
  
  public static final String EVENT_SERVER_NAME = "EVENT";
  
  public ItemTreasureMap() {
    func_77655_b("treasure_map");
    func_111206_d("palamod:treasure_map");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (!CommonModule.getInstance().getConfig().getServerName().equalsIgnoreCase("EVENT")) {
      MonthlyUtils.sendMessage(player, new String[] { "&cUtilises cet objet sur le serveur event pour être téléporté sur l'île du roi des pirates !" });
      return itemStack;
    } 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    teleport(player);
    itemStack.field_77994_a--;
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Effectue un clic droit en tenant l'objet sur le serveur event pour être téléporté sur l’île du roi des pirates. Consomme l’objet après utilisation.");
  }
  
  private void teleport(EntityPlayer player) {
    JulyConfigManager manager = JulyConfigManager.getInstance();
    DoubleLocation location = manager.getConfig().getPirateIslandLocation();
    if (location == null)
      return; 
    MonthlyUtils.sendMessage(player, new String[] { "§eVous avez été téléporté sur l'île du roi des pirates !" });
    location.teleportServer(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemTreasureMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
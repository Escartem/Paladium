package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.combattag.CombatTag;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;

public class LuckyBlockUtils {
  public static final String ON_COMBAT_MESSAGE = "&cVous ne pouvez pas faire ceci en combat !";
  
  public static final String CANT_INTERACT_MESSAGE = "&cVous ne pouvez pas faire cela ici !";
  
  public static final String COOLDOWN_MESSAGE = "&cVous devez attendre %s avant de pouvoir faire ceci !";
  
  public static boolean isInCombat(EntityPlayer player, boolean message) {
    try {
      boolean combat = CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(player.func_110124_au()));
      if (combat && message)
        MonthlyUtils.sendMessage(player, new String[] { "&cVous ne pouvez pas faire ceci en combat !" }); 
      return combat;
    } catch (NoClassDefFoundError|Exception e) {
      return false;
    } 
  }
  
  public static boolean canUseItem(EntityPlayerMP player, boolean message) {
    int blockX = (int)player.field_70165_t;
    int blockY = (int)player.field_70163_u;
    int blockZ = (int)player.field_70161_v;
    return canUseItem(player, blockX, blockY, blockZ, message);
  }
  
  public static boolean canUseItem(EntityPlayerMP player, int x, int y, int z, boolean message) {
    boolean ret = EventUtils.canInteract((EntityPlayer)player, x, y, z);
    if (!ret && message) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous ne pouvez pas faire cela ici !" });
      return false;
    } 
    return true;
  }
  
  public static boolean isInCombat(EntityPlayer player) {
    return isInCombat(player, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\LuckyBlockUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
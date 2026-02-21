package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DiviseXP extends ALuckyEvent {
  private static Map<UUID, Long> diviseXp = new HashMap<>();
  
  public static boolean isXpDivised(EntityPlayer player) {
    if (player == null)
      return false; 
    if (!diviseXp.containsKey(player.func_110124_au()))
      return false; 
    return (((Long)diviseXp.get(player.func_110124_au())).longValue() > System.currentTimeMillis());
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.sendMessage((EntityPlayer)player, "§aL'XP de vos métiers est divisé par 2 pendant 15 minutes !");
    diviseXp.put(player.func_110124_au(), Long.valueOf(System.currentTimeMillis() + 900000L));
  }
  
  public String getName() {
    return "Divise XP";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "divise_xp";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DiviseXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
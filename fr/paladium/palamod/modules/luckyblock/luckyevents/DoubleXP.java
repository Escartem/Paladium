package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladium.common.items.boost.PlayerBoostEProperties;
import fr.paladium.palamod.modules.paladium.common.items.boost.hud.ModuleBoost;
import fr.paladium.palamod.modules.paladium.common.items.boost.packet.PlayerBoostEPropertiesSync;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DoubleXP extends ALuckyEvent {
  private static Map<UUID, Long> doubleXp = new HashMap<>();
  
  public static boolean isXpMultiplied(EntityPlayer player) {
    if (player == null)
      return false; 
    if (!doubleXp.containsKey(player.func_110124_au()))
      return false; 
    return (((Long)doubleXp.get(player.func_110124_au())).longValue() > System.currentTimeMillis());
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.sendMessage((EntityPlayer)player, "§aL'XP de vos métiers est doublée pendant 15 minutes !");
    PlayerUtils.sendMessage((EntityPlayer)player, "§eProfites-en!");
    JobsManager.getInstance().setDoubleXpUntil((EntityPlayer)player, System.currentTimeMillis() + 900000L);
    if (!player.field_70170_p.field_72995_K && player instanceof EntityPlayerMP) {
      PlayerBoostEPropertiesSync senderPacket = new PlayerBoostEPropertiesSync(PlayerBoostEProperties.get((EntityPlayer)player));
      ModuleBoost.getInstance().getNetwork().sendTo((IMessage)senderPacket, player);
    } 
  }
  
  public String getName() {
    return "DoubleXP";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "double_xp";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DoubleXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
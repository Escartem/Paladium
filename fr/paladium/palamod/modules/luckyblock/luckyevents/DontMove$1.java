package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.common.CommonModule;
import fr.paladium.helios.utils.TeleportUtils;
import fr.paladium.homemod.common.dto.Home;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import java.security.SecureRandom;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements IRetrofitCallback<ConcurrentHashMap<String, Home>> {
  public void onSuccess(ConcurrentHashMap<String, Home> homesMap) {
    String serverName = CommonModule.getInstance().getConfig().getServerName();
    Set<Home> homes = (Set<Home>)homesMap.values().stream().filter(home -> home.getServerName().equalsIgnoreCase(serverName)).collect(Collectors.toSet());
    if (homes.size() > 0) {
      SecureRandom random = new SecureRandom();
      int resultRandom = random.nextInt(homes.size());
      Home home = (Home)homes.toArray()[resultRandom];
      TeleportUtils.teleport((EntityPlayer)player, home.getX(), home.getY(), home.getZ(), home.getYaw(), home.getPitch());
    } else {
      player.func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oMince, vous n'avez pas de Home"));
    } 
  }
  
  public void onFail(ConcurrentHashMap<String, Home> stringHomeConcurrentHashMap, Throwable throwable) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oMince, vous n'avez pas de Home"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DontMove$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
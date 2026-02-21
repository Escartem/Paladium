package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.common.CommonModule;
import fr.paladium.helios.utils.TeleportUtils;
import fr.paladium.homemod.common.dto.Home;
import fr.paladium.homemod.server.manager.HomeManager;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.security.SecureRandom;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class DontMove extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    HomeManager.getInstance().getOrLoadHomes(player.func_110124_au(), new IRetrofitCallback<ConcurrentHashMap<String, Home>>() {
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
        });
  }
  
  public String getName() {
    return "Reste à ta place";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "dontmove";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DontMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
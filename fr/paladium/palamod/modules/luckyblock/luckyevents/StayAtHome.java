package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.helios.utils.TeleportUtils;
import fr.paladium.homemod.common.dto.Home;
import fr.paladium.homemod.server.manager.HomeManager;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class StayAtHome extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    try {
      ConcurrentHashMap<String, Home> homes = (ConcurrentHashMap<String, Home>)HomeManager.getInstance().getCachedHomes().getIfPresent(player.func_110124_au());
      Set<Home> homeList = (Set<Home>)homes.values().stream().collect(Collectors.toSet());
      Home randHome = getRandomSetElement(new Random(), homeList);
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      if (pl == null || !pl.isOnline())
        return; 
      if (randHome == null) {
        World bukkitWorld = pl.getWorld();
        Location spawnLocation = bukkitWorld.getSpawnLocation();
        pl.teleport(spawnLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
        return;
      } 
      TeleportUtils.teleport((EntityPlayer)player, randHome.getX(), randHome.getY(), randHome.getZ(), randHome.getYaw(), randHome.getPitch());
    } catch (Exception|NoClassDefFoundError e) {
      e.printStackTrace();
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue lors de l'exécution de l'événement."));
    } 
  }
  
  private <E> E getRandomSetElement(Random random, Set<E> set) {
    if (set == null)
      return null; 
    return set.stream().skip(random.nextInt(set.size())).findFirst().orElse(null);
  }
  
  public String getName() {
    return "Reste à la maison";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "reste_a_la_maison";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\StayAtHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
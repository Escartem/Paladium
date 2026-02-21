package fr.paladium.palajobs.utils.forge.player;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.location.WorldUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerUtils {
  public static EntityPlayer getPlayer(World world, UUID uniqueId) {
    if (world == null)
      return null; 
    return world.func_152378_a(uniqueId);
  }
  
  public static List<EntityPlayer> getPlayers() {
    List<EntityPlayer> players = new ArrayList<>();
    for (World world : WorldUtils.getWorlds()) {
      for (Object object : world.field_73010_i) {
        if (!(object instanceof EntityPlayer))
          continue; 
        players.add((EntityPlayer)object);
      } 
    } 
    return players;
  }
  
  public static EntityPlayer getPlayer(World world, String playerName) {
    if (world == null)
      return null; 
    for (int i = 0; i < world.field_73010_i.size(); i++) {
      EntityPlayer entityplayer = world.field_73010_i.get(i);
      if (playerName.equalsIgnoreCase(entityplayer.func_70005_c_()))
        return entityplayer; 
    } 
    return null;
  }
  
  public static void sendMessage(EntityPlayer player, String... messages) {
    for (String message : messages) {
      if (!message.startsWith("§"))
        message = "§e" + message; 
      player.func_145747_a((IChatComponent)new ChatComponentText(message));
    } 
  }
  
  public static void teleport(EntityPlayer player, Location location) {
    try {
      Player bukkityPlayer = Bukkit.getPlayer(player.func_110124_au());
      Location bukkitLocation = new Location(bukkityPlayer.getWorld(), location.getX(), location.getY(), location.getZ());
      bukkityPlayer.teleport(bukkitLocation);
    } catch (Exception e) {
      player.func_70634_a(location.getX(), location.getY(), location.getZ());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\player\PlayerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
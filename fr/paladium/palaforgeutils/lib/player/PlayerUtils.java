package fr.paladium.palaforgeutils.lib.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PlayerUtils {
  @SideOnly(Side.SERVER)
  public static EntityPlayerMP getPlayer(String name) {
    return getPlayer(name, getOnlinePlayers());
  }
  
  @SideOnly(Side.SERVER)
  public static EntityPlayerMP getPlayer(UUID uuid) {
    return getPlayer(uuid, getOnlinePlayers());
  }
  
  @SideOnly(Side.SERVER)
  public static EntityPlayerMP getPlayer(UUID uuid, List<EntityPlayerMP> players) {
    if (uuid == null || players == null || players.isEmpty())
      return null; 
    return players.stream()
      .filter(player -> player.func_110124_au().equals(uuid))
      .findFirst()
      .orElse(null);
  }
  
  @SideOnly(Side.SERVER)
  public static EntityPlayerMP getPlayer(String name, List<EntityPlayerMP> players) {
    if (name == null || players == null || players.isEmpty())
      return null; 
    return players.stream()
      .filter(player -> player.func_70005_c_().equalsIgnoreCase(name))
      .findFirst()
      .orElse(null);
  }
  
  @SideOnly(Side.SERVER)
  public static List<EntityPlayerMP> getOnlinePlayers() {
    return (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\player\PlayerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
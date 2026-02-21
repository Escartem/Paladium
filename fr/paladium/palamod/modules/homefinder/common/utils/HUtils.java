package fr.paladium.palamod.modules.homefinder.common.utils;

import fr.paladium.palamod.modules.homefinder.common.blocks.BlockHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.managers.HomeFinderManager;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.SearchStatus;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class HUtils {
  public static List<TileEntityHomeFinder> getHomeFinders(World world, Location target, int radius) {
    HomeFinderManager manager = HomeFinderManager.getInstance();
    List<Location> findersLocation = new ArrayList<>();
    List<TileEntityHomeFinder> finders = new ArrayList<>();
    List<Location> toRemove = new ArrayList<>();
    for (Location location : manager.getFinders()) {
      if (location.distanceTo(target) > radius || findersLocation.contains(location))
        continue; 
      Block block = world.func_147439_a(location.getBlockX(), location.getBlockY(), location.getBlockZ());
      if (!(block instanceof BlockHomeFinder)) {
        toRemove.add(location);
        continue;
      } 
      TileEntityHomeFinder finder = (TileEntityHomeFinder)world.func_147438_o(location.getBlockX(), location.getBlockY(), location.getBlockZ());
      finders.add(finder);
      findersLocation.add(location);
    } 
    for (Location location : toRemove)
      manager.getFinders().remove(location); 
    return finders;
  }
  
  public static String formatTime(long time) {
    if (time < 0L)
      return "0s"; 
    long seconds = time / 1000L;
    long minutes = seconds / 60L;
    if (minutes > 0L)
      return minutes + "m " + (seconds % 60L) + "s"; 
    return seconds + "s";
  }
  
  public static boolean canPlaceHome(World world, Location location, EntityPlayer player) {
    List<TileEntityHomeFinder> finders = new ArrayList<>();
    int maxRadius = RadiusEnum.FIVE_FLOORS.getRadius();
    finders = getHomeFinders(world, location, maxRadius);
    for (TileEntityHomeFinder finder : finders) {
      Location finderLocation = new Location(finder.field_145851_c, finder.field_145848_d, finder.field_145849_e);
      int radius = RadiusEnum.getRadiusFromFloor(((BlockHomeFinder)finder.func_145838_q()).getTowerSize(world, (int)finderLocation.getX(), (int)finderLocation.getY(), (int)finderLocation.getZ()));
      int distance = (int)finderLocation.distanceTo(location);
      if (distance > radius)
        continue; 
      if (finder.isCovered() && finder.isClaimed() && (finder.getSearchStatus() == SearchStatus.LOCKED || finder.getSearchStatus() == SearchStatus.NONE) && 
        !finder.canPlayerUseHome(player))
        return false; 
    } 
    return true;
  }
  
  public static TileEntityHomeFinder getHomeFinder(World world, int x, int y, int z) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityHomeFinder))
      return null; 
    return (TileEntityHomeFinder)tile;
  }
  
  public static void sendMessage(EntityPlayer player, String... messages) {
    for (String message : messages)
      player.func_145747_a((IChatComponent)new ChatComponentText(message)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\commo\\utils\HUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
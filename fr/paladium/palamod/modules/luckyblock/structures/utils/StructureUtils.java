package fr.paladium.palamod.modules.luckyblock.structures.utils;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import fr.paladium.factions.api.util.Vec2i;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class StructureUtils {
  public static final int NEVER = -1;
  
  public static final String UNABLE_TO_LOAD_MESSAGE = "&cIl n'a pas été possible de charger la structure.";
  
  public static final String UNABLE_TO_PASTE_MESSAGE = "&cIl n'a pas été possible de coller la structure.";
  
  public static final String CANT_PASTE_HERE_MESSAGE = "&cVous ne pouvez pas coller la structure ici.";
  
  public static final String SCHEMATIC_WAITING_PASTE_MESSAGE = "&aVotre structure est en cours de collage...";
  
  public static final String SCHEMATIC_PASTED_MESSAGE = "&aVotre structure a été collée avec succès.";
  
  public static boolean canPaste(EntityPlayer player, Schematic schematic, DoubleLocation location, boolean checkAir) {
    if (schematic == null)
      return false; 
    World world = player.func_130014_f_();
    int x = location.getBlockX();
    int y = location.getBlockY();
    int z = location.getBlockZ();
    for (BlockData data : schematic.getBlocks()) {
      BlockPos pos = data.getPos();
      int posX = x + pos.getX();
      int posY = y + pos.getY();
      int posZ = z + pos.getZ();
      if ((checkAir && !world.func_147437_c(posX, posY, posZ)) || !EventUtils.canInteract(player, posX, posY, posZ))
        return false; 
    } 
    return true;
  }
  
  public static void pasteTimed(EntityPlayer player, Schematic schematic, DoubleLocation location, long expirationMillis, boolean checkAir, ITimedCallBack<TimedSchematic> callback) {
    Thread thread = new Thread(() -> {
          if (!canPaste(player, schematic, location, checkAir)) {
            callback.accept(null);
            return;
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        });
    thread.start();
  }
  
  public static void pasteTimedAsync(EntityPlayer player, Schematic schematic, DoubleLocation location, long expirationMillis, boolean checkAir, ITimedCallBack<TimedSchematic> callback) {
    Thread thread = new Thread(() -> {
          if (!canPaste(player, schematic, location, checkAir)) {
            callback.accept(null);
            return;
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        });
    thread.start();
  }
  
  public static interface ITimedCallBack<T> {
    void accept(T param1T);
  }
  
  public static abstract class ATimedSchematic {
    private final TimedSchematic timedSchematic;
    
    private final StructureUtils.ITimedCallBack<TimedSchematic> callback;
    
    public ATimedSchematic(TimedSchematic timedSchematic, StructureUtils.ITimedCallBack<TimedSchematic> callback) {
      this.timedSchematic = timedSchematic;
      this.callback = callback;
    }
  }
  
  public static TimedSchematic pasteTimed(EntityPlayer player, Schematic schematic, DoubleLocation location, long expirationMillis, boolean checkAir) {
    if (!canPaste(player, schematic, location, checkAir))
      return null; 
    BlockPos pos = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    return SchematicUtils.pasteTimedSchematic(player.func_130014_f_(), pos, schematic, true, true, expirationMillis);
  }
  
  public static boolean paste(EntityPlayer player, Schematic schematic, DoubleLocation location, boolean checkAir) {
    if (!canPaste(player, schematic, location, checkAir))
      return false; 
    BlockPos pos = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    SchematicUtils.pasteSchematic(player.func_130014_f_(), pos, schematic, false, true);
    return true;
  }
  
  public static List<Chunk> getChunks(Cuboid cuboid) {
    List<Chunk> chunks = new ArrayList<>();
    for (int x = (int)cuboid.getMinX(); x <= cuboid.getMaxX(); x += 16) {
      for (int z = (int)cuboid.getMinZ(); z <= cuboid.getMaxZ(); z += 16) {
        Chunk chunk = cuboid.getWorld().func_72964_e(x >> 4, z >> 4);
        for (Chunk c : chunks) {
          if (c.field_76635_g != chunk.field_76635_g || c.field_76647_h == chunk.field_76647_h);
        } 
        chunks.add(chunk);
      } 
    } 
    return chunks;
  }
  
  public static boolean isClaimed(List<Chunk> chunks) {
    boolean claimed = false;
    for (Chunk chunk : chunks) {
      if (ClaimController.getInstance().isClaimed(new Vec2i(chunk.field_76635_g, chunk.field_76647_h))) {
        claimed = true;
        break;
      } 
    } 
    return claimed;
  }
  
  public static boolean isAirAtLocation(Location location) {
    World world = location.getWorld();
    if (world == null)
      return false; 
    return world.func_147437_c(
        (int)location.getX(), 
        (int)location.getY(), 
        (int)location.getZ());
  }
  
  public static boolean isBlocksAtLocation(Location location, Block... blocks) {
    World world = location.getWorld();
    if (world == null)
      return false; 
    Block blockAtLocation = location.getBlock();
    for (Block block : blocks) {
      if (blockAtLocation == block)
        return true; 
    } 
    return false;
  }
  
  public static boolean isBedrockAtLocation(Location location) {
    return isBlocksAtLocation(location, new Block[] { Blocks.field_150357_h });
  }
  
  public static boolean isPlayableAtLocation(Location location) {
    World world = location.getWorld();
    int y = (int)location.getY();
    if (world == null)
      return false; 
    return (y > 0 && y < 256);
  }
  
  public static boolean containsWorldguardFlag(Location location) {
    try {
      Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
      WorldGuardPlugin worldGuardPlugin = (WorldGuardPlugin)plugin;
      Location bukkitLocation = new Location(Bukkit.getWorld(location.getWorldName()), location.getX(), location.getY(), location.getZ());
      RegionContainer container = worldGuardPlugin.getRegionContainer();
      return container.get(bukkitLocation.getWorld()).getApplicableRegions(bukkitLocation).getRegions().isEmpty();
    } catch (Exception e) {
      return false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structure\\utils\StructureUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
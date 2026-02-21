package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palaschematic.PalaSchematic;
import fr.paladium.palaschematic.utils.BlockData;
import fr.paladium.palaschematic.utils.BlockPos;
import fr.paladium.palaschematic.utils.Schematic;
import java.io.File;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@SideOnly(Side.SERVER)
public class SchematicUtils {
  public static final String UNABLE_TO_PASTE_MESSAGE = "&cIl n'a pas été possible de coller la structure.";
  
  public static final String CANT_PASTE_HERE_MESSAGE = "&cVous ne pouvez pas coller la structure ici.";
  
  public static Schematic load(String schematicName) throws IOException {
    File schematicFile = new File(PalaMod.conf, schematicName);
    return PalaSchematic.get().getSchematicManager().load(schematicFile);
  }
  
  public static Schematic paste(EntityPlayer player, Schematic schematic, World world, int x, int y, int z, boolean checkAir) {
    return paste(player, schematic, world, new DoubleLocation(x, y, z), checkAir);
  }
  
  public static Schematic paste(EntityPlayer player, Schematic schematic, World world, DoubleLocation location, boolean checkAir) {
    if (schematic == null)
      return null; 
    int x = location.getBlockX();
    int y = location.getBlockY();
    int z = location.getBlockZ();
    for (BlockData data : schematic.getBlocks()) {
      BlockPos pos = data.getPos();
      if (!EventUtils.canInteract(player, x + pos.getX(), y + pos.getY(), z + pos.getZ())) {
        schematic = null;
        return null;
      } 
      if (checkAir && !world.func_147437_c(x + pos.getX(), y + pos.getY(), z + pos.getZ())) {
        schematic = null;
        return null;
      } 
    } 
    PalaSchematic.get().getSchematicManager().paste(schematic, new Location(
          
          Bukkit.getWorlds().get(0), x, y, z));
    return schematic;
  }
  
  public static void clean(Schematic schematic, World world, int x, int y, int z) {
    clean(schematic, world, new DoubleLocation(x, y, z));
  }
  
  public static void clean(Schematic schematic, World world, DoubleLocation location) {
    if (schematic == null)
      return; 
    int x = location.getBlockX();
    int y = location.getBlockY();
    int z = location.getBlockZ();
    for (BlockData data : schematic.getBlocks()) {
      BlockPos pos = data.getPos();
      int newX = x + pos.getX();
      int newY = y + pos.getY();
      int newZ = z + pos.getZ();
      if (newY <= 0 || newY > 256)
        continue; 
      if (world.func_147439_a(newX, newY, newZ) == Blocks.field_150357_h)
        continue; 
      world.func_147468_f(newX, newY, newZ);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\SchematicUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
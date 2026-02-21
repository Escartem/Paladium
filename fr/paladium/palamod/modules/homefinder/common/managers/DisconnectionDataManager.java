package fr.paladium.palamod.modules.homefinder.common.managers;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palaforgeutils.lib.java.hash.LongHash;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.homefinder.common.data.DisconnectionData;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationSaver;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.commons.io.FileUtils;

public class DisconnectionDataManager {
  private static final String DATA_PATH = "world/data/homefinder/chunks/";
  
  private static DisconnectionDataManager instance;
  
  public static DisconnectionDataManager getInstance() {
    return instance;
  }
  
  public DisconnectionDataManager() {
    instance = this;
  }
  
  public void addDisconnection(EntityPlayer player, boolean shouldChangeLocation) {
    addDisconnection(player
        .getDisplayName(), player.func_110124_au(), player.field_70170_p, new Location(player.field_70165_t, player.field_70163_u, player.field_70161_v), shouldChangeLocation);
  }
  
  public void addDisconnection(String playerName, UUID uniqueId, World world, Location location, boolean shouldChangeLocation) {
    File file = new File(getChunkFolder(location.getBlockX() >> 4, location.getBlockZ() >> 4), uniqueId.toString() + ".dat");
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("playerName", playerName);
    compound.func_74778_a("uniqueId", UUIDUtils.toString(uniqueId));
    compound.func_74768_a("locationX", location.getBlockX());
    compound.func_74768_a("locationY", location.getBlockY());
    compound.func_74768_a("locationZ", location.getBlockZ());
    compound.func_74757_a("cancelled", shouldChangeLocation);
    try {
      CompressedStreamTools.func_74799_a(compound, new FileOutputStream(file));
    } catch (Exception e) {
      Constants.logger.error("Error while saving disconnection data for player {} (path: {})", new Object[] { playerName, file.getPath(), e });
    } 
  }
  
  public void removeDisconnection(String playerName, UUID uniqueId, World world, Location location) {
    File file = new File(getChunkFolder(location.getBlockX() >> 4, location.getBlockZ() >> 4), uniqueId.toString() + ".dat");
    if (file.exists())
      try {
        FileUtils.forceDelete(file);
      } catch (IOException e) {
        e.printStackTrace();
      }  
  }
  
  public DisconnectionData getDisconnection(String displayName, UUID uniqueId, World world, Location location) {
    File file = new File(getChunkFolder(location.getBlockX() >> 4, location.getBlockZ() >> 4), uniqueId + ".dat");
    if (!file.exists() || !file.isFile())
      return null; 
    try {
      NBTTagCompound compound = CompressedStreamTools.func_74796_a(new BufferedInputStream(FileUtils.openInputStream(file)));
      return new DisconnectionData(file
          .getPath(), displayName, uniqueId, location, compound
          .func_74767_n("cancelled"));
    } catch (Exception exception) {
      Constants.logger.error("Error while loading disconnection data for player {} (path: {})", new Object[] { displayName, file.getPath(), exception });
      return null;
    } 
  }
  
  public DisconnectionData getDisconnection(World world, LocationSaver saved) {
    return getDisconnection(saved.getPlayerName(), saved.getUniqueId(), world, saved.getLocation());
  }
  
  public List<DisconnectionData> getDisconnectionsOnRadius(World world, Location location, int radius) {
    List<DisconnectionData> list = new ArrayList<>();
    List<Integer[]> chunks = (List)new ArrayList<>();
    for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x += 16) {
      for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z += 16) {
        chunks.add(new Integer[] { Integer.valueOf(x >> 4), Integer.valueOf(z >> 4) });
      } 
    } 
    for (Iterator<Integer> iterator = chunks.iterator(); iterator.hasNext(); ) {
      Integer[] chunk = (Integer[])iterator.next();
      File folder = getChunkFolder(chunk[0].intValue(), chunk[1].intValue());
      if (!folder.exists())
        continue; 
      FileUtils.listFiles(folder, new String[] { "dat" }, true).forEach(file -> {
            String name = file.getName().replace(".dat", "");
            try {
              NBTTagCompound compound = CompressedStreamTools.func_74796_a(new BufferedInputStream(FileUtils.openInputStream(file)));
              Location dataLocation = new Location(compound.func_74762_e("locationX"), compound.func_74762_e("locationY"), compound.func_74762_e("locationZ"));
              if (dataLocation.distanceTo(location) <= radius)
                list.add(new DisconnectionData(file.getPath(), compound.func_74779_i("playerName"), UUIDUtils.parseUUID(compound.func_74779_i("uniqueId")), dataLocation, compound.func_74767_n("cancelled"))); 
            } catch (Exception exception) {
              Constants.logger.error("Error while loading disconnection data for player {} (path: {})", new Object[] { name, file.getPath(), exception });
            } 
          });
    } 
    return list;
  }
  
  public String getChunkId(int chunkX, int chunkZ) {
    return String.valueOf(LongHash.toLong(chunkX, chunkZ));
  }
  
  public File getChunkFolder(int chunkX, int chunkZ) {
    File folder = new File(getFolder(), getChunkId(chunkX, chunkZ));
    if (!folder.exists())
      folder.mkdirs(); 
    return folder;
  }
  
  public File getFolder() {
    File folder = FMLCommonHandler.instance().getMinecraftServerInstance().func_71209_f("world/data/homefinder/chunks/");
    if (!folder.exists())
      folder.mkdirs(); 
    return folder;
  }
  
  public File getPlayerFolder(World world, Location location, UUID uniqueId) {
    File folder = new File(getChunkFolder(location.getBlockX() >> 4, location.getBlockZ() >> 4), uniqueId.toString());
    if (!folder.exists())
      folder.mkdirs(); 
    return folder;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\managers\DisconnectionDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
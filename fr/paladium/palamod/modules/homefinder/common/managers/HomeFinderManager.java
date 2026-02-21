package fr.paladium.palamod.modules.homefinder.common.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.homefinder.common.data.DisconnectionData;
import fr.paladium.palamod.modules.homefinder.common.data.FlatFileObject;
import fr.paladium.palamod.modules.homefinder.common.data.HomeFinderData;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import fr.paladium.palamod.modules.homefinder.server.data.HomeFinderWorldData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import org.apache.commons.io.FileUtils;

public class HomeFinderManager implements FlatFileObject {
  private static HomeFinderManager instance;
  
  public static HomeFinderManager getInstance() {
    return instance;
  }
  
  private final Gson gson = (new GsonBuilder()).setPrettyPrinting().serializeNulls().create();
  
  private Long saveIntervalMillis;
  
  private final Long lastSaveMillis;
  
  private final File configFile;
  
  private final Executor executor;
  
  public Gson getGson() {
    return this.gson;
  }
  
  public Long getSaveIntervalMillis() {
    return this.saveIntervalMillis;
  }
  
  public Long getLastSaveMillis() {
    return this.lastSaveMillis;
  }
  
  public File getConfigFile() {
    return this.configFile;
  }
  
  public Executor getExecutor() {
    return this.executor;
  }
  
  public HomeFinderManager() {
    instance = this;
    this.lastSaveMillis = Long.valueOf(System.currentTimeMillis());
    this.configFile = PalaMod.conf;
    this.executor = Executors.newSingleThreadExecutor();
  }
  
  public File getFile() {
    (new File(this.configFile, "disconnections")).mkdirs();
    return new File(this.configFile, "disconnections/finders.json");
  }
  
  @Deprecated
  public void load() {
    HomeFinderData config;
    File file = getFile();
    if (!file.exists())
      return; 
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      config = (HomeFinderData)this.gson.fromJson(reader, HomeFinderData.class);
    } catch (Exception e) {
      throw new RuntimeException("Error while loading config", e);
    } 
    if (config == null)
      return; 
    HomeFinderWorldData data = HomeFinderWorldData.get();
    if (data == null)
      return; 
    DisconnectionDataManager manager = DisconnectionDataManager.getInstance();
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    config.getDisconnections().forEach((uuid, disconnection) -> manager.addDisconnection(disconnection.getPlayerName(), uuid, world, disconnection.getLocation(), disconnection.isCancelled()));
    data.setFinders(config.getFinders());
    data.func_76185_a();
    try {
      FileUtils.forceDelete(file);
    } catch (IOException e) {
      e.printStackTrace();
      Constants.logger.warn("Unable to delete file {}", new Object[] { file.getName() });
    } 
  }
  
  @Deprecated
  public void save() {}
  
  public Long getSaveInterval() {
    return this.saveIntervalMillis;
  }
  
  public List<Location> getFinders() {
    HomeFinderWorldData data = HomeFinderWorldData.get();
    if (data == null)
      return new ArrayList<>(); 
    List<Location> finders = data.getFinders();
    if (finders == null) {
      Constants.logger.warn("HomeFinder => Finders list is null, creating a new one");
      finders = new ArrayList<>();
    } 
    return finders;
  }
  
  public void setSaveInterval() {
    this.saveIntervalMillis = Long.valueOf(System.currentTimeMillis());
  }
  
  public boolean canSave() {
    return (System.currentTimeMillis() - this.lastSaveMillis.longValue() >= this.saveIntervalMillis.longValue());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\managers\HomeFinderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
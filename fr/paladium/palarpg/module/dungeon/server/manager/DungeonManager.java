package fr.paladium.palarpg.module.dungeon.server.manager;

import com.google.gson.JsonObject;
import fr.paladium.palaconfiguration.server.dto.file.RemoteDirectory;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonGlobalConfig;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonRankingConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelEntitiesConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelLootsConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import lombok.NonNull;
import org.apache.commons.io.FileUtils;

public class DungeonManager {
  private static final Map<String, DungeonConfig> DUNGEON_MAP = new HashMap<>();
  
  private static final AtomicReference<DungeonGlobalConfig> GLOBAL_CONFIG = new AtomicReference<>(null);
  
  private static final long TIMEOUT = 15L;
  
  private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS;
  
  public static void load() throws Exception {
    long start = System.currentTimeMillis();
    DungeonModule.logger.info("Loading dungeons from Config-API", new Object[0]);
    RemoteDirectory directory = ((RemoteObject)get(ConfigurationManager.getInstance().getFile("rpg/dungeon"))).asDirectory();
    for (RemoteObject object : directory.listFiles()) {
      if (!object.isDirectory()) {
        if ("config.json".equals(object.getName()))
          GLOBAL_CONFIG.set(get(object.asFile().getContentObject(DungeonGlobalConfig.class))); 
        if ("ranking.json".equals(object.getName()))
          DungeonRankingManager.load(get(object.asFile().getContentObject(DungeonRankingConfig.class))); 
        continue;
      } 
      DungeonModule.logger.info("Loading dungeon " + object.getName(), new Object[0]);
      RemoteDirectory dungeonDirectory = object.asDirectory();
      Optional<RemoteObject> optionalRooms = dungeonDirectory.getFile("rooms");
      if (!optionalRooms.isPresent() || !((RemoteObject)optionalRooms.get()).isDirectory()) {
        DungeonModule.logger.warn("No rooms directory found in dungeon " + dungeonDirectory.getName(), new Object[0]);
        continue;
      } 
      Optional<RemoteObject> optionalLevels = dungeonDirectory.getFile("levels");
      if (!optionalLevels.isPresent() || !((RemoteObject)optionalLevels.get()).isDirectory()) {
        DungeonModule.logger.warn("No levels directory found in dungeon " + dungeonDirectory.getName(), new Object[0]);
        continue;
      } 
      Optional<RemoteObject> optionalData = dungeonDirectory.getFile("data.json");
      if (!optionalData.isPresent() || !((RemoteObject)optionalData.get()).isFile()) {
        DungeonModule.logger.warn("No data.json file found in dungeon " + dungeonDirectory.getName(), new Object[0]);
        continue;
      } 
      List<DungeonRoomConfig> rooms = new ArrayList<>();
      for (DungeonRoomConfig.DungeonRoomType type : DungeonRoomConfig.DungeonRoomType.values()) {
        Optional<RemoteObject> optionalType = ((RemoteObject)optionalRooms.get()).asDirectory().getFile(type.name().toLowerCase());
        if (!optionalType.isPresent() || !((RemoteObject)optionalType.get()).isDirectory()) {
          DungeonModule.logger.warn("No " + type.name().toLowerCase() + " room directory found in dungeon " + dungeonDirectory.getName(), new Object[0]);
        } else {
          RemoteDirectory typeDirectory = ((RemoteObject)optionalType.get()).asDirectory();
          for (RemoteObject room : typeDirectory.listFiles()) {
            if (!room.isDirectory())
              continue; 
            RemoteDirectory roomDirectory = room.asDirectory();
            Optional<RemoteObject> optionalSchematic = roomDirectory.getFile("room.palaschematic");
            if (!optionalSchematic.isPresent() || !((RemoteObject)optionalSchematic.get()).isFile()) {
              DungeonModule.logger.warn("No room.palaschematic file found in room " + room.getName() + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
              continue;
            } 
            Optional<RemoteObject> optionalRoomData = roomDirectory.getFile("data.json");
            if (!optionalRoomData.isPresent() || !((RemoteObject)optionalRoomData.get()).isFile()) {
              DungeonModule.logger.warn("No data.json file found in room " + room.getName() + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
              continue;
            } 
            File file = new File("assets/paladium/rpg/schematics/" + dungeonDirectory.getName() + "/" + type.name().toLowerCase() + "/" + room.getName() + ".palaschematic");
            if (file.exists())
              FileUtils.forceDelete(file); 
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
              DungeonModule.logger.warn("Unable to create directory " + file.getAbsolutePath(), new Object[0]);
              continue;
            } 
            File schematicFile = get(((RemoteObject)optionalSchematic.get()).asFile().download(file));
            DungeonRoomConfig roomConfig = ((DungeonRoomConfig)get(((RemoteObject)optionalRoomData.get()).asFile().getContentObject(DungeonRoomConfig.class))).build(roomDirectory.getName(), type, schematicFile);
            rooms.add(roomConfig);
          } 
        } 
      } 
      DungeonLevelConfig infiniteLevel = null;
      List<DungeonLevelConfig> levels = new ArrayList<>();
      for (RemoteObject levelObject : ((RemoteObject)optionalLevels.get()).asDirectory().listFiles()) {
        if (!levelObject.isDirectory())
          continue; 
        String levelName = levelObject.getName();
        int startLevelId = 0;
        int endLevelId = 0;
        if ("[infinite]".equals(levelName)) {
          startLevelId = -1;
          endLevelId = -1;
        } else if (levelName.contains(";")) {
          String[] levelParts = levelName.split(";");
          if (levelParts[0].startsWith("[")) {
            startLevelId = Integer.parseInt(levelParts[0].replace("[", ""));
          } else if (levelParts[0].startsWith("]")) {
            startLevelId = Integer.parseInt(levelParts[0].replace("]", "")) + 1;
          } else {
            throw new IllegalArgumentException("Invalid level range: " + levelName + " in dungeon " + dungeonDirectory.getName());
          } 
          if (levelParts[1].endsWith("]")) {
            endLevelId = Integer.parseInt(levelParts[1].replace("]", ""));
          } else if (levelParts[1].endsWith("[")) {
            endLevelId = Integer.parseInt(levelParts[1].replace("[", "")) - 1;
          } else {
            throw new IllegalArgumentException("Invalid level range: " + levelName + " in dungeon " + dungeonDirectory.getName());
          } 
        } else {
          startLevelId = Integer.parseInt(levelName);
          endLevelId = startLevelId;
        } 
        for (int levelId = startLevelId; levelId <= endLevelId; levelId++) {
          try {
            String levelNameId = (levelId == -1) ? "[infinite]" : String.valueOf(levelId);
            Optional<RemoteObject> optionalEntities = levelObject.asDirectory().getFile("entities");
            if (!optionalEntities.isPresent() || !((RemoteObject)optionalEntities.get()).isDirectory()) {
              DungeonModule.logger.warn("No entities directory found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
            } else {
              Optional<RemoteObject> optionalLoots = levelObject.asDirectory().getFile("loots");
              if (!optionalLoots.isPresent() || !((RemoteObject)optionalLoots.get()).isDirectory()) {
                DungeonModule.logger.warn("No loots directory found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
              } else {
                Optional<RemoteObject> optionalLevelData = levelObject.asDirectory().getFile("data.json");
                if (!optionalLevelData.isPresent() || !((RemoteObject)optionalLevelData.get()).isFile()) {
                  DungeonModule.logger.warn("No data.json file found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
                } else {
                  DungeonLevelConfig levelConfig = get(((RemoteObject)optionalLevelData.get()).asFile().getContentObject(DungeonLevelConfig.class));
                  if (levelConfig.getRawRooms() == null || levelConfig.getRawRooms().isEmpty()) {
                    DungeonModule.logger.warn("No rooms found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
                  } else if (levelConfig.getGeneration() == null) {
                    DungeonModule.logger.warn("No generation config found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
                  } else if (levelConfig.getGenerationConfig(DungeonRoomConfig.DungeonRoomType.BOSS).isPresent()) {
                    DungeonModule.logger.warn("Generation config for boss room found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName() + ". This is not supported and will be ignored.", new Object[0]);
                  } else if (levelConfig.getGenerationConfig(DungeonRoomConfig.DungeonRoomType.MINIBOSS).isPresent()) {
                    DungeonModule.logger.warn("Generation config for miniboss room found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName() + ". This is not supported and will be ignored.", new Object[0]);
                  } else if (levelConfig.getGeneration().isEmpty()) {
                    DungeonModule.logger.warn("No generation config found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
                  } else {
                    boolean shouldContinue = false;
                    for (Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonLevelConfig.DungeonLevelGenerationConfig> entry : (Iterable<Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonLevelConfig.DungeonLevelGenerationConfig>>)levelConfig.getGeneration().entrySet()) {
                      DungeonRoomConfig.DungeonRoomType type = entry.getKey();
                      DungeonLevelConfig.DungeonLevelGenerationConfig generationConfig = entry.getValue();
                      if (generationConfig.getWeight() == null) {
                        DungeonModule.logger.warn("No weight found for " + type.name().toLowerCase() + " room generation config in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
                        shouldContinue = true;
                      } 
                    } 
                    if (!shouldContinue) {
                      List<DungeonRoomConfig> levelRooms = new ArrayList<>();
                      for (String roomName : levelConfig.getRawRooms()) {
                        Optional<DungeonRoomConfig> room = rooms.stream().filter(r -> r.getId().equalsIgnoreCase(roomName)).findFirst();
                        if (!room.isPresent()) {
                          DungeonModule.logger.warn("Room " + roomName + " not found in level " + levelNameId + " of dungeon " + dungeonDirectory.getName(), new Object[0]);
                          continue;
                        } 
                        levelRooms.add(room.get());
                      } 
                      if (levelRooms.size() != levelConfig.getRawRooms().size()) {
                        DungeonModule.logger.warn("Level " + levelNameId + " is invalid in dungeon " + dungeonDirectory.getName(), new Object[0]);
                      } else {
                        boolean valid = true;
                        Map<DungeonLevelConfig.DungeonLevelRoomType, DungeonLevelLootsConfig> levelLoots = new HashMap<>();
                        for (DungeonLevelConfig.DungeonLevelRoomType roomType : DungeonLevelConfig.DungeonLevelRoomType.values()) {
                          if ((roomType != DungeonLevelConfig.DungeonLevelRoomType.BOSS || levelRooms.stream().anyMatch(levelRoom -> (levelRoom.getType() == DungeonRoomConfig.DungeonRoomType.BOSS))) && (roomType != DungeonLevelConfig.DungeonLevelRoomType.MINIBOSS || levelRooms.stream().anyMatch(levelRoom -> (levelRoom.getType() == DungeonRoomConfig.DungeonRoomType.MINIBOSS)))) {
                            String lootFileName = roomType.name().toLowerCase() + ".json";
                            Optional<RemoteObject> optionalLevelLoots = ((RemoteObject)optionalLoots.get()).asDirectory().getFile(lootFileName);
                            if (!optionalLevelLoots.isPresent() || !((RemoteObject)optionalLevelLoots.get()).isFile()) {
                              DungeonModule.logger.error("No " + lootFileName + " file found in level " + levelNameId + "'s loots of dungeon " + dungeonDirectory.getName(), new Object[0]);
                              valid = false;
                              break;
                            } 
                            DungeonLevelLootsConfig lootsConfig = get(((RemoteObject)optionalLevelLoots.get()).asFile().getContentObject(DungeonLevelLootsConfig.class));
                            if (lootsConfig.getLoots() == null) {
                              DungeonModule.logger.error("No " + lootFileName + " file found in level " + levelNameId + "'s loots of dungeon " + dungeonDirectory.getName(), new Object[0]);
                              valid = false;
                            } else if (lootsConfig.getChests() == null || lootsConfig.getChests().isEmpty()) {
                              DungeonModule.logger.error("No chests config found in " + lootFileName + " file in level " + levelNameId + "'s loots of dungeon " + dungeonDirectory.getName(), new Object[0]);
                              valid = false;
                            } else if (lootsConfig.getLoots().isEmpty()) {
                              DungeonModule.logger.warn("Empty " + lootFileName + " file found in level " + levelNameId + "'s loots of dungeon " + dungeonDirectory.getName(), new Object[0]);
                            } else {
                              Optional<RemoteObject> optionalLevelEntities = ((RemoteObject)optionalEntities.get()).asDirectory().getFile(roomType.name().toLowerCase());
                              if (!optionalLevelEntities.isPresent() || !((RemoteObject)optionalLevelEntities.get()).isDirectory()) {
                                DungeonModule.logger.error("No " + roomType.name().toLowerCase() + " directory found in level " + levelNameId + "'s entities of dungeon " + dungeonDirectory.getName(), new Object[0]);
                                valid = false;
                              } else if (valid) {
                                levelLoots.put(roomType, lootsConfig);
                              } 
                            } 
                          } 
                        } 
                        if (!valid) {
                          DungeonModule.logger.warn("Level " + levelNameId + " has invalid loot configuration in dungeon " + dungeonDirectory.getName(), new Object[0]);
                        } else {
                          Map<DungeonLevelConfig.DungeonLevelRoomType, DungeonLevelEntitiesConfig> levelEntities = new HashMap<>();
                          for (DungeonLevelConfig.DungeonLevelRoomType roomType : DungeonLevelConfig.DungeonLevelRoomType.values()) {
                            if ((roomType != DungeonLevelConfig.DungeonLevelRoomType.BOSS || levelRooms.stream().anyMatch(levelRoom -> (levelRoom.getType() == DungeonRoomConfig.DungeonRoomType.BOSS))) && (roomType != DungeonLevelConfig.DungeonLevelRoomType.MINIBOSS || levelRooms.stream().anyMatch(levelRoom -> (levelRoom.getType() == DungeonRoomConfig.DungeonRoomType.MINIBOSS)))) {
                              Optional<RemoteObject> optionalLevelEntities = ((RemoteObject)optionalEntities.get()).asDirectory().getFile(roomType.name().toLowerCase());
                              if (!optionalLevelEntities.isPresent() || !((RemoteObject)optionalLevelEntities.get()).isDirectory()) {
                                DungeonModule.logger.error("No " + roomType.name().toLowerCase() + " directory found in level " + levelNameId + "'s entities of dungeon " + dungeonDirectory.getName(), new Object[0]);
                                valid = false;
                              } else {
                                Map<String, JsonObject> entitiesConfig = new HashMap<>();
                                for (RemoteObject entityObject : ((RemoteObject)optionalLevelEntities.get()).asDirectory().listFiles(true)) {
                                  if (!entityObject.isFile())
                                    continue; 
                                  String entityName = entityObject.getName().replace(".json", "");
                                  JsonObject entityData = get(entityObject.asFile().getContentObject(JsonObject.class));
                                  if (entityData == null) {
                                    DungeonModule.logger.warn("Unable to load " + entityName + " in level " + levelNameId + "'s entities of dungeon " + dungeonDirectory.getName(), new Object[0]);
                                    continue;
                                  } 
                                  if (entitiesConfig.containsKey(entityName)) {
                                    DungeonModule.logger.warn("Duplicate entity " + entityName + " in level " + levelNameId + "'s entities of dungeon " + dungeonDirectory.getName(), new Object[0]);
                                    continue;
                                  } 
                                  entitiesConfig.put(entityName, entityData);
                                } 
                                if (valid)
                                  levelEntities.put(roomType, new DungeonLevelEntitiesConfig(entitiesConfig)); 
                              } 
                            } 
                          } 
                          if (!valid) {
                            DungeonModule.logger.warn("Level " + levelNameId + " is invalid in dungeon " + dungeonDirectory.getName(), new Object[0]);
                          } else {
                            DungeonLevelConfig level = levelConfig.build(levelId, levelEntities, levelLoots, levelRooms);
                            if (levelId == -1) {
                              infiniteLevel = level;
                            } else {
                              levels.add(levelConfig.build(levelId, levelEntities, levelLoots, levelRooms));
                            } 
                          } 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } catch (NumberFormatException e) {
            DungeonModule.logger.warn("Invalid level id " + levelObject.getName() + " in dungeon " + dungeonDirectory.getName(), new Object[0]);
          } 
        } 
      } 
      DungeonConfig dungeonConfig = ((DungeonConfig)get(((RemoteObject)optionalData.get()).asFile().getContentObject(DungeonConfig.class))).build(dungeonDirectory.getName(), rooms, levels, infiniteLevel);
      DUNGEON_MAP.put(dungeonConfig.getId(), dungeonConfig);
      DungeonModule.logger.info("Loaded dungeon " + dungeonConfig.getId() + " with " + rooms.size() + " rooms and " + levels.size() + " levels", new Object[0]);
    } 
    if (GLOBAL_CONFIG.get() == null)
      throw new IllegalStateException("No global config found for dungeons"); 
    if (!DungeonRankingManager.isLoaded())
      throw new IllegalStateException("No ranking config found for dungeons"); 
    DungeonModule.logger.info(DUNGEON_MAP.size() + " dungeons loaded from Config-API in " + (System.currentTimeMillis() - start) + "ms", new Object[0]);
  }
  
  @NonNull
  public static Optional<DungeonConfig> getDungeon(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return Optional.ofNullable(DUNGEON_MAP.get(id));
  }
  
  @NonNull
  public static List<DungeonConfig> getDungeons() {
    return new ArrayList<>(DUNGEON_MAP.values());
  }
  
  @ServerAction(cache = @SidedActionCache(client = "5m", server = "5m"), secure = false)
  @NonNull
  public static CompletableFuture<DungeonGlobalConfig> getGlobalConfig() {
    return ServerActionHook.useServer(context -> {
          CompletableFuture<DungeonGlobalConfig> future = new CompletableFuture<>();
          future.complete(GLOBAL_CONFIG.get());
          return future;
        }new Object[0]);
  }
  
  private static <T> T get(@NonNull CompletableFuture<T> future) throws Exception {
    if (future == null)
      throw new NullPointerException("future is marked non-null but is null"); 
    return future.get(15L, TIMEOUT_UNIT);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\manager\DungeonManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
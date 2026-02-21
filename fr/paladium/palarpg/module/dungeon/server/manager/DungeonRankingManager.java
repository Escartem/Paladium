package fr.paladium.palarpg.module.dungeon.server.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingDataList;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonRankingConfig;
import fr.paladium.palarpg.module.dungeon.server.task.DungeonRankingTask;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DungeonRankingManager {
  private static final String DATABASE_NAME = "palarpg";
  
  private static final String COLLECTION_RANKING_GLOBAL = "dungeon_ranking_global";
  
  private static final String COLLECTION_RANKING_WEEKLY = "dungeon_ranking_weekly";
  
  private static final String COLLECTION_RANKING_WEEKLY_LOCK = "dungeon_ranking_weekly_lock";
  
  private static final AtomicReference<DungeonRankingConfig> CONFIG = new AtomicReference<>(null);
  
  private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(0, 5, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
  
  private static MongoClient mongoClient;
  
  private static MongoDatabase mongoDatabase;
  
  private static boolean loaded;
  
  public static void load(@NonNull DungeonRankingConfig config) {
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    CONFIG.set(config);
    if (config.getDatabase() == null)
      throw new RuntimeException("[DungeonRankingManager] Database is not initialized, please check the configuration file."); 
    String mongoUri = "mongodb://";
    if (config.getDatabase().getUser() != null && !config.getDatabase().getUser().isEmpty()) {
      mongoUri = mongoUri + config.getDatabase().getUser() + ":" + config.getDatabase().getPassword() + "@" + config.getDatabase().getHost() + ":" + config.getDatabase().getPort() + "/" + "palarpg";
    } else {
      mongoUri = mongoUri + config.getDatabase().getHost() + ":" + config.getDatabase().getPort() + "/" + "palarpg";
    } 
    if (mongoClient != null)
      mongoClient.close(); 
    mongoClient = MongoClients.create(mongoUri);
    mongoDatabase = mongoClient.getDatabase("palarpg");
    if (mongoDatabase == null)
      throw new RuntimeException("[DungeonRankingManager] MongoDB is not initialized, please check the configuration file or database."); 
    List<String> collections = (List<String>)mongoDatabase.listCollectionNames().into(new ArrayList());
    if (!collections.contains("dungeon_ranking_global"))
      mongoDatabase.createCollection("dungeon_ranking_global"); 
    if (!collections.contains("dungeon_ranking_weekly"))
      mongoDatabase.createCollection("dungeon_ranking_weekly"); 
    if (!collections.contains("dungeon_ranking_weekly_lock"))
      mongoDatabase.createCollection("dungeon_ranking_weekly_lock"); 
    if (!(new DungeonRankingTask()).start())
      throw new RuntimeException("[DungeonRankingManager] Failed to start DungeonRankingTask."); 
    loaded = true;
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<DungeonRankingConfig> getConfig() {
    return ServerActionHook.useServer(context -> CompletableFuture.completedFuture(CONFIG.get()), new Object[0]);
  }
  
  public static boolean isLoaded() {
    return loaded;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static MongoCollection<Document> getGlobalCollection() {
    return mongoDatabase.getCollection("dungeon_ranking_global");
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<DungeonRankingDataList> getGlobal(@NonNull String dungeon, Integer index) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> CompletableFuture.supplyAsync((), EXECUTOR_SERVICE), new Object[] { dungeon, index });
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<DungeonRankingData> getGlobalPlayer(@NonNull String dungeon, @NonNull String playerUUID) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> CompletableFuture.supplyAsync((), EXECUTOR_SERVICE), new Object[] { dungeon, playerUUID });
  }
  
  @SideOnly(Side.SERVER)
  public static void setGlobal(@NonNull String dungeon, @NonNull EntityPlayer player, int value) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    String playerUUID = UUIDUtils.toString((Entity)player);
    String playerName = player.func_70005_c_();
    EXECUTOR_SERVICE.submit(() -> {
          try {
            MongoCollection<Document> collection = getGlobalCollection();
            Document filter = (new Document("dungeon", dungeon)).append("playerUUID", playerUUID);
            Document update = new Document("$set", (new Document("value", Integer.valueOf(value))).append("playerName", playerName));
            UpdateOptions options = (new UpdateOptions()).upsert(true);
            collection.updateOne((Bson)filter, (Bson)update, options);
          } catch (Exception e) {
            DungeonModule.logger.error("Failed to set global dungeon ranking for dungeon " + dungeon + " and player " + playerUUID, new Object[0]);
            e.printStackTrace();
          } 
        });
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static MongoCollection<Document> getWeeklyCollection() {
    return mongoDatabase.getCollection("dungeon_ranking_weekly");
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static MongoCollection<Document> getWeeklyLockCollection() {
    return mongoDatabase.getCollection("dungeon_ranking_weekly_lock");
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<DungeonRankingDataList> getWeekly(Integer index) {
    return ServerActionHook.useServer(context -> CompletableFuture.supplyAsync((), EXECUTOR_SERVICE), new Object[] { index });
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<DungeonRankingData> getWeeklyPlayer(@NonNull String playerUUID) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> CompletableFuture.supplyAsync((), EXECUTOR_SERVICE), new Object[] { playerUUID });
  }
  
  @SideOnly(Side.SERVER)
  public static void addWeekly(@NonNull EntityPlayer player, int value) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    String playerUUID = UUIDUtils.toString((Entity)player);
    String playerName = player.func_70005_c_();
    EXECUTOR_SERVICE.submit(() -> {
          try {
            MongoCollection<Document> collection = getWeeklyCollection();
            Document filter = new Document("playerUUID", playerUUID);
            Document update = (new Document("$inc", new Document("value", Integer.valueOf(value)))).append("$set", new Document("playerName", playerName));
            UpdateOptions options = (new UpdateOptions()).upsert(true);
            collection.updateOne((Bson)filter, (Bson)update, options);
          } catch (Exception e) {
            DungeonModule.logger.error("Failed to set weekly dungeon ranking for player " + playerUUID, new Object[0]);
            e.printStackTrace();
          } 
        });
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static List<DungeonRankingData> resetWeekly() throws RuntimeException {
    try {
      MongoCollection<Document> collection = getWeeklyCollection();
      List<DungeonRankingData> rankingDataList = new LinkedList<>();
      List<Document> documents = (List<Document>)collection.find().sort((Bson)new Document("value", Integer.valueOf(-1))).into(new ArrayList());
      for (int i = 0; i < documents.size(); i++) {
        Document document = documents.get(i);
        DungeonRankingData rankingData = new DungeonRankingData(i, document.getString("playerUUID"), document.getString("playerName"), ((Number)document.get("value", Number.class)).longValue());
        rankingDataList.add(rankingData);
      } 
      collection.deleteMany((Bson)new Document());
      return rankingDataList;
    } catch (Exception e) {
      DungeonModule.logger.error("Failed to reset weekly dungeon rankings", new Object[0]);
      throw new RuntimeException(e);
    } 
  }
  
  @ServerAction(cache = @SidedActionCache(client = "5m", server = "5m"), secure = false)
  @NonNull
  public static CompletableFuture<Long> getWeeklyResetDate() {
    return ServerActionHook.useServer(context -> {
          ZonedDateTime now = UniversalTimeUtils.nowZoned();
          ZonedDateTime nextMonday = now.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);
          if (!now.isBefore(nextMonday))
            nextMonday = nextMonday.plusWeeks(1L); 
          return CompletableFuture.completedFuture(Long.valueOf(nextMonday.toEpochSecond() * 1000L));
        }new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\manager\DungeonRankingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
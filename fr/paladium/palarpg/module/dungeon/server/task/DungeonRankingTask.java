package fr.paladium.palarpg.module.dungeon.server.task;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonRankingConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonRankingManager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

@Schedule(every = "5m")
public class DungeonRankingTask extends ATask {
  private final String serverId;
  
  public DungeonRankingTask() {
    super("palarpg-dungeon-ranking");
    try {
      this.serverId = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      throw new RuntimeException("[DungeonRankingTask] Unable to retrieve server identifier", e);
    } 
  }
  
  public void run() {
    if (!shouldReset())
      return; 
    reset();
  }
  
  private void reset() {
    try {
      DungeonModule.logger.info("[DungeonRankingTask] Resetting weekly dungeon rankings...", new Object[0]);
      DungeonRankingConfig config = DungeonRankingManager.getConfig().join();
      List<DungeonRankingData> list = DungeonRankingManager.resetWeekly();
      if (!list.isEmpty()) {
        if (!config.getTopRewards().isEmpty())
          for (int i = 0; i < 10; i++) {
            DungeonRankingData data = (list.size() > i) ? list.get(i) : null;
            if (data != null)
              for (DungeonRankingConfig.DungeonRankingRewardConfig reward : config.getTopRewards())
                reward.apply(data);  
          }  
        if (!config.getExperienceRewards().isEmpty())
          for (DungeonRankingData data : list) {
            if (data.getValue() < config.getNeededExperience())
              break; 
            for (DungeonRankingConfig.DungeonRankingRewardConfig reward : config.getExperienceRewards())
              reward.apply(data); 
          }  
      } 
      markDone();
      DungeonModule.logger.info("[DungeonRankingTask] Weekly dungeon rankings have been reset successfully.", new Object[0]);
    } catch (Exception e) {
      e.printStackTrace();
      DungeonModule.logger.error("[DungeonRankingTask] An error occurred while resetting weekly dungeon rankings", new Object[0]);
      return;
    } 
  }
  
  private boolean shouldReset() {
    long ttl = 3600000L;
    MongoCollection<Document> lockCollection = DungeonRankingManager.getWeeklyLockCollection();
    String weekId = getWeekId();
    Document lockDoc = (new Document("_id", weekId)).append("done", Boolean.valueOf(false)).append("lockedBy", this.serverId).append("lockedAt", Boolean.valueOf(true));
    try {
      lockCollection.insertOne(lockDoc);
      return true;
    } catch (Exception exception) {
      Document filter = (new Document("_id", weekId)).append("done", Boolean.valueOf(false)).append("lockedAt", new Document("$lt", new Date(System.currentTimeMillis() - 3600000L)));
      Document update = (new Document("$set", new Document("lockedBy", this.serverId))).append("$currentDate", new Document("lockedAt", Boolean.valueOf(true)));
      Document updated = (Document)lockCollection.findOneAndUpdate((Bson)filter, (Bson)update, (new FindOneAndUpdateOptions()).returnDocument(ReturnDocument.AFTER));
      return (updated != null);
    } 
  }
  
  private void markDone() {
    MongoCollection<Document> lockCollection = DungeonRankingManager.getWeeklyLockCollection();
    String weekId = getWeekId();
    Document filter = new Document("_id", weekId);
    Document update = (new Document("$set", new Document("done", Boolean.valueOf(true)))).append("$currentDate", new Document("lockedAt", Boolean.valueOf(true)));
    lockCollection.updateOne((Bson)filter, (Bson)update);
  }
  
  private String getWeekId() {
    ZonedDateTime now = UniversalTimeUtils.nowZoned();
    int week = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    int year = now.get(IsoFields.WEEK_BASED_YEAR);
    return year + "-W" + week;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\task\DungeonRankingTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
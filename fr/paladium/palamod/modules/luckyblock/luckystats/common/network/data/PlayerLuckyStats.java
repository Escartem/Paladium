package fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data;

import com.influxdb.client.WriteApi;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.LuckyStatsError;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerLuckyStats implements IBufSerializable {
  private static final ExecutorService SINGLE_THREAD_EXECUTOR;
  
  private transient EntityPlayer player;
  
  static {
    SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor(r -> {
          Thread t = new Thread(r, "LuckyStats-AsyncWorker");
          t.setDaemon(true);
          return t;
        });
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  private Map<LuckyEvents, Long> stats = new HashMap<>();
  
  public Map<LuckyEvents, Long> getStats() {
    return this.stats;
  }
  
  private Map<LuckyType, List<Integer>> rewards = new HashMap<>();
  
  public Map<LuckyType, List<Integer>> getRewards() {
    return this.rewards;
  }
  
  private final Map<LuckyType, List<Instant>> rewardsTime = new HashMap<>();
  
  public Map<LuckyType, List<Instant>> getRewardsTime() {
    return this.rewardsTime;
  }
  
  public PlayerLuckyStats() {
    this(null);
  }
  
  public PlayerLuckyStats(EntityPlayer player) {
    this.player = player;
    this.stats = new HashMap<>();
    this.rewards = new HashMap<>();
    for (LuckyType type : LuckyType.values()) {
      this.rewards.put(type, Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) }));
      this.rewardsTime.put(type, Arrays.asList(new Instant[] { null, null, null, null }));
    } 
  }
  
  public void load(Consumer<PlayerLuckyStats> callback, Consumer<LuckyStatsError> error) {
    PlayerLuckyBlockProperties eep = PlayerLuckyBlockProperties.get(this.player);
    if (eep.isLoadingLuckyStats()) {
      error.accept(LuckyStatsError.LOADING);
      return;
    } 
    eep.setLoadingLuckyStats(true);
    if (PLuckyBlock.dbManager.getClient() == null) {
      error.accept(LuckyStatsError.DB_ERROR);
      eep.setLoadingLuckyStats(false);
      return;
    } 
    SINGLE_THREAD_EXECUTOR.execute(() -> {
          try {
            String uuid = FastUUID.toString((Entity)this.player);
            String query = String.format("from(bucket: \"%s\")\r\n  |> range(start: 0)\r\n  |> filter(fn: (r) => r[\"_field\"] == \"events\")\r\n  |> filter(fn: (r) => r[\"player\"] == \"%s\")", new Object[] { PLuckyBlock.dbManager.getBucket(), uuid });
            List<FluxTable> tables = PLuckyBlock.dbManager.getClient().getQueryApi().query(query, PLuckyBlock.dbManager.getOrg());
            this.stats.clear();
            for (FluxTable table : tables) {
              for (FluxRecord record : table.getRecords()) {
                try {
                  addStat(LuckyEvents.valueOf((String)record.getValue()), Long.valueOf(record.getTime().atZone(ZoneId.of("Europe/Paris")).toEpochSecond()));
                } catch (Exception e) {
                  System.err.println("[LuckyStats][Error] Player try to load strange event: " + (String)record.getValue());
                } 
              } 
            } 
            query = String.format("from(bucket: \"%s\")\r\n  |> range(start: 0)\r\n  |> filter(fn: (r) => r[\"_field\"] == \"rewards\")\r\n  |> filter(fn: (r) => r[\"player\"] == \"%s\")", new Object[] { PLuckyBlock.dbManager.getBucket(), uuid });
            tables = PLuckyBlock.dbManager.getClient().getQueryApi().query(query, PLuckyBlock.dbManager.getOrg());
            for (FluxTable table : tables) {
              for (FluxRecord record : table.getRecords()) {
                String[] args = ((String)record.getValue()).split(":");
                LuckyType type = LuckyType.valueOf(args[0]);
                int i = Integer.parseInt(args[1]);
                setReward(type, i, 1);
                setRewardTime(type, i, record.getTime());
              } 
            } 
            if (callback != null)
              callback.accept(this); 
            eep.setLoadingLuckyStats(false);
          } catch (Exception e) {
            System.err.println("[LuckyStats][Error] Cannot load player due an error: ");
            e.printStackTrace();
            error.accept(LuckyStatsError.DB_ERROR);
            eep.setLoadingLuckyStats(false);
          } 
        });
  }
  
  public void save(Consumer<PlayerLuckyStats> callback, Consumer<LuckyStatsError> error) {
    if (PLuckyBlock.dbManager.getClient() == null) {
      error.accept(LuckyStatsError.DB_ERROR);
      return;
    } 
    SINGLE_THREAD_EXECUTOR.execute(() -> {
          try {
            String uuid = FastUUID.toString((Entity)this.player);
            Point point = Point.measurement("stats").addTag("player", uuid);
            String query = String.format("from(bucket: \"%s\")\r\n  |> range(start: 0)\r\n  |> filter(fn: (r) => r[\"_field\"] == \"rewards\")\r\n  |> filter(fn: (r) => r[\"player\"] == \"%s\")", new Object[] { PLuckyBlock.dbManager.getBucket(), uuid });
            List<FluxTable> tables = PLuckyBlock.dbManager.getClient().getQueryApi().query(query, PLuckyBlock.dbManager.getOrg());
            List<String> rewards = new ArrayList<>();
            for (FluxTable table : tables) {
              for (FluxRecord record : table.getRecords())
                rewards.add((String)record.getValue()); 
            } 
            for (LuckyType type : LuckyType.values()) {
              for (int i = 0; i < 4; i++) {
                if (((Integer)((List<Integer>)this.rewards.get(type)).get(i)).intValue() == 1 && !rewards.contains(type.toString() + ":" + i))
                  point.addField("rewards", type.toString() + ":" + i); 
              } 
            } 
            query = String.format("from(bucket: \"%s\")\r\n  |> range(start: 0)\r\n  |> filter(fn: (r) => r[\"_field\"] == \"events\")\r\n  |> filter(fn: (r) => r[\"player\"] == \"%s\")", new Object[] { PLuckyBlock.dbManager.getBucket(), uuid });
            tables = PLuckyBlock.dbManager.getClient().getQueryApi().query(query, PLuckyBlock.dbManager.getOrg());
            List<LuckyEvents> events = new ArrayList<>();
            for (FluxTable table : tables) {
              for (FluxRecord record : table.getRecords()) {
                try {
                  events.add(LuckyEvents.valueOf((String)record.getValue()));
                } catch (Exception e) {
                  System.err.println("[LuckyStats][Error] Player try to load strange event: " + (String)record.getValue());
                } 
              } 
            } 
            for (LuckyEvents event : this.stats.keySet()) {
              if (!events.contains(event))
                point.addField("events", event.toString()); 
            } 
            if (point.hasFields())
              try (WriteApi writeApi = PLuckyBlock.dbManager.getClient().getWriteApi()) {
                writeApi.writePoint(PLuckyBlock.dbManager.getBucket(), PLuckyBlock.dbManager.getOrg(), point);
              }  
            if (callback != null)
              callback.accept(this); 
          } catch (Exception e) {
            error.accept(LuckyStatsError.DB_ERROR);
          } 
        });
  }
  
  public PlayerLuckyStats addStat(LuckyEvents event, Long instant) {
    if (!this.stats.containsKey(event))
      this.stats.put(event, instant); 
    return this;
  }
  
  public PlayerLuckyStats setReward(LuckyType type, int index, int state) {
    ((List<Integer>)this.rewards.get(type)).set(index, Integer.valueOf(state));
    return this;
  }
  
  public PlayerLuckyStats setRewardTime(LuckyType type, int index, Instant instant) {
    ((List<Instant>)this.rewardsTime.get(type)).set(index, instant);
    return this;
  }
  
  public void notifyPlayer(LuckyEvents event) {
    if (!(this.player instanceof EntityPlayerMP))
      return; 
    for (LuckyType type : LuckyType.values()) {
      if (LuckyType.disabledLuckyType.contains(type) || (event != null && !event.getTypes().contains(type)))
        continue; 
      long count = this.stats.keySet().stream().filter(e -> e.getTypes().contains(type)).count();
      long maxCount = Arrays.<LuckyEvents>stream(LuckyEvents.values()).filter(e -> e.getTypes().contains(type)).count();
      if (maxCount <= 0L || count <= 0L)
        continue; 
      float percentage = (float)count / (float)maxCount * 100.0F;
      int level = (int)Math.floor(percentage / 25.0D) - 1;
      if (level < 0)
        continue; 
      if (event != null) {
        float oldPercentage = (float)(count - 1L) / (float)maxCount * 100.0F;
        int oldLevel = (int)Math.floor(oldPercentage / 25.0D) - 1;
        if (oldLevel >= level)
          continue; 
      } 
      if (((Integer)((List<Integer>)this.rewards.get(type)).get(level)).intValue() == 0) {
        int mappedPercentage = (level + 1) * 25;
        (new Notification(Notification.NotificationType.SUCCESS, "Vous pouvez récupérer le palier " + mappedPercentage + "% des " + type.getText(), "luckystats")).send((EntityPlayerMP)this.player);
      } 
      continue;
    } 
  }
  
  public void read(ByteBuf buf) {
    this.stats = new HashMap<>();
    int size = buf.readInt();
    int i;
    for (i = 0; i < size; i++)
      this.stats.put(LuckyEvents.values()[buf.readInt()], Long.valueOf(buf.readLong())); 
    this.rewards = new HashMap<>();
    size = buf.readInt();
    for (i = 0; i < size; i++) {
      LuckyType type = LuckyType.values()[buf.readInt()];
      List<Integer> list = new ArrayList<>();
      int listSize = buf.readInt();
      for (int k = 0; k < listSize; k++)
        list.add(Integer.valueOf(buf.readInt())); 
      this.rewards.put(type, list);
    } 
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.stats.size());
    for (Map.Entry<LuckyEvents, Long> entry : this.stats.entrySet()) {
      buf.writeInt(((LuckyEvents)entry.getKey()).ordinal());
      buf.writeLong(((Long)entry.getValue()).longValue());
    } 
    buf.writeInt(this.rewards.size());
    for (Map.Entry<LuckyType, List<Integer>> entry : this.rewards.entrySet()) {
      buf.writeInt(((LuckyType)entry.getKey()).ordinal());
      buf.writeInt(((List)entry.getValue()).size());
      for (Iterator<Integer> iterator = ((List)entry.getValue()).iterator(); iterator.hasNext(); ) {
        int i = ((Integer)iterator.next()).intValue();
        buf.writeInt(i);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\common\network\data\PlayerLuckyStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
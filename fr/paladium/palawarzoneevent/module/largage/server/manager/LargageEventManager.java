package fr.paladium.palawarzoneevent.module.largage.server.manager;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawarzoneevent.module.largage.LargageModule;
import fr.paladium.palawarzoneevent.module.largage.common.action.client.LargageClientAction;
import fr.paladium.palawarzoneevent.module.largage.common.entity.EntityLargage;
import fr.paladium.palawarzoneevent.module.largage.server.config.LargageConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class LargageEventManager {
  private static LargageEventManager INSTANCE;
  
  private static final Random RANDOM = new Random();
  
  private UUID eventUUID = null;
  
  public UUID getEventUUID() {
    return this.eventUUID;
  }
  
  private boolean running = false;
  
  public boolean isRunning() {
    return this.running;
  }
  
  private LargageConfig.SpawnPoint runningSpawnPoint = null;
  
  public LargageConfig.SpawnPoint getRunningSpawnPoint() {
    return this.runningSpawnPoint;
  }
  
  private final List<UUID> entities = new ArrayList<>();
  
  public List<UUID> getEntities() {
    return this.entities;
  }
  
  public static LargageEventManager inst() {
    if (INSTANCE == null)
      INSTANCE = new LargageEventManager(); 
    return INSTANCE;
  }
  
  public List<ItemStack> rollRewards(List<LargageConfig.ItemReward> possibleRewards, int minItemDrop, int maxItemDrop) {
    if (possibleRewards.isEmpty())
      return new ArrayList<>(); 
    List<LargageConfig.ItemReward> copyRewardList = new ArrayList<>(possibleRewards);
    Collections.shuffle(copyRewardList, RANDOM);
    List<ItemStack> items = new ArrayList<>();
    int maxWeight = possibleRewards.stream().mapToInt(LargageConfig.ItemReward::getWeight).sum();
    int numOfRoll = RANDOM.nextInt(maxItemDrop + 1 - minItemDrop) + minItemDrop;
    for (int i = 0; i < numOfRoll; i++) {
      int weight = RANDOM.nextInt(maxWeight) + 1;
      int sumRolledItemWeight = 0;
      for (LargageConfig.ItemReward itemReward : copyRewardList) {
        sumRolledItemWeight += itemReward.getWeight();
        if (weight <= sumRolledItemWeight) {
          ItemStack itemStack = ItemUtils.parse(itemReward.getItem());
          if (itemStack == null) {
            LargageModule.logger.error("Invalid item format: " + itemReward.getItem(), new Object[0]);
            break;
          } 
          items.add(itemStack);
          break;
        } 
      } 
    } 
    return items;
  }
  
  public LargageConfig.SpawnPoint startEvent() {
    return startEvent(MinecraftServer.func_71276_C().func_130014_f_(), null);
  }
  
  public LargageConfig.SpawnPoint startEvent(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    return startEvent(world, null);
  }
  
  public LargageConfig.SpawnPoint startEvent(@NonNull final World world, final UUID uuid) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    this.entities.clear();
    final LargageConfig.SpawnPoint spawnPoint = LargageModule.getServer().getConfig().getRandomSpawnPoint();
    if (spawnPoint == null)
      return null; 
    final double x = spawnPoint.getX();
    final double y = LargageModule.getServer().getConfig().getStartingY();
    final double z = spawnPoint.getZ();
    int minLargageCount = LargageModule.getServer().getConfig().getMinLargageCount();
    int maxLargageCount = LargageModule.getServer().getConfig().getMaxLargageCount();
    int numberOfLargage = RANDOM.nextInt(maxLargageCount + 1 - minLargageCount) + minLargageCount;
    List<LargageConfig.SpawnPoint> spawnPointsAround = LargageModule.getServer().getConfig().getSpawnPointsAround(spawnPoint, 100.0D);
    if (spawnPointsAround.isEmpty()) {
      LargageModule.logger.error("Aucun point de largage trouvé autour du point de largage " + spawnPoint + " !", new Object[0]);
      return null;
    } 
    Collections.shuffle(spawnPointsAround, RANDOM);
    int iteration = 0;
    final List<LargageConfig.SpawnPoint> randomSpawnPoints = new ArrayList<>();
    while (randomSpawnPoints.size() < numberOfLargage && iteration < 100) {
      LargageConfig.SpawnPoint point = spawnPointsAround.get(RANDOM.nextInt(spawnPointsAround.size()));
      if (!randomSpawnPoints.contains(point))
        randomSpawnPoints.add(point); 
      iteration++;
    } 
    if (randomSpawnPoints.size() < minLargageCount) {
      LargageModule.logger.error("Impossible de trouver au minimum " + minLargageCount + " points de largage autour du point " + spawnPoint + " !", new Object[0]);
      return null;
    } 
    if (uuid != null) {
      ChronosManager.getInstance().startEventAsync(UUIDUtils.toString(uuid), new ChronosCallback<Planned>() {
            public void onSuccess(Planned planned) {
              randomSpawnPoints.forEach(sp -> {
                    EntityLargage entity = new EntityLargage(world, sp.getX(), y + LargageEventManager.RANDOM.nextInt(21), sp.getZ());
                    Chunk chunk = world.func_72938_d((int)x, (int)z);
                    if (!chunk.field_76636_d)
                      world.func_72863_F().func_73158_c((int)x >> 4, (int)z >> 4); 
                    world.func_72838_d((Entity)entity);
                    LargageEventManager.this.entities.add(entity.func_110124_au());
                  });
              LargageEventManager.this.eventUUID = uuid;
              LargageEventManager.this.running = true;
              LargageEventManager.this.runningSpawnPoint = spawnPoint;
              LargageEventManager.this.sendLargageInfo();
            }
            
            public void onFail(Planned planned, Throwable throwable) {
              LargageModule.logger.error("Impossible de démarré l'évènement de largage " + UUIDUtils.toString(LargageEventManager.this.eventUUID) + " !", new Object[] { throwable });
              MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rUne erreur est survenue avec l'évènement largage, celui-ci est donc annulé."));
            }
          });
      return spawnPoint;
    } 
    randomSpawnPoints.forEach(sp -> {
          EntityLargage entity = new EntityLargage(world, sp.getX(), y + RANDOM.nextInt(21), sp.getZ());
          Chunk chunk = world.func_72938_d((int)x, (int)z);
          if (!chunk.field_76636_d)
            world.func_72863_F().func_73158_c((int)x >> 4, (int)z >> 4); 
          world.func_72838_d((Entity)entity);
          this.entities.add(entity.func_110124_au());
        });
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rLe largage entame sa descente dans la Warzone. Bonne chance !"));
    this.running = true;
    this.runningSpawnPoint = spawnPoint;
    sendLargageInfo();
    return spawnPoint;
  }
  
  public void stopEvent() {
    if (this.eventUUID != null) {
      ChronosManager.getInstance().stopEventAsync(UUIDUtils.toString(this.eventUUID), new ChronosCallback<Planned>() {
            public void onSuccess(Planned planned) {
              MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rLes largages ont été détruit !"));
            }
            
            public void onFail(Planned planned, Throwable throwable) {
              LargageModule.logger.error("Impossible d'arrêter l'évènement de largage " + UUIDUtils.toString(LargageEventManager.this.eventUUID) + " !", new Object[] { throwable });
              MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rUne erreur est survenue lors de la fin de l'évement largage."));
            }
          });
      this.eventUUID = null;
      this.running = false;
      this.runningSpawnPoint = null;
      this.entities.clear();
      sendLargageInfo();
      return;
    } 
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rLes largages ont été détruit !"));
    this.eventUUID = null;
    this.running = false;
    this.runningSpawnPoint = null;
    this.entities.clear();
    sendLargageInfo();
  }
  
  public void onLargageDeath(@NonNull UUID uniqueID) {
    if (uniqueID == null)
      throw new NullPointerException("uniqueID is marked non-null but is null"); 
    this.entities.remove(uniqueID);
    if (this.entities.isEmpty() && isEventRunning())
      stopEvent(); 
  }
  
  public boolean isEventRunning() {
    return this.running;
  }
  
  public void sendLargageInfo() {
    double copyX = isEventRunning() ? this.runningSpawnPoint.getX() : 0.0D;
    double copyY = isEventRunning() ? 80.0D : 0.0D;
    double copyZ = isEventRunning() ? this.runningSpawnPoint.getZ() : 0.0D;
    LargageClientAction.updateScoreboardLocation(copyX, copyY, copyZ).execute(PlayerSelector.ALL());
  }
  
  public void spawnManualLargage(World world, double x, double z) {
    spawnManualLargage(world, x, 200.0D, z, 0, 0, null);
  }
  
  public void spawnManualLargage(World world, double x, double z, int maxItemDrop) {
    spawnManualLargage(world, x, 200.0D, z, 0, maxItemDrop, null);
  }
  
  public void spawnManualLargage(World world, double x, double z, int minItemDrop, int maxItemDrop) {
    spawnManualLargage(world, x, 200.0D, z, minItemDrop, maxItemDrop, null);
  }
  
  public void spawnManualLargage(World world, double x, double z, int maxItemDrop, List<LargageConfig.ItemReward> rewards) {
    spawnManualLargage(world, x, 200.0D, z, 0, maxItemDrop, rewards);
  }
  
  public void spawnManualLargage(World world, double x, double z, int minItemDrop, int maxItemDrop, List<LargageConfig.ItemReward> rewards) {
    spawnManualLargage(world, x, 200.0D, z, minItemDrop, maxItemDrop, rewards);
  }
  
  public void spawnManualLargage(World world, double x, double y, double z) {
    spawnManualLargage(world, x, y, z, 0, 0, null);
  }
  
  public void spawnManualLargage(World world, double x, double y, double z, int maxItemDrop) {
    spawnManualLargage(world, x, y, z, 0, maxItemDrop, null);
  }
  
  public void spawnManualLargage(World world, double x, double y, double z, int maxItemDrop, List<LargageConfig.ItemReward> rewards) {
    spawnManualLargage(world, x, y, z, 0, maxItemDrop, rewards);
  }
  
  public void spawnManualLargage(World world, double x, double y, double z, int minItemDrop, int maxItemDrop) {
    spawnManualLargage(world, x, y, z, minItemDrop, maxItemDrop, null);
  }
  
  public void spawnManualLargage(World world, double x, double y, double z, int minItemDrop, int maxItemDrop, List<LargageConfig.ItemReward> rewards) {
    EntityLargage largage = new EntityLargage(world, x, x, z);
    largage.setEventLinked(false);
    largage.setRewards(rewards);
    largage.setMinNbRewards(minItemDrop);
    largage.setMaxNbRewards(maxItemDrop);
    largage.func_70107_b(x, y, z);
    Chunk chunk = world.func_72938_d((int)x, (int)z);
    if (!chunk.field_76636_d)
      world.func_72863_F().func_73158_c((int)x >> 4, (int)z >> 4); 
    world.func_72838_d((Entity)largage);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\manager\LargageEventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palarpg.module.dungeon.common.world.room;

import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.google.gson.JsonElement;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.client.ui.choice.UIDungeonChoice;
import fr.paladium.palarpg.module.dungeon.client.ui.transition.UIDungeonTransitionOverlay;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.network.skip.BBPacketRPGDungeonSkip;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelLootsConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import fr.paladium.palarpg.module.dungeon.server.utils.EntityTrackerUtils;
import fr.paladium.palarpg.module.profile.server.manager.RPGLevelManager;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class DungeonRoom {
  private static final int ROOM_HEIGHT = 10;
  
  private final int index;
  
  private final boolean premium;
  
  private final DungeonWorld world;
  
  private final DungeonLevelConfig level;
  
  private final DungeonRoomConfig config;
  
  private final DungeonLevelLootsConfig loots;
  
  private final List<DungeonRoomBehavior> behaviors;
  
  private final Set<String> startingPlayers;
  
  private final Set<String> finishingPlayers;
  
  private final List<TileEntity> tileEntities;
  
  private int x;
  
  private int z;
  
  private Schematic schematic;
  
  private List<BlockLocation> startLocations;
  
  private List<BlockLocation> finishLocations;
  
  private List<BlockLocation> joinPowerLocations;
  
  private List<BlockLocation> activePowerLocations;
  
  private List<DoubleLocation> spawnLocations;
  
  private Map.Entry<BlockLocation, ForgeDirection> chestLocation;
  
  private boolean debug;
  
  private boolean active;
  
  private boolean finished;
  
  private long startTime;
  
  private boolean wasSkippable;
  
  public int getIndex() {
    return this.index;
  }
  
  public boolean isPremium() {
    return this.premium;
  }
  
  public DungeonWorld getWorld() {
    return this.world;
  }
  
  public DungeonLevelConfig getLevel() {
    return this.level;
  }
  
  public DungeonRoomConfig getConfig() {
    return this.config;
  }
  
  public DungeonLevelLootsConfig getLoots() {
    return this.loots;
  }
  
  public List<DungeonRoomBehavior> getBehaviors() {
    return this.behaviors;
  }
  
  public Set<String> getStartingPlayers() {
    return this.startingPlayers;
  }
  
  public Set<String> getFinishingPlayers() {
    return this.finishingPlayers;
  }
  
  public List<TileEntity> getTileEntities() {
    return this.tileEntities;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public Schematic getSchematic() {
    return this.schematic;
  }
  
  public List<BlockLocation> getStartLocations() {
    return this.startLocations;
  }
  
  public List<BlockLocation> getFinishLocations() {
    return this.finishLocations;
  }
  
  public List<BlockLocation> getJoinPowerLocations() {
    return this.joinPowerLocations;
  }
  
  public List<BlockLocation> getActivePowerLocations() {
    return this.activePowerLocations;
  }
  
  public List<DoubleLocation> getSpawnLocations() {
    return this.spawnLocations;
  }
  
  public Map.Entry<BlockLocation, ForgeDirection> getChestLocation() {
    return this.chestLocation;
  }
  
  public boolean isDebug() {
    return this.debug;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  public boolean isFinished() {
    return this.finished;
  }
  
  public long getStartTime() {
    return this.startTime;
  }
  
  public boolean isWasSkippable() {
    return this.wasSkippable;
  }
  
  private DungeonRoom(int index, boolean premium, @NonNull DungeonWorld world, @NonNull DungeonRoomConfig config) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    this.index = index;
    this.premium = premium;
    this.world = world;
    this.config = config;
    this.level = this.world.getDungeon().getLevelOrInfinite(world.getLevel()).get();
    this.loots = (DungeonLevelLootsConfig)this.level.getLoots().get(DungeonLevelConfig.DungeonLevelRoomType.from(config.getType()));
    this.behaviors = new ArrayList<>();
    this.tileEntities = new ArrayList<>();
    this.startingPlayers = new HashSet<>();
    this.finishingPlayers = new HashSet<>();
    if (config.getBehaviors() != null && !config.getBehaviors().isEmpty())
      for (String behavior : config.getBehaviors()) {
        Optional<Class<? extends DungeonRoomBehavior>> optional = DungeonRoomBehavior.getBehavior(behavior);
        if (!optional.isPresent())
          throw new IllegalArgumentException("Unable to find behavior " + behavior + " for room " + config.getId() + "."); 
        try {
          this.behaviors.add(((Class<DungeonRoomBehavior>)optional.get()).getConstructor(new Class[] { DungeonRoom.class }).newInstance(new Object[] { this }));
        } catch (Exception e) {
          throw new IllegalArgumentException("Unable to create behavior " + behavior + " for room " + config.getId() + ".", e);
        } 
      }  
    Optional<Class<? extends DungeonRoomBehavior>> optionalBehavior = DungeonRoomBehavior.getBehavior(this.config.getType());
    optionalBehavior.ifPresent(behavior -> {
          try {
            this.behaviors.add(behavior.getConstructor(new Class[] { DungeonRoom.class }).newInstance(new Object[] { this }));
          } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create behavior " + this.config.getType() + " for room " + config.getId() + ".", e);
          } 
        });
    for (DungeonRoomBehavior behavior : this.behaviors) {
      MinecraftForge.EVENT_BUS.register(behavior);
      FMLCommonHandler.instance().bus().register(behavior);
    } 
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  @NonNull
  public static DungeonRoom create(int index, boolean premium, @NonNull DungeonWorld world, @NonNull DungeonRoomConfig config) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    return new DungeonRoom(index, premium, world, config);
  }
  
  @NonNull
  public CompletableFuture<DungeonRoom> paste(int x, int z) {
    this.x = x;
    this.z = z;
    this.chestLocation = null;
    this.startLocations = new ArrayList<>();
    this.finishLocations = new ArrayList<>();
    this.joinPowerLocations = new ArrayList<>();
    this.activePowerLocations = new ArrayList<>();
    this.spawnLocations = new ArrayList<>();
    CompletableFuture<DungeonRoom> future = new CompletableFuture<>();
    (new Thread(() -> {
          try {
            this.schematic = SchematicUtils.loadSchematic(this.config.getSchematic(), SchematicUtils.SchematicVersion.V2);
            if (this.schematic == null)
              this.schematic = SchematicUtils.loadSchematic(this.config.getSchematic(), SchematicUtils.SchematicVersion.V1); 
          } catch (Exception e) {
            this.schematic = SchematicUtils.loadSchematic(this.config.getSchematic(), SchematicUtils.SchematicVersion.V1);
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        }"DungeonRoom-" + this.config.getId() + "/PrePaste")).start();
    return future;
  }
  
  @NonNull
  public DungeonRoom cut() {
    if (this.x != 0 && this.z != 0 && this.schematic != null && this.world.getWorld() != null) {
      BlockPos min = this.schematic.getMinPoint();
      BlockPos max = this.schematic.getMaxPoint();
      for (int x = min.getX(); x <= max.getX(); x++) {
        for (int y = min.getY(); y <= max.getY(); y++) {
          for (int z = min.getZ(); z <= max.getZ(); z++)
            setBlockToAir(x, y, z); 
        } 
      } 
    } 
    return this;
  }
  
  @NonNull
  public DungeonRoom teleport(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.spawnLocations.isEmpty())
      return this; 
    DoubleLocation spawnLocation = this.spawnLocations.get(0);
    spawnLocation.teleportServer(player);
    EntityTrackerUtils.update(player);
    return this;
  }
  
  @NonNull
  public DungeonRoom join() {
    this.active = true;
    this.startTime = System.currentTimeMillis();
    this.world.setActiveRoom(this);
    int index = 0;
    for (EntityPlayer player : this.world.getOnlinePlayers()) {
      ((DoubleLocation)this.spawnLocations.get(index % this.spawnLocations.size())).teleportServer(player);
      (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send((EntityPlayerMP)player);
      index++;
    } 
    for (EntityPlayer player : this.world.getOnlinePlayers())
      EntityTrackerUtils.update(player); 
    if (!this.tileEntities.isEmpty()) {
      List<Packet> packets = new ArrayList<>();
      for (TileEntity tileEntity : this.tileEntities) {
        if (tileEntity != null)
          packets.add(tileEntity.func_145844_m()); 
      } 
      if (!packets.isEmpty())
        for (EntityPlayer player : this.world.getOnlinePlayers()) {
          if (!(player instanceof EntityPlayerMP))
            continue; 
          EntityPlayerMP playerMP = (EntityPlayerMP)player;
          for (Packet packet : packets)
            playerMP.field_71135_a.func_147359_a(packet); 
        }  
    } 
    try {
      this.behaviors.forEach(DungeonRoomBehavior::onJoin);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    this.joinPowerLocations.forEach(blockLocation -> {
          blockLocation.setBlock(Blocks.field_150451_bX);
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        });
    this.activePowerLocations.forEach(blockLocation -> blockLocation.setBlock(Blocks.field_150451_bX));
    return this;
  }
  
  @NonNull
  public DungeonRoom leave() {
    this.behaviors.forEach(DungeonRoomBehavior::onLeave);
    this.activePowerLocations.forEach(BlockLocation::setBlockToAir);
    this.debug = false;
    this.active = false;
    return this;
  }
  
  @NonNull
  public DungeonRoom reset() {
    this.behaviors.forEach(DungeonRoomBehavior::onReset);
    leave();
    join();
    return this;
  }
  
  @NonNull
  public DungeonRoom remove() {
    MinecraftForge.EVENT_BUS.unregister(this);
    FMLCommonHandler.instance().bus().unregister(this);
    for (DungeonRoomBehavior behavior : getBehaviors()) {
      MinecraftForge.EVENT_BUS.unregister(behavior);
      FMLCommonHandler.instance().bus().unregister(behavior);
    } 
    cut();
    this.behaviors.forEach(DungeonRoomBehavior::onRemove);
    this.schematic = null;
    this.behaviors.clear();
    this.tileEntities.clear();
    return this;
  }
  
  @NonNull
  public DungeonRoom setFinished(boolean finished) {
    if (this.finished == finished || MinecraftForge.EVENT_BUS.post(new DungeonRoomFinishEvent.Pre(this, finished)))
      return this; 
    this.finished = finished;
    if (this.finished) {
      boolean hasLoot = hasLoot();
      MolangParser parser = this.world.getParser();
      if (hasLoot && this.chestLocation != null && this.chestLocation.getKey() != null && ((BlockLocation)this.chestLocation.getKey()).getTileEntity() instanceof TileEntityDungeonChest) {
        BlockLocation location = this.chestLocation.getKey();
        TileEntityDungeonChest.DungeonChestRarity rarity = ((TileEntityDungeonChest.DungeonChestRarity[])this.loots.getChests().keySet().toArray((T[])new TileEntityDungeonChest.DungeonChestRarity[0]))[0];
        if (this.premium) {
          rarity = TileEntityDungeonChest.DungeonChestRarity.LEGENDARY;
        } else {
          try {
            double totalWeight = 0.0D;
            for (Map.Entry<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig> entry : (Iterable<Map.Entry<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig>>)this.loots.getChests().entrySet())
              totalWeight += parser.parseJson(((DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig)entry.getValue()).getWeight()).get(); 
            double currentWeight = 0.0D;
            double randomWeight = (this.world.getWorld()).field_73012_v.nextDouble() * totalWeight;
            for (Map.Entry<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig> entry : (Iterable<Map.Entry<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig>>)this.loots.getChests().entrySet()) {
              currentWeight += parser.parseJson(((DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig)entry.getValue()).getWeight()).get();
              if (currentWeight >= randomWeight) {
                rarity = entry.getKey();
                break;
              } 
            } 
          } catch (Exception e) {
            e.printStackTrace();
          } 
        } 
        TileEntityDungeonChest chest = (TileEntityDungeonChest)location.getTileEntity();
        chest.setRarity(rarity);
        for (EntityPlayer player : this.world.getOnlinePlayers()) {
          List<RPGDungeonPlayerData.RPGDungeonItem> rewardsList = new ArrayList<>();
          for (Map.Entry<String, JsonElement> lootCountEntry : (Iterable<Map.Entry<String, JsonElement>>)this.loots.getLootCount().entrySet()) {
            String type = lootCountEntry.getKey();
            if (type == null || type.isEmpty())
              continue; 
            boolean defaultType = "default".equalsIgnoreCase(type);
            int lootCount = 5;
            try {
              lootCount = Double.valueOf(parser.parseJson(lootCountEntry.getValue()).get()).intValue();
            } catch (MolangException e) {
              e.printStackTrace();
            } 
            if (lootCount <= 0)
              continue; 
            Optional<JsonElement> optionalLootProbability = this.loots.getLootProbability(type);
            if (!optionalLootProbability.isPresent())
              continue; 
            try {
              if ((this.world.getWorld()).field_73012_v.nextDouble() > parser.parseJson(optionalLootProbability.get()).get())
                continue; 
            } catch (Exception e) {
              e.printStackTrace();
              continue;
            } 
            double bonusLoot = 0.0D;
            if (defaultType)
              try {
                bonusLoot = parser.parseJson(((DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig)this.loots.getChests().get(rarity)).getBonus()).get();
              } catch (MolangException e) {
                e.printStackTrace();
              }  
            if (defaultType && this.premium && this.loots.getPremium() != null)
              try {
                bonusLoot += parser.parseJson(this.loots.getPremium()).get();
              } catch (MolangException e) {
                e.printStackTrace();
              }  
            double minWeight = Double.MAX_VALUE;
            List<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig> loots = this.loots.getLoots(type);
            if (loots.isEmpty())
              continue; 
            Collections.shuffle(loots);
            Map<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig, Double> weights = new HashMap<>();
            for (DungeonLevelLootsConfig.DungeonLevelLootsElementConfig loot : loots) {
              try {
                double weight = parser.parseJson(loot.getWeight()).get();
                weights.put(loot, Double.valueOf(weight));
                if (weight < minWeight)
                  minWeight = weight; 
              } catch (MolangException e) {
                e.printStackTrace();
              } 
            } 
            if (weights.isEmpty())
              continue; 
            double playerBonusLoot = Math.max(1.0D, bonusLoot + StatsManager.BONUS_LOOT.getBonusLoot((EntityLivingBase)player, type));
            for (int i = 0; i < lootCount; i++) {
              double totalWeight = 0.0D;
              Map<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig, Double> adjustedWeights = new HashMap<>();
              for (DungeonLevelLootsConfig.DungeonLevelLootsElementConfig loot : weights.keySet()) {
                double weight = ((Double)weights.get(loot)).doubleValue();
                double bonus = minWeight / weight;
                double adjustedWeight = weight * (1.0D + (playerBonusLoot - 1.0D) * bonus);
                totalWeight += adjustedWeight;
                adjustedWeights.put(loot, Double.valueOf(adjustedWeight));
              } 
              double currentWeight = 0.0D;
              double randomWeight = (this.world.getWorld()).field_73012_v.nextDouble() * totalWeight;
              for (Map.Entry<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig, Double> entry : adjustedWeights.entrySet()) {
                currentWeight += ((Double)entry.getValue()).doubleValue();
                if (currentWeight >= randomWeight) {
                  RPGDungeonPlayerData.RPGDungeonItem reward = null;
                  try {
                    reward = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), type, ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).create(parser), ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).getRarity());
                  } catch (Exception e) {
                    reward = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), type, ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).create(), ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).getRarity());
                    continue;
                  } 
                  if (reward != null) {
                    reward.getItem().func_77973_b().func_77622_d(reward.getItem(), this.world.getWorld(), player);
                    rewardsList.add(reward);
                  } 
                  break;
                } 
              } 
            } 
          } 
          chest.setRewards(UUIDUtils.toString((Entity)player), rewardsList.<RPGDungeonPlayerData.RPGDungeonItem>toArray(new RPGDungeonPlayerData.RPGDungeonItem[0]));
        } 
        chest.func_70296_d();
        location.getWorld().func_147471_g(location.getX(), location.getY(), location.getZ());
      } 
      for (EntityPlayer player : this.world.getOnlinePlayers()) {
        if (this.loots != null && this.loots.getExperience() != null && !this.loots.getExperience().isJsonNull())
          try {
            double experience = parser.parseJson(this.loots.getExperience()).get();
            if (experience > 0.0D) {
              RPGLevelManager.addExperience((EntityLivingBase)player, experience);
              this.world.getPlayer(UUIDUtils.toString((Entity)player)).ifPresent(dungeonPlayer -> dungeonPlayer.addExperience(experience));
            } 
          } catch (MolangException e) {
            throw new RuntimeException("Failed to parse experience", e);
          }  
        (new SCPacketRPGPlaySound("sounds/dungeon/loot/spawn.ogg", 5.0F)).send((EntityPlayerMP)player);
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §6✔ §6§lVous §6§lavez §6§lterminé §6§lcette §6§lsalle."));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        if (hasLoot)
          player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Le coffre de récompense est déverouillé.")); 
        player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7La portail de la salle suivante est ouvert."));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
      } 
    } 
    MinecraftForge.EVENT_BUS.post(new DungeonRoomFinishEvent.Post(this, finished));
    return this;
  }
  
  @NonNull
  public DungeonRoom next() {
    int index = this.index;
    if (index == this.world.getGenerator().getRoomCount() - 1) {
      try {
        this.world.finish(true);
      } catch (Exception e) {
        this.world.remove();
        e.printStackTrace();
      } 
    } else if (index + 1 < this.world.getRooms().size()) {
      DungeonRoom nextRoom = this.world.getRooms().get(index + 1);
      this.world.getOnlinePlayers().forEach(p -> ZUIServer.open(UIDungeonTransitionOverlay.class, (EntityPlayerMP)p));
      (new Thread(() -> {
            try {
              Thread.sleep(650L);
            } catch (InterruptedException interruptedException) {}
            leave();
            nextRoom.join();
          }"DungeonRoom-" + this.config.getId() + "/Next-" + nextRoom.config.getId())).start();
    } else {
      this.world.getOnlinePlayers().forEach(p -> ZUIServer.open(UIDungeonChoice.class, true, (EntityPlayerMP)p));
    } 
    return this;
  }
  
  public boolean inArea(double x, double y, double z) {
    return (x >= this.x && x <= (this.x + this.config.getSize().getX() * 25) && y >= 0.0D && y <= 256.0D && z >= this.z && z <= (this.z + this.config.getSize().getZ() * 25));
  }
  
  public boolean hasLoot() {
    return (this.loots != null && !this.loots.getLoots().isEmpty());
  }
  
  public boolean isSkippable() {
    return (this.active && this.config.getType() != DungeonRoomConfig.DungeonRoomType.MINIBOSS && this.config.getType() != DungeonRoomConfig.DungeonRoomType.BOSS && (this.startTime + 600000L <= System.currentTimeMillis() || ForgeEnv.isIDE()));
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    List<EntityPlayer> players = this.world.getAlivePlayers();
    if (this.debug) {
      DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Active: " + isActive(), new Object[0]);
      DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Players: " + players.size(), new Object[0]);
    } 
    if (players.isEmpty() || !isActive())
      return; 
    boolean skippable = isSkippable();
    if (skippable && !this.wasSkippable)
      this.world.getLeader().getOnlinePlayer().ifPresent(player -> (new BBPacketRPGDungeonSkip(this.config.getId())).send((EntityPlayerMP)player)); 
    this.wasSkippable = skippable;
    if (this.debug)
      DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Skippable: " + skippable, new Object[0]); 
    label78: for (EntityPlayer player : players) {
      String uuid = UUIDUtils.toString((Entity)player);
      int playerX = MathHelper.func_76128_c(player.field_70165_t);
      int playerY = MathHelper.func_76128_c(player.field_70163_u);
      int playerZ = MathHelper.func_76128_c(player.field_70161_v);
      if (this.debug)
        DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Checking player " + player.func_70005_c_() + " at (" + playerX + ", " + playerY + ", " + playerZ + ")", new Object[0]); 
      boolean foundStart = false;
      for (BlockLocation startLocation : this.startLocations) {
        if (this.debug)
          DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Comparing with start location at (" + startLocation.getX() + ", " + startLocation.getY() + ", " + startLocation.getZ() + ")", new Object[0]); 
        if (playerX == startLocation.getX() && playerY == startLocation.getY() && playerZ == startLocation.getZ()) {
          if (this.debug)
            DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Player " + player.func_70005_c_() + " is at start location (" + this.startingPlayers.contains(uuid) + ")", new Object[0]); 
          foundStart = true;
          if (!this.startingPlayers.contains(uuid)) {
            this.startingPlayers.add(uuid);
            if (!this.world.isLeader(uuid)) {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg")).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§laccéder §6§la §6§lcette §6§lsalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §cSeul §cle §ccréateur §cdu §cdonjon §cpeut §cchanger §cde §csalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              break;
            } 
            if (this.index == 0) {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg")).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§laccéder §6§la §6§lcette §6§lsalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cêtes §cdans §cla §cpremière §csalle §cdu §cdonjon."));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              break;
            } 
            if (!isFinished()) {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg")).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§laccéder §6§la §6§lcette §6§lsalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cdevez §cterminer §cla §csalle §cavant §cde §cpouvoir §crevenir §cen §carrière."));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              break;
            } 
            DungeonRoom previousRoom = this.world.getRooms().get(this.index - 1);
            this.world.getOnlinePlayers().forEach(p -> ZUIServer.open(UIDungeonTransitionOverlay.class, (EntityPlayerMP)p));
            (new Thread(() -> {
                  try {
                    Thread.sleep(650L);
                  } catch (InterruptedException interruptedException) {}
                  leave();
                  previousRoom.join();
                }"DungeonRoom-" + this.config.getId() + "/Previous-" + previousRoom.config.getId())).start();
            break label78;
          } 
        } 
      } 
      if (!foundStart)
        this.startingPlayers.remove(uuid); 
      boolean foundFinish = false;
      for (BlockLocation finishLocation : this.finishLocations) {
        if (this.debug)
          DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Comparing with finish location at (" + finishLocation.getX() + ", " + finishLocation.getY() + ", " + finishLocation.getZ() + ")", new Object[0]); 
        if (playerX == finishLocation.getX() && playerY == finishLocation.getY() && playerZ == finishLocation.getZ()) {
          if (this.debug)
            DungeonModule.logger.debug("[" + this.world.getUniqueId() + "][" + this.config.getId() + "] Player " + player.func_70005_c_() + " is at finish location (" + this.finishingPlayers.contains(uuid) + ")", new Object[0]); 
          foundFinish = true;
          if (!this.finishingPlayers.contains(uuid)) {
            this.finishingPlayers.add(uuid);
            if (!this.world.isLeader(uuid)) {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg")).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§laccéder §6§la §6§lcette §6§lsalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §cSeul §cle §ccréateur §cdu §cdonjon §cpeut §cchanger §cde §csalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              break;
            } 
            if (!isFinished()) {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg")).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§laccéder §6§la §6§lcette §6§lsalle."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cdevez §cterminer §cla §csalle §cavant §cde §cpouvoir §cdébloquer §cla §csalle §csuivante."));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              break;
            } 
            next();
            break label78;
          } 
        } 
      } 
      if (!foundFinish)
        this.finishingPlayers.remove(uuid); 
    } 
  }
  
  private void setBlockToAir(int x, int y, int z) {
    if (y < 0 || y >= 256)
      return; 
    Chunk chunk = this.world.getWorld().func_72938_d(x, z);
    Block block1 = chunk.func_150810_a(x & 0xF, y, z & 0xF);
    if (block1 == Blocks.field_150350_a)
      return; 
    int cx = x & 0xF;
    int cy = y;
    int cz = z & 0xF;
    int i1 = cz << 4 | cx;
    if (y >= chunk.field_76638_b[i1] - 1)
      chunk.field_76638_b[i1] = -999; 
    ExtendedBlockStorage extendedblockstorage = chunk.func_76587_i()[cy >> 4];
    if (extendedblockstorage == null)
      return; 
    int l1 = chunk.field_76635_g * 16 + cx;
    int i2 = chunk.field_76647_h * 16 + cz;
    int k1 = chunk.func_76628_c(cx, cy, cz);
    extendedblockstorage.func_150818_a(cx, cy & 0xF, cz, Blocks.field_150350_a);
    extendedblockstorage.func_76654_b(cx, cy & 0xF, cz, 0);
    TileEntity te = chunk.getTileEntityUnsafe(cx & 0xF, cy, cz & 0xF);
    if (te != null && te.shouldRefresh(block1, chunk.func_150810_a(cx & 0xF, cy, cz & 0xF), k1, chunk.func_76628_c(cx & 0xF, cy, cz & 0xF), this.world.getWorld(), l1, cy, i2))
      chunk.func_150805_f(cx & 0xF, cy, cz & 0xF); 
  }
  
  @NonNull
  public DungeonRoom setDebug(boolean debug) {
    this.debug = debug;
    return this;
  }
  
  public <T extends DungeonRoomBehavior> Optional<T> getBehavior(@NonNull Class<T> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    for (DungeonRoomBehavior behavior : this.behaviors) {
      if (behavior.getClass() == clazz)
        return Optional.of((T)behavior); 
    } 
    return Optional.empty();
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.index), this.config.getId(), this.world.getUniqueId() });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonRoom other = (DungeonRoom)obj;
    return (this.index == other.index && this.config.getId().equals(other.config.getId()) && this.world.getUniqueId().equals(other.world.getUniqueId()));
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder("DungeonRoom{");
    sb.append("index=").append(this.index);
    sb.append(", id='").append((this.config != null) ? this.config.getId() : "null").append('\'');
    sb.append(", type='").append((this.config != null) ? this.config.getType() : "null").append('\'');
    sb.append(", premium=").append(this.premium);
    sb.append(", active=").append(this.active);
    sb.append(", finished=").append(this.finished);
    sb.append(", position=(").append(this.x).append(", ").append(this.z).append(")");
    sb.append(", size=(").append((this.config != null && this.config.getSize() != null) ? this.config.getSize().getX() : 0)
      .append("x").append((this.config != null && this.config.getSize() != null) ? this.config.getSize().getZ() : 0).append(")");
    sb.append(", behaviors=[");
    if (!this.behaviors.isEmpty())
      for (int i = 0; i < this.behaviors.size(); i++) {
        if (i > 0)
          sb.append(", "); 
        sb.append(((DungeonRoomBehavior)this.behaviors.get(i)).getClass().getSimpleName());
      }  
    sb.append("]");
    sb.append(", spawnLocations=").append((this.spawnLocations != null) ? this.spawnLocations.size() : 0);
    if (this.spawnLocations != null && !this.spawnLocations.isEmpty()) {
      sb.append(" [");
      for (int i = 0; i < this.spawnLocations.size(); i++) {
        if (i > 0)
          sb.append(", "); 
        DoubleLocation loc = this.spawnLocations.get(i);
        sb.append("(").append(String.format("%.1f", new Object[] { Double.valueOf(loc.getX()) })).append(", ")
          .append(String.format("%.1f", new Object[] { Double.valueOf(loc.getY()) })).append(", ")
          .append(String.format("%.1f", new Object[] { Double.valueOf(loc.getZ()) })).append(", yaw=")
          .append(String.format("%.0f", new Object[] { Float.valueOf(loc.getYaw()) })).append("°)");
      } 
      sb.append("]");
    } 
    sb.append(", startLocations=").append((this.startLocations != null) ? this.startLocations.size() : 0);
    if (this.startLocations != null && !this.startLocations.isEmpty()) {
      sb.append(" [");
      for (int i = 0; i < this.startLocations.size(); i++) {
        if (i > 0)
          sb.append(", "); 
        BlockLocation loc = this.startLocations.get(i);
        sb.append("(").append(loc.getX()).append(", ").append(loc.getY()).append(", ").append(loc.getZ()).append(")");
      } 
      sb.append("]");
    } 
    sb.append(", finishLocations=").append((this.finishLocations != null) ? this.finishLocations.size() : 0);
    if (this.finishLocations != null && !this.finishLocations.isEmpty()) {
      sb.append(" [");
      for (int i = 0; i < this.finishLocations.size(); i++) {
        if (i > 0)
          sb.append(", "); 
        BlockLocation loc = this.finishLocations.get(i);
        sb.append("(").append(loc.getX()).append(", ").append(loc.getY()).append(", ").append(loc.getZ()).append(")");
      } 
      sb.append("]");
    } 
    if (this.chestLocation != null) {
      BlockLocation loc = this.chestLocation.getKey();
      ForgeDirection dir = this.chestLocation.getValue();
      sb.append(", chest=(").append(loc.getX()).append(", ").append(loc.getY()).append(", ").append(loc.getZ())
        .append(", facing=").append((dir != null) ? dir.name() : "null").append(")");
    } else {
      sb.append(", chest=null");
    } 
    sb.append(", hasLoot=").append(hasLoot());
    sb.append(", skippable=").append(isSkippable());
    sb.append(", startingPlayers=[");
    if (!this.startingPlayers.isEmpty()) {
      int count = 0;
      for (String uuid : this.startingPlayers) {
        if (count > 0)
          sb.append(", "); 
        sb.append(uuid);
        count++;
      } 
    } 
    sb.append("]");
    sb.append(", finishingPlayers=[");
    if (!this.finishingPlayers.isEmpty()) {
      int count = 0;
      for (String uuid : this.finishingPlayers) {
        if (count > 0)
          sb.append(", "); 
        sb.append(uuid);
        count++;
      } 
    } 
    sb.append("]");
    if (this.active && this.startTime > 0L) {
      long elapsed = (System.currentTimeMillis() - this.startTime) / 1000L;
      sb.append(", elapsed=").append(elapsed).append("s");
    } 
    sb.append('}');
    return sb.toString();
  }
  
  public static class DungeonRoomFinishEvent extends Event {
    private final DungeonRoom room;
    
    private final boolean finished;
    
    public DungeonRoomFinishEvent(DungeonRoom room, boolean finished) {
      this.room = room;
      this.finished = finished;
    }
    
    public DungeonRoom getRoom() {
      return this.room;
    }
    
    public boolean isFinished() {
      return this.finished;
    }
    
    @Cancelable
    public static class Pre extends DungeonRoomFinishEvent {
      public Pre(DungeonRoom room, boolean finished) {
        super(room, finished);
      }
    }
    
    public static class Post extends DungeonRoomFinishEvent {
      public Post(DungeonRoom room, boolean finished) {
        super(room, finished);
      }
    }
  }
  
  @Cancelable
  public static class Pre extends DungeonRoomFinishEvent {
    public Pre(DungeonRoom room, boolean finished) {
      super(room, finished);
    }
  }
  
  public static class Post extends DungeonRoomFinishEvent {
    public Post(DungeonRoom room, boolean finished) {
      super(room, finished);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\DungeonRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
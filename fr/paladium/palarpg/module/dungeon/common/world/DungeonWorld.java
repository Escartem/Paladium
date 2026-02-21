package fr.paladium.palarpg.module.dungeon.common.world;

import com.eliotlash.molang.MolangParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palacore.commands.admin.QuickRestartCommand;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palamixins.internal.util.ForgeEnv;
import fr.paladium.palarpg.common.api.ItemsRegister;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.client.ui.choice.UIDungeonChoice;
import fr.paladium.palarpg.module.dungeon.client.ui.finish.UIDungeonFinish;
import fr.paladium.palarpg.module.dungeon.common.event.DungeonWorldUpdateEvent;
import fr.paladium.palarpg.module.dungeon.common.network.start.SCPacketRPGDungeonLoading;
import fr.paladium.palarpg.module.dungeon.common.network.start.SCPacketRPGDungeonStart;
import fr.paladium.palarpg.module.dungeon.common.network.world.SCPacketRPGDungeonWorldSync;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.generator.DungeonGenerator;
import fr.paladium.palarpg.module.dungeon.common.world.parser.GsonMolangDungeonNumberAdapter;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palarpg.module.dungeon.server.utils.EntityTrackerUtils;
import fr.paladium.palarpg.module.entity.server.loader.adapter.RPGEntityDataGsonTypeAdapter;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemParchment;
import fr.paladium.palarpg.module.equipment.common.playerdata.RPGCraftPlayerData;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import fr.paladium.worldguardbridge.server.parser.WGParser;
import fr.paladium.worldguardbridge.server.utils.WGServer;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import fr.paladium.zephyrui.internal.mod.utils.command.uuid.UUIDUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class DungeonWorld {
  public static final int ROOM_SIZE = 25;
  
  public static final int MAX_PLAYERS = 4;
  
  private static final Map<World, DungeonWorld> WORLD_MAP = new ConcurrentHashMap<>();
  
  private final transient MolangParser parser;
  
  private final transient Gson gson;
  
  private final transient Object bukkitWorld;
  
  private final transient LinkedList<DungeonRoom> rooms;
  
  private final String uniqueId;
  
  private final LinkedList<DungeonGenerator.DungeonRoomPath> paths;
  
  private final Map<String, DungeonPlayer> players;
  
  private final Map<String, Integer> maxLevels;
  
  private final Set<String> ready;
  
  private transient World world;
  
  private DungeonPlayer owner;
  
  private DungeonPlayer leader;
  
  private DungeonConfig dungeon;
  
  private DungeonGenerator generator;
  
  private DungeonState state;
  
  private int level;
  
  private long startTime;
  
  private boolean respawning;
  
  private int pasteZ;
  
  private String activeRoomId;
  
  public MolangParser getParser() {
    return this.parser;
  }
  
  public Gson getGson() {
    return this.gson;
  }
  
  public Object getBukkitWorld() {
    return this.bukkitWorld;
  }
  
  public LinkedList<DungeonRoom> getRooms() {
    return this.rooms;
  }
  
  public String getUniqueId() {
    return this.uniqueId;
  }
  
  public LinkedList<DungeonGenerator.DungeonRoomPath> getPaths() {
    return this.paths;
  }
  
  public Map<String, Integer> getMaxLevels() {
    return this.maxLevels;
  }
  
  public Set<String> getReady() {
    return this.ready;
  }
  
  public World getWorld() {
    return this.world;
  }
  
  public DungeonPlayer getOwner() {
    return this.owner;
  }
  
  public DungeonPlayer getLeader() {
    return this.leader;
  }
  
  public DungeonConfig getDungeon() {
    return this.dungeon;
  }
  
  public DungeonGenerator getGenerator() {
    return this.generator;
  }
  
  public DungeonState getState() {
    return this.state;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public long getStartTime() {
    return this.startTime;
  }
  
  public boolean isRespawning() {
    return this.respawning;
  }
  
  public int getPasteZ() {
    return this.pasteZ;
  }
  
  public String getActiveRoomId() {
    return this.activeRoomId;
  }
  
  private DungeonWorld() {
    this(null, null);
  }
  
  private DungeonWorld(World world, Object bukkitWorld) {
    this.uniqueId = UUIDUtils.randomStringUUID();
    this.parser = new MolangParser();
    this.world = world;
    this.bukkitWorld = bukkitWorld;
    this.rooms = new LinkedList<>();
    this.paths = new LinkedList<>();
    this.players = new LinkedHashMap<>();
    this.maxLevels = new LinkedHashMap<>();
    this.ready = new HashSet<>();
    this.dungeon = null;
    this.level = -1;
    this.state = DungeonState.WAITING;
    this.parser.setValue("level", 0.0D);
    this.parser.setValue("room_count", 0.0D);
    this.parser.setValue("player_count", 1.0D);
    this.parser.setValue("player_max", 4.0D);
    this.parser.setValue("job_level", 0.0D);
    this.parser.setValue("job_required_exp", 0.0D);
    GsonMolangDungeonNumberAdapter adapter = new GsonMolangDungeonNumberAdapter(this.parser);
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(int.class, adapter);
    builder.registerTypeAdapter(long.class, adapter);
    builder.registerTypeAdapter(float.class, adapter);
    builder.registerTypeAdapter(double.class, adapter);
    builder.registerTypeAdapter(byte.class, adapter);
    builder.registerTypeAdapter(short.class, adapter);
    builder.registerTypeAdapter(Number.class, adapter);
    builder.registerTypeAdapter(RPGEntityData.class, new RPGEntityDataGsonTypeAdapter());
    this.gson = builder.create();
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<DungeonWorld> create() {
    return CompletableFuture.supplyAsync(() -> {
          WorldServer worldServer = DimensionManager.getWorld(0);
          World bukkitWorld = null;
          try {
            if (Bukkit.getServer() == null)
              throw new RuntimeException("Bukkit server is not initialized"); 
            WorldCreator worldCreator = new WorldCreator(UUIDUtils.randomStringUUID());
            File worldFile = new File(worldServer.func_72860_G().func_75765_b(), worldCreator.name());
            FileUtils.copyDirectory(DungeonModule.getServer().getTemplateWorldFile(), worldFile);
            if (!worldFile.exists())
              throw new RuntimeException("Failed to copy the template world to " + worldFile.getAbsolutePath()); 
            worldCreator.environment(World.Environment.NORMAL);
            worldCreator.type(WorldType.FLAT);
            worldCreator.generateStructures(false);
            bukkitWorld = Bukkit.createWorld(worldCreator);
            if (bukkitWorld != null)
              for (WorldServer worldServer1 : (MinecraftServer.func_71276_C()).field_71305_c) {
                if (worldServer1.func_72912_H().func_76065_j().equals(bukkitWorld.getName())) {
                  worldServer = worldServer1;
                  break;
                } 
              }  
            try {
              File worldGuardDirectory = WGServer.getPlugin().getDataFolder();
              File worldGuardDefaultDirectory = new File(worldGuardDirectory, "worlds/world");
              if (worldGuardDefaultDirectory.exists() && worldGuardDefaultDirectory.isDirectory()) {
                File worldGuardTargetDirectory = new File(worldGuardDirectory, "worlds/" + worldCreator.name());
                if (worldGuardTargetDirectory.exists())
                  FileUtils.deleteDirectory(worldGuardTargetDirectory); 
                FileUtils.copyDirectory(worldGuardDefaultDirectory, worldGuardTargetDirectory);
                WGServer.getPlugin().getRegionContainer().get(bukkitWorld).load();
                WGParser.parse((World)worldServer);
              } else {
                DungeonModule.logger.warn("WorldGuard default world configuration directory does not exist at " + worldGuardDefaultDirectory.getAbsolutePath(), new Object[0]);
              } 
            } catch (Exception e) {
              DungeonModule.logger.error("Failed to copy WorldGuard configuration for world " + worldCreator.name(), new Object[] { e });
            } 
          } catch (Exception|Error e) {
            DungeonModule.logger.error("Failed to create a new world from bukkit implementation, using default world instead.", new Object[0]);
            e.printStackTrace();
          } 
          if (WORLD_MAP.containsKey(worldServer))
            throw new IllegalStateException("Dungeon world already exists for world " + worldServer); 
          worldServer.func_82736_K().func_82764_b("keepInventory", "true");
          worldServer.func_82736_K().func_82764_b("doMobSpawning", "false");
          worldServer.func_82736_K().func_82764_b("mobGriefing", "false");
          worldServer.func_82736_K().func_82764_b("doFireTick", "true");
          worldServer.func_82736_K().func_82764_b("doDaylightCycle", "false");
          worldServer.func_72877_b(6000L);
          return set((World)worldServer, new DungeonWorld((World)worldServer, bukkitWorld)).sync();
        });
  }
  
  @NonNull
  public static Optional<DungeonWorld> get(@NonNull String uniqueId) {
    if (uniqueId == null)
      throw new NullPointerException("uniqueId is marked non-null but is null"); 
    return getAll().stream().filter(world -> Objects.equals(world.getUniqueId(), uniqueId)).findAny();
  }
  
  @NonNull
  public static Optional<DungeonWorld> get(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    return Optional.ofNullable(WORLD_MAP.get(world));
  }
  
  @NonNull
  public static Optional<DungeonWorld> get(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (ForgeEnv.isIDE() && !getAll().isEmpty())
      return Optional.of(getAll().iterator().next()); 
    if (player.field_70170_p != null) {
      Optional<DungeonWorld> optionalWorld = get(player.field_70170_p);
      if (optionalWorld.isPresent() && ((DungeonWorld)optionalWorld.get()).isMember(UUIDUtils.toString((Entity)player)))
        return optionalWorld; 
    } 
    return getAll().stream().filter(world -> world.isMember(UUIDUtils.toString((Entity)player))).findAny();
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static Optional<DungeonWorld> getClient() {
    if ((Minecraft.func_71410_x()).field_71441_e == null)
      return Optional.empty(); 
    return Optional.ofNullable(WORLD_MAP.get((Minecraft.func_71410_x()).field_71441_e));
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static Optional<DungeonWorld> getServer() {
    if (MinecraftServer.func_71276_C().func_130014_f_() == null)
      return Optional.empty(); 
    return Optional.ofNullable(WORLD_MAP.get(MinecraftServer.func_71276_C().func_130014_f_()));
  }
  
  @NonNull
  public static Set<DungeonWorld> getAll() {
    return new HashSet<>(WORLD_MAP.values());
  }
  
  @NonNull
  public static DungeonWorld set(@NonNull DungeonWorld dungeonWorld) {
    if (dungeonWorld == null)
      throw new NullPointerException("dungeonWorld is marked non-null but is null"); 
    WORLD_MAP.put(dungeonWorld.getWorld(), dungeonWorld);
    return dungeonWorld;
  }
  
  @NonNull
  public static DungeonWorld set(@NonNull World world, @NonNull DungeonWorld dungeonWorld) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (dungeonWorld == null)
      throw new NullPointerException("dungeonWorld is marked non-null but is null"); 
    WORLD_MAP.put(world, dungeonWorld);
    return dungeonWorld;
  }
  
  public static DungeonWorld remove(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    return WORLD_MAP.remove(world);
  }
  
  @NonNull
  public DungeonWorld addPlayer(@NonNull OfflinePlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.players.size() >= 4 || this.state != DungeonState.WAITING)
      return this; 
    DungeonPlayer dungeonPlayer = new DungeonPlayer(this, player);
    this.players.put(player.getUuidString(), dungeonPlayer);
    if (this.leader == null || !this.leader.isOnline())
      this.leader = dungeonPlayer; 
    if (this.owner == null || !this.owner.isOnline())
      this.owner = dungeonPlayer; 
    getOnlinePlayers().stream().filter(EntityPlayerMP.class::isInstance).filter(onlinePlayer -> !onlinePlayer.func_110124_au().equals(player.getUuid())).map(EntityPlayerMP.class::cast).forEach(onlinePlayer -> {
          (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send(onlinePlayer);
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6✔ §6§lLe §6§ljoueur §b" + player.getName() + " §6§la §6§lété §6§linvité §6§ldans §6§lle §6§ldonjon."));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Chef d'équipe : §b" + ((this.leader == null) ? "Aucun" : this.leader.getName())));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
        });
    if (!player.hasData())
      (new Thread(() -> player.loadData().thenAccept(()), "DungeonWorld/PaladiumData-" + player
          
          .getUuidString())).start(); 
    this.parser.setValue("player_count", getOnlinePlayers().size());
    updateMaxLevels().sync();
    return this;
  }
  
  @NonNull
  public DungeonWorld removePlayer(@NonNull String uuid, boolean kick) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    DungeonPlayer player = this.players.remove(uuid);
    if (player == null)
      return this; 
    if (this.players.isEmpty()) {
      remove();
      return this;
    } 
    if (this.leader != null && this.leader.equals(player)) {
      updateLeader();
      if (this.leader == null) {
        remove();
        return this;
      } 
    } 
    player.getOnlinePlayer().ifPresent(onlinePlayer -> ((EntityPlayerMP)onlinePlayer).field_71135_a.func_147360_c("§8[§6Paladium§8] §cVous avez été " + (kick ? "expulsé" : "retiré") + " du donjon de §b" + ((this.leader == null) ? "Aucun" : this.leader.getName())));
    this.parser.setValue("player_count", getOnlinePlayers().size());
    updateMaxLevels().sync();
    return this;
  }
  
  @NonNull
  public DungeonWorld updateLeader() {
    return setLeader(this.players.values().stream().filter(DungeonPlayer::isOnline).filter(DungeonPlayer::isAlive).findFirst().orElse(this.owner));
  }
  
  @NonNull
  public DungeonWorld setLeader(DungeonPlayer player) {
    if (player == null) {
      this.leader = null;
      remove();
      return this;
    } 
    if (!isMember(player.getUuidString()) || !player.isOnline() || (this.leader != null && this.leader.equals(player)))
      return this; 
    getOnlinePlayers().stream().filter(EntityPlayerMP.class::isInstance).map(EntityPlayerMP.class::cast).forEach(onlinePlayer -> {
          (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send(onlinePlayer);
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          if (onlinePlayer.func_110124_au().equals(player.getUuid())) {
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §7Vous êtes le chef de l'équipe, vous pouvez"));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Gérer votre équipe."));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Changer ou lancer le donjon."));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Modifier votre équipement."));
          } else {
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6✔ §6§lLe §6§ljoueur §b" + player.getName() + " §6§lest §6§ldésormais §6§lle §6§lchef §6§lde §6§ll'équipe."));
            if (this.leader != null && onlinePlayer.func_110124_au().equals(this.leader.getUuid())) {
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cn'êtes §cplus §cle §cchef §cde §cl'équipe."));
            } 
          } 
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
        });
    this.leader = player;
    sync();
    return this;
  }
  
  @NonNull
  public List<DungeonPlayer> getPlayers() {
    return new ArrayList<>(this.players.values());
  }
  
  @NonNull
  public Optional<DungeonPlayer> getPlayer(int index) {
    return this.players.values().stream().skip(index).findFirst();
  }
  
  @NonNull
  public Optional<DungeonPlayer> getPlayer(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return Optional.ofNullable(this.players.get(uuid));
  }
  
  @NonNull
  public List<EntityPlayer> getOnlinePlayers() {
    List<EntityPlayer> players = new ArrayList<>();
    this.players.values().forEach(player -> player.getOnlinePlayer().ifPresent(players::add));
    return players;
  }
  
  @NonNull
  public List<EntityPlayer> getAlivePlayers() {
    List<EntityPlayer> players = new ArrayList<>();
    this.players.values().stream().filter(DungeonPlayer::isAlive).forEach(player -> player.getOnlinePlayer().ifPresent(players::add));
    return players;
  }
  
  @NonNull
  public List<EntityPlayer> getWorldPlayers() {
    return this.world.field_73010_i;
  }
  
  public boolean isLeader(@NonNull Entity player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return isLeader(UUIDUtils.toString(player));
  }
  
  public boolean isLeader(@NonNull UUID playerUuid) {
    if (playerUuid == null)
      throw new NullPointerException("playerUuid is marked non-null but is null"); 
    return isLeader(UUIDUtils.toString(playerUuid));
  }
  
  public boolean isLeader(@NonNull String playerUuid) {
    if (playerUuid == null)
      throw new NullPointerException("playerUuid is marked non-null but is null"); 
    return (this.leader != null && this.leader.getUuidString().equals(playerUuid));
  }
  
  public boolean isMember(@NonNull String player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return this.players.containsKey(player);
  }
  
  @NonNull
  public DungeonWorld setWorld(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    this.world = world;
    return this;
  }
  
  @NonNull
  public DungeonWorld setDungeon(@NonNull DungeonConfig dungeon) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    this.dungeon = dungeon;
    sync();
    return this;
  }
  
  public void setState(@NonNull DungeonState state) {
    if (state == null)
      throw new NullPointerException("state is marked non-null but is null"); 
    this.state = state;
    sync();
  }
  
  @NonNull
  public DungeonWorld setLevel(int level) {
    if (level < 0)
      throw new IllegalArgumentException("Dungeon level cannot be negative"); 
    this.level = level;
    sync();
    return this;
  }
  
  @NonNull
  public DungeonWorld setRespawning(boolean respawning) {
    this.respawning = respawning;
    sync();
    return this;
  }
  
  @NonNull
  public DungeonWorld setActiveRoom(@NonNull DungeonRoom room) {
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.activeRoomId = room.getConfig().getId();
    sync();
    return this;
  }
  
  @NonNull
  public DungeonWorld clear() {
    this.dungeon = null;
    this.level = -1;
    sync();
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld remove() {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            if (this.rooms != null && !this.rooms.isEmpty())
              this.rooms.forEach(DungeonRoom::remove); 
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
            getOnlinePlayers().forEach(());
            remove(this.world);
            if (ForgeEnv.isIDE())
              return; 
            for (Integer worldId : DimensionManager.getIDs()) {
              if (DimensionManager.getWorld(worldId.intValue()) == this.world) {
                DimensionManager.setWorld(worldId.intValue(), null);
                break;
              } 
            } 
            try {
              World bukkitWorldImpl = (World)this.bukkitWorld;
              Bukkit.unloadWorld(bukkitWorldImpl, false);
            } catch (Exception e) {
              DungeonModule.logger.error("Failed to delete world " + this.world.func_72912_H().func_76065_j() + " (" + this.uniqueId + ") from bukkit implementation.", new Object[0]);
              e.printStackTrace();
            } 
          } });
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld updateMaxLevels() {
    this.maxLevels.clear();
    for (DungeonConfig dungeon : DungeonManager.getDungeons()) {
      int maxLevel = -1;
      for (EntityPlayer player : getOnlinePlayers()) {
        RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
        if (data == null)
          continue; 
        if (maxLevel == -1) {
          maxLevel = data.getUnlockedLevel(dungeon);
          continue;
        } 
        maxLevel = Math.min(maxLevel, data.getUnlockedLevel(dungeon));
      } 
      maxLevel = (maxLevel == -1) ? 0 : maxLevel;
      this.maxLevels.put(dungeon.getId(), Integer.valueOf(maxLevel));
    } 
    if (this.dungeon != null && this.level > getMaxLevel(this.dungeon))
      this.level = getMaxLevel(this.dungeon); 
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld sync() {
    MinecraftForge.EVENT_BUS.post((Event)new DungeonWorldUpdateEvent.Pre(this));
    SCPacketRPGDungeonWorldSync packet = new SCPacketRPGDungeonWorldSync(this);
    getOnlinePlayers().stream().filter(EntityPlayerMP.class::isInstance).map(EntityPlayerMP.class::cast).forEach(packet::send);
    MinecraftForge.EVENT_BUS.post((Event)new DungeonWorldUpdateEvent.Post(this));
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld sync(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    MinecraftForge.EVENT_BUS.post((Event)new DungeonWorldUpdateEvent.Pre(this));
    (new SCPacketRPGDungeonWorldSync(this)).send(player);
    MinecraftForge.EVENT_BUS.post((Event)new DungeonWorldUpdateEvent.Post(this));
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld teleport(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    double posX = (this.world.func_72861_E()).field_71574_a + 0.5D;
    double posY = (this.world.func_72861_E()).field_71572_b;
    double posZ = (this.world.func_72861_E()).field_71573_c + 0.5D;
    if (player.field_70170_p != this.world) {
      if (Bukkit.getPlayer(player.func_110124_au()) == null) {
        player.field_71135_a.func_147360_c("§8[§6Paladium§8] §cUne erreur est survenue lors de votre téléportation dans le donjon.");
        return this;
      } 
      Bukkit.getPlayer(player.func_110124_au()).teleport(new Location((World)this.bukkitWorld, posX, posY, posZ, 180.0F, 0.0F));
    } else {
      TeleportUtils.teleport((EntityPlayer)player, posX, posY, posZ, 180.0F, 0.0F);
    } 
    EntityTrackerUtils.update((EntityPlayer)player);
    player.func_71063_a(new ChunkCoordinates((this.world.func_72861_E()).field_71574_a, (this.world.func_72861_E()).field_71572_b, (this.world.func_72861_E()).field_71573_c), true);
    player.func_145747_a((IChatComponent)new ChatComponentText(""));
    player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
    player.func_145747_a((IChatComponent)new ChatComponentText(" §6⚔ §lBienvenue sur le serveur donjon."));
    player.func_145747_a((IChatComponent)new ChatComponentText(""));
    if (isLeader((Entity)player)) {
      player.func_145747_a((IChatComponent)new ChatComponentText(" §7Vous êtes le chef de l'équipe, vous pouvez"));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Gérer votre équipe."));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Changer ou lancer le donjon."));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Modifier votre équipement."));
    } else {
      player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Vous êtes dans l'équipe de §6" + this.leader.getName()));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §6⊙ §7Attendez que §6" + this.leader.getName() + "§7 lance le donjon"));
    } 
    player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
    player.func_145747_a((IChatComponent)new ChatComponentText(""));
    String uuid = UUIDUtils.toString((Entity)player);
    for (DungeonWorld world : getAll()) {
      if (equals(world))
        continue; 
      if (world.isMember(uuid))
        world.removePlayer(uuid, false); 
    } 
    getPlayer(uuid).ifPresent(dungeonPlayer -> dungeonPlayer.setAlive(true));
    StatsManager.SPEED.applySpeed((EntityLivingBase)player);
    StatsManager.MAX_HEALTH.applyMaxHealth((EntityLivingBase)player, true);
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld start() throws Exception {
    boolean quickRestartActive = false;
    try {
      if (QuickRestartCommand.active)
        quickRestartActive = true; 
    } catch (Exception|NoClassDefFoundError exception) {}
    if (quickRestartActive)
      throw new IllegalStateException("un redémarrage du serveur est en cours"); 
    if (this.state != DungeonState.WAITING)
      throw new IllegalStateException("un donjon est déjà en cours"); 
    setState(DungeonState.STARTING);
    if (this.dungeon == null || !DungeonManager.getDungeon(this.dungeon.getId()).isPresent()) {
      setState(DungeonState.WAITING);
      throw new IllegalArgumentException("ce donjon n'existe pas");
    } 
    if (this.level < 0) {
      setState(DungeonState.WAITING);
      throw new IllegalArgumentException("le niveau du donjon est invalide");
    } 
    this.ready.clear();
    for (DungeonPlayer dungeonPlayer : getPlayers()) {
      if (!dungeonPlayer.isOnline())
        removePlayer(dungeonPlayer.getUuidString(), false); 
      dungeonPlayer.reset();
    } 
    List<EntityPlayer> onlinePlayers = getOnlinePlayers();
    if (this.leader == null || onlinePlayers.isEmpty()) {
      setState(DungeonState.WAITING);
      remove();
      throw new IllegalStateException("le donjon n'a pas de chef d'équipe ou de joueurs en ligne");
    } 
    int maxLevel = updateMaxLevels().getMaxLevel(this.dungeon);
    if (this.level > maxLevel) {
      setState(DungeonState.WAITING);
      throw new IllegalStateException("le niveau du donjon est supérieur au niveau maximum de l'équipe (" + maxLevel + ")");
    } 
    Optional<DungeonLevelConfig> optionalDungeonLevel = this.dungeon.getLevelOrInfinite(this.level);
    if (!optionalDungeonLevel.isPresent()) {
      setState(DungeonState.WAITING);
      throw new IllegalStateException("ce niveau de donjon n'existe pas");
    } 
    ItemStack key = this.dungeon.getItem();
    ItemStack keyring = new ItemStack(ItemsRegister.DUNGEON_KEYRING);
    EntityPlayer leaderPlayer = this.leader.getOnlinePlayer().orElse(null);
    if (key != null) {
      if (leaderPlayer == null) {
        setState(DungeonState.WAITING);
        throw new IllegalStateException("le chef d'équipe n'est pas en ligne");
      } 
      if (!leaderPlayer.field_71075_bZ.field_75098_d)
        if (InventoryUtils.haveRequiredItem(leaderPlayer, key, 1, false)) {
          InventoryUtils.removeItems(leaderPlayer, key, 1, false);
          leaderPlayer.field_71069_bz.func_75142_b();
        } else if (InventoryUtils.haveRequiredItem(leaderPlayer, keyring, 1, false)) {
          InventoryUtils.removeItems(leaderPlayer, keyring, 1, false);
          leaderPlayer.field_71069_bz.func_75142_b();
        } else {
          setState(DungeonState.WAITING);
          (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg", 2.0F)).send((EntityPlayerMP)leaderPlayer);
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§ldémarrer §6§lce §6§ldonjon."));
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cdevez §cposséder §bx1 " + (IRPGItem.is(key) ? ((IRPGItem)IRPGItem.get(key).get()).getItemData().getTranslation("en") : key.func_82833_r()) + " §cou §bx1 " + keyring.func_82833_r() + " §cpour §clancer §cle §cdonjon."));
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          leaderPlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          return this;
        }  
    } 
    this.owner = this.leader;
    this.parser.setValue("level", this.level);
    this.parser.setValue("player_count", onlinePlayers.size());
    this.parser.setValue("player_max", 4.0D);
    DungeonLevelConfig dungeonLevel = optionalDungeonLevel.get();
    try {
      this.generator = new DungeonGenerator(this, dungeonLevel);
      this.parser.setValue("room_count", this.generator.getRoomCount());
      SCPacketRPGDungeonLoading packet = new SCPacketRPGDungeonLoading();
      onlinePlayers.stream().filter(EntityPlayerMP.class::isInstance).map(EntityPlayerMP.class::cast).forEach(player -> {
            packet.send(player);
            StatsManager.SPEED.applySpeed((EntityLivingBase)player);
            StatsManager.MAX_HEALTH.applyMaxHealth((EntityLivingBase)player, true);
          });
      this.rooms.forEach(DungeonRoom::remove);
      this.rooms.clear();
      this.paths.clear();
      this.generator.generate();
      this.generator.printTree();
      this.paths.add(this.generator.getRoom(1, 0));
      this.pasteZ += 500;
      this.generator.getRoom(1, 0).create().paste(500, this.pasteZ).thenAccept(room -> {
            this.rooms.add(room);
            setState(DungeonState.READY);
          }).exceptionally(t -> {
            t.printStackTrace();
            setState(DungeonState.WAITING);
            throw new RuntimeException("Failed to create dungeon rooms", t);
          });
      (new Thread(() -> {
            try {
              Thread.sleep(30000L);
              if (this.state == DungeonState.STARTING || this.state == DungeonState.READY) {
                if (key != null && !leaderPlayer.field_71075_bZ.field_75098_d) {
                  InventoryUtils.addItem(leaderPlayer, key);
                  leaderPlayer.field_71069_bz.func_75142_b();
                } 
                setState(DungeonState.WAITING);
                this.rooms.forEach(DungeonRoom::remove);
                getOnlinePlayers().stream().filter(EntityPlayerMP.class::isInstance).map(EntityPlayerMP.class::cast).forEach(());
              } 
            } catch (Exception exception) {}
          }"DungeonWorld/Generator-" + this.uniqueId)).start();
    } catch (Exception e) {
      e.printStackTrace();
      setState(DungeonState.WAITING);
      throw new IllegalStateException("échec de la génération du donjon");
    } 
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld ready(@NonNull EntityPlayerMP player) throws Exception {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.state != DungeonState.READY)
      throw new IllegalStateException("Dungeon world is not ready (" + this.state + ")"); 
    List<EntityPlayer> onlinePlayers = getOnlinePlayers();
    this.ready.add(UUIDUtils.toString((Entity)player));
    if (this.ready.size() != onlinePlayers.size())
      return sync(); 
    SCPacketRPGDungeonStart packet = new SCPacketRPGDungeonStart();
    String playerNames = String.join(", ", (CharSequence[])onlinePlayers.stream().map(EntityPlayer::func_70005_c_).toArray(x$0 -> new String[x$0]));
    onlinePlayers.stream().filter(EntityPlayerMP.class::isInstance).map(EntityPlayerMP.class::cast).forEach(onlinePlayer -> {
          packet.send(onlinePlayer);
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §lDonjon en cours d'exploration !"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Nom du donjon : §6" + StringUtils.capitalize(this.dungeon.getName())));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Palier sélectionné : §6" + (this.level + 1)));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Équipe engagée : §6" + playerNames));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          StatsManager.MAX_HEALTH.applyMaxHealth((EntityLivingBase)onlinePlayer, true);
        });
    ((DungeonRoom)this.rooms.getFirst()).join();
    setState(DungeonState.STARTED);
    this.startTime = System.currentTimeMillis();
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld next(int column) throws Exception {
    List<EntityPlayer> players = getOnlinePlayers();
    DungeonRoom currentRoom = getRoom().orElse(null);
    if (currentRoom == null || this.state != DungeonState.STARTED)
      throw new IllegalStateException("Dungeon world is not started (" + this.state + ") or has no active room"); 
    if (!currentRoom.isFinished())
      throw new IllegalStateException("Dungeon world current room is not finished"); 
    int row = currentRoom.getIndex() + 1;
    try {
      if (!this.generator.hasPath(column, row))
        throw new IllegalArgumentException("Dungeon world has no path at column " + column + " and row " + row); 
    } catch (Exception e) {
      throw e;
    } 
    DungeonGenerator.DungeonRoomPath nextPath = this.generator.getRoom(column, row);
    if (this.paths.isEmpty())
      throw new IllegalStateException("Dungeon world has no current path"); 
    if (!((DungeonGenerator.DungeonRoomPath)this.paths.getLast()).getLinks().contains(nextPath))
      throw new IllegalArgumentException("Dungeon world current path is not linked to the next path"); 
    DungeonRoom nextRoom = nextPath.create();
    this.paths.add(nextPath);
    this.rooms.add(nextRoom);
    sync();
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> nextRoom.paste(currentRoom.getX() + currentRoom.getConfig().getSize().getX() * 25 + 50, currentRoom.getZ()).whenComplete(()) });
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld stop() throws Exception {
    if (this.state != DungeonState.STARTED && this.state != DungeonState.FINISHED)
      throw new IllegalStateException("Dungeon world is not started (" + this.state + ")"); 
    setState(DungeonState.FINISHED);
    getOnlinePlayers().forEach(player -> teleport((EntityPlayerMP)player));
    this.rooms.forEach(DungeonRoom::remove);
    this.rooms.clear();
    setState(DungeonState.WAITING);
    if (getOnlinePlayers().isEmpty())
      remove(); 
    sync();
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public DungeonWorld finish(boolean success) throws Exception {
    if (this.owner != null)
      setLeader(this.owner); 
    Optional<DungeonLevelConfig> optionalDungeonLevel = (this.dungeon == null) ? Optional.<DungeonLevelConfig>empty() : this.dungeon.getLevelOrInfinite(this.level);
    if (optionalDungeonLevel.isPresent()) {
      DungeonLevelConfig dungeonLevel = optionalDungeonLevel.get();
      String playerNames = String.join(", ", (CharSequence[])getOnlinePlayers().stream().map(EntityPlayer::func_70005_c_).toArray(x$0 -> new String[x$0]));
      getOnlinePlayers().forEach(player -> {
            StatsManager.SPEED.applySpeed((EntityLivingBase)player);
            StatsManager.MAX_HEALTH.applyMaxHealth((EntityLivingBase)player, true);
            AtomicReference<JobType> jobType = new AtomicReference<>(null);
            AtomicReference<Double> jobExperience = new AtomicReference<>(Double.valueOf(0.0D));
            RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
            DungeonPlayer dungeonPlayer = getPlayer(UUIDUtils.toString((Entity)player)).orElse(null);
            if (success) {
              (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §a§lVous §a§lavez §a§lterminé §a§lce §a§lpalier."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Nom du donjon : §6" + StringUtils.capitalize(this.dungeon.getName())));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Palier sélectionné : §6" + (dungeonLevel.getLevel() + 1)));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Équipe engagée : §6" + playerNames));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              if (dungeonLevel.getRewards() != null && dungeonLevel.getRewards().getJobs() != null && dungeonPlayer != null && dungeonPlayer.isAlive()) {
                JobType job = JobType.valueOf(dungeonLevel.getRewards().getJobs().getJob().toUpperCase());
                if (job != null)
                  PalaJobsAPI.inst().getJobsPlayer(player).ifPresent(()); 
              } 
              if (data != null) {
                if (data.getBackpack() != null && !data.getBackpack().isEmpty()) {
                  List<ItemStack> items = new ArrayList<>();
                  RPGCraftPlayerData craftPlayerData = (RPGCraftPlayerData)RPGPlayer.getData((Entity)player, "craft");
                  for (RPGDungeonPlayerData.RPGDungeonItem dungeonItem : data.getBackpack()) {
                    if (dungeonItem == null || dungeonItem.getItem() == null || dungeonItem.getItem().func_77973_b() == null)
                      continue; 
                    ItemStack itemStack = dungeonItem.getItem();
                    if (itemStack.func_77973_b() instanceof RPGItemParchment) {
                      ItemStack craftedItem = ((RPGItemParchment)itemStack.func_77973_b()).getItemData().getItemStack();
                      if (craftedItem != null && craftPlayerData != null && !craftPlayerData.isCraftUnlocked(craftedItem)) {
                        craftPlayerData.unlockCraft(craftedItem);
                        player.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §aVous avez débloqué le §b" + itemStack.func_82833_r())).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                      } 
                      continue;
                    } 
                    items.add(itemStack);
                  } 
                  PalaGiveManager.inst().give(player, items);
                } 
                this.dungeon.getLevelOrInfinite(this.level + 1).ifPresent(());
              } 
              if (dungeonLevel.getRewards() != null && dungeonLevel.getRewards().getJobs() != null && dungeonPlayer != null && dungeonPlayer.isAlive()) {
                jobType.set(JobType.valueOf(dungeonLevel.getRewards().getJobs().getJob().toUpperCase()));
                if (jobType.get() != null)
                  PalaJobsAPI.inst().getJobsPlayer(player).ifPresent(()); 
              } 
            } else {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg", 2.0F)).send((EntityPlayerMP)player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §c§lVous §c§lavez §c§léchoué §c§lce §c§lpalier."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Nom du donjon : §6" + StringUtils.capitalize(this.dungeon.getName())));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Palier sélectionné : §6" + (dungeonLevel.getLevel() + 1)));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Équipe engagée : §6" + playerNames));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
            } 
            if (data != null) {
              Map<String, RPGItemRarity> items = new HashMap<>();
              if (data.getBackpack() != null && !data.getBackpack().isEmpty())
                for (RPGDungeonPlayerData.RPGDungeonItem dungeonItem : data.getBackpack()) {
                  if (dungeonItem == null || dungeonItem.getItem() == null)
                    continue; 
                  items.put(ItemStackSerializer.write64(dungeonItem.getItem()), dungeonItem.getRarity());
                }  
              AtomicInteger jobLevel = new AtomicInteger(0);
              AtomicReference<Double> jobProgress = new AtomicReference<>(Double.valueOf(0.0D));
              if (jobType.get() != null)
                PalaJobsAPI.inst().getJobsPlayer(player).ifPresent(()); 
              ZUIServer.open(UIDungeonFinish.class, true, (EntityPlayerMP)player, new Object[] { Boolean.valueOf(success), new UIDungeonFinish.UIDungeonFinishData(this.dungeon, this.paths, items, System.currentTimeMillis() - this.startTime, jobLevel.get(), jobType.get(), ((Double)jobExperience.get()).doubleValue(), ((Double)jobProgress.get()).doubleValue(), (dungeonPlayer != null) ? dungeonPlayer.getDungeonExperience() : 0.0D, this.level) });
              if (data.getBackpack() != null && !data.getBackpack().isEmpty())
                data.getBackpack().clear(); 
              data.sync();
            } 
          });
    } 
    stop();
    clear();
    updateMaxLevels();
    sync();
    return this;
  }
  
  public int getMaxLevel(@NonNull DungeonConfig dungeon) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    return ((Integer)this.maxLevels.getOrDefault(dungeon.getId(), Integer.valueOf(0))).intValue();
  }
  
  @NonNull
  public Optional<DungeonRoom> getRoom() {
    if (this.rooms.isEmpty())
      return Optional.empty(); 
    return this.rooms.stream().filter(DungeonRoom::isActive).findAny();
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.level), this.state, this.dungeon, this.leader, this.maxLevels, this.players });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonWorld other = (DungeonWorld)obj;
    return (this.level == other.level && this.state == other.state && Objects.equals(this.dungeon, other.dungeon) && Objects.equals(this.leader, other.leader) && Objects.equals(this.maxLevels, other.maxLevels) && Objects.equals(this.players, other.players));
  }
  
  public enum DungeonState {
    WAITING("hub.ogg", 1.0F),
    STARTING("hub.ogg", 1.0F),
    READY("hub.ogg", 1.0F),
    STARTED("room.ogg", 2.0F),
    FINISHED("room.ogg", 1.0F);
    
    DungeonState(String music, float volume) {
      this.music = music;
      this.volume = volume;
    }
    
    private final String music;
    
    private final float volume;
    
    public String getMusic() {
      return this.music;
    }
    
    public float getVolume() {
      return this.volume;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\DungeonWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
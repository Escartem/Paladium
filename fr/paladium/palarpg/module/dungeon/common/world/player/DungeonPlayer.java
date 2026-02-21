package fr.paladium.palarpg.module.dungeon.common.world.player;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.client.ui.respawn.UIDungeonRespawn;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonGlobalConfig;
import fr.paladium.palarpg.module.dungeon.server.event.DungeonPlayerDeathEvent;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

public final class DungeonPlayer {
  private transient DungeonWorld world;
  
  private final OfflinePlayer player;
  
  private boolean alive;
  
  private int remainingRespawns;
  
  private double dungeonExperience;
  
  public OfflinePlayer getPlayer() {
    return this.player;
  }
  
  public boolean isAlive() {
    return this.alive;
  }
  
  public int getRemainingRespawns() {
    return this.remainingRespawns;
  }
  
  public double getDungeonExperience() {
    return this.dungeonExperience;
  }
  
  public DungeonPlayer(@NonNull OfflinePlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    this.player = player;
    this.alive = true;
  }
  
  public DungeonPlayer(@NonNull DungeonWorld world, @NonNull OfflinePlayer player) {
    this(player);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    this.world = world;
  }
  
  @SideOnly(Side.CLIENT)
  public static Optional<DungeonPlayer> getClient() {
    DungeonWorld world = DungeonWorld.getClient().orElse(null);
    if (world == null)
      return Optional.empty(); 
    return world.getPlayer(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g));
  }
  
  public DungeonWorld getWorld() {
    if (this.world == null)
      if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        this.world = DungeonWorld.getClient().get();
      } else {
        Optional<EntityPlayer> optionalPlayer = this.player.update().getPlayer();
        if (!optionalPlayer.isPresent())
          return null; 
        this.world = DungeonWorld.get(optionalPlayer.get()).orElse(null);
      }  
    return this.world;
  }
  
  @NonNull
  public String getName() {
    return this.player.getName();
  }
  
  @NonNull
  public UUID getUuid() {
    return this.player.getUuid();
  }
  
  @NonNull
  public String getUuidString() {
    return this.player.getUuidString();
  }
  
  @NonNull
  public Optional<EntityPlayer> getOnlinePlayer() {
    return this.player.getPlayer(getWorld().getWorld());
  }
  
  public boolean isLeader() {
    return getWorld().isLeader(this.player.getUuidString());
  }
  
  public boolean isOnline() {
    return this.player.isOnline(getWorld().getWorld());
  }
  
  @NonNull
  public DungeonPlayer setAlive(boolean alive) {
    if (alive == this.alive)
      return this; 
    this.alive = alive;
    getOnlinePlayer().ifPresent(player -> {
          if (this.alive) {
            player.field_71075_bZ.field_75099_e = true;
            if (!player.field_71075_bZ.field_75098_d) {
              player.field_71075_bZ.field_75100_b = false;
              player.field_71075_bZ.field_75101_c = false;
              player.field_71075_bZ.field_75102_a = false;
            } 
          } else {
            player.field_71075_bZ.field_75099_e = false;
            player.field_71075_bZ.field_75100_b = true;
            player.field_71075_bZ.field_75101_c = true;
            player.field_71075_bZ.field_75102_a = true;
          } 
          ((EntityPlayerMP)player).func_71016_p();
        });
    return this;
  }
  
  @NonNull
  public DungeonPlayer setRemainingRespawns(int amount) {
    this.remainingRespawns = amount;
    return this;
  }
  
  @NonNull
  public DungeonPlayer addExperience(double amount) {
    this.dungeonExperience += amount;
    return this;
  }
  
  @NonNull
  public DungeonPlayer setExperience(double amount) {
    this.dungeonExperience = amount;
    return this;
  }
  
  @NonNull
  public DungeonPlayer reset() {
    setAlive(true);
    this.remainingRespawns = ((DungeonGlobalConfig)DungeonManager.getGlobalConfig().join()).getRespawn().getUsage();
    this.dungeonExperience = 0.0D;
    return this;
  }
  
  @NonNull
  public DungeonPlayer kill() {
    if (MinecraftForge.EVENT_BUS.post((Event)new DungeonPlayerDeathEvent.Pre(this)))
      return this; 
    setAlive(false);
    this.world.updateLeader();
    this.world.sync();
    if (this.world.getAlivePlayers().isEmpty())
      (new Thread(() -> {
            DungeonGlobalConfig config = DungeonManager.getGlobalConfig().join();
            long price = config.getRespawn().getPrice().getTeam() * this.world.getOnlinePlayers().size();
            long endTime = UniversalTimeUtils.now() + 7000L;
            for (EntityPlayer player : this.world.getOnlinePlayers()) {
              ZUIServer.open(UIDungeonRespawn.class, (EntityPlayerMP)player, new Object[] { Long.valueOf(price) });
            } 
            while (UniversalTimeUtils.now() < endTime + 2000L || this.world.isRespawning()) {
              try {
                Thread.sleep(500L);
              } catch (InterruptedException interruptedException) {}
            } 
            if (this.world.getAlivePlayers().isEmpty())
              FMLServerScheduler.getInstance().add(new Runnable[] { () }); 
          })).start(); 
    this.world.getOnlinePlayers().forEach(onlinePlayer -> {
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §c✘ §c§lLe §c§ljoueur §4§l" + getName() + " §c§la §c§lété §c§ltué."));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
        });
    MinecraftForge.EVENT_BUS.post((Event)new DungeonPlayerDeathEvent.Post(this));
    return this;
  }
  
  @NonNull
  public DungeonPlayer respawn() {
    if (isAlive())
      return this; 
    setAlive(true);
    this.world.sync();
    return this;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.player.getUuidString(), Boolean.valueOf(this.alive), Integer.valueOf(this.remainingRespawns), Double.valueOf(this.dungeonExperience) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonPlayer other = (DungeonPlayer)obj;
    return (Objects.equals(this.player.getUuidString(), other.player.getUuidString()) && this.alive == other.alive && this.remainingRespawns == other.remainingRespawns && Double.doubleToLongBits(this.dungeonExperience) == Double.doubleToLongBits(other.dungeonExperience));
  }
  
  public String toString() {
    return "DungeonPlayer{player=" + this.player.getName() + " (" + this.player.getUuidString() + "), alive=" + this.alive + ", remainingRespawns=" + this.remainingRespawns + ", dungeonExperience=" + this.dungeonExperience + "}";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\player\DungeonPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palaforgeutils.lib.sidedaction.utils.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public final class ServerActionContext {
  private Side side;
  
  private EntityPlayerMP player;
  
  private NetHandlerPlayServer netHandler;
  
  private boolean active;
  
  public Side getSide() {
    return this.side;
  }
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public NetHandlerPlayServer getNetHandler() {
    return this.netHandler;
  }
  
  @NonNull
  public final ServerActionContext set(@NonNull Side side, @NonNull EntityPlayerMP player, @NonNull NetHandlerPlayServer netHandler) {
    if (side == null)
      throw new NullPointerException("side is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (netHandler == null)
      throw new NullPointerException("netHandler is marked non-null but is null"); 
    this.side = side;
    this.player = player;
    this.netHandler = netHandler;
    this.active = true;
    return this;
  }
  
  @NonNull
  public final ServerActionContext clear() {
    this.side = null;
    this.player = null;
    this.netHandler = null;
    this.active = false;
    return this;
  }
  
  public final void execute(@NonNull Side side, @NonNull EntityPlayerMP player, @NonNull NetHandlerPlayServer netHandler, @NonNull Runnable runnable) {
    if (side == null)
      throw new NullPointerException("side is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (netHandler == null)
      throw new NullPointerException("netHandler is marked non-null but is null"); 
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    set(side, player, netHandler);
    runnable.run();
    clear();
  }
  
  @NonNull
  public final CompletableFuture<Void> async(@NonNull Runnable runnable) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    return CompletableFuture.runAsync(runnable);
  }
  
  @NonNull
  public final CompletableFuture<Void> async(@NonNull Runnable runnable, @NonNull Executor executor) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    if (executor == null)
      throw new NullPointerException("executor is marked non-null but is null"); 
    return CompletableFuture.runAsync(runnable, executor);
  }
  
  @NonNull
  private final CompletableFuture<Void> schedule(@NonNull Runnable runnable, long value, @NonNull TimeUnit unit) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    if (unit == null)
      throw new NullPointerException("unit is marked non-null but is null"); 
    CompletableFuture<Void> future = new CompletableFuture<>();
    (new Thread(() -> {
          try {
            Thread.sleep(unit.toMillis(value));
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
              FMLClientScheduler.getInstance().add(new Runnable[] { () }, );
            } else {
              FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
            } 
          } catch (InterruptedException e) {
            future.completeExceptionally(e);
          } 
        }"ServerActionContext-Scheduler")).start();
    return future;
  }
  
  public final boolean hasPermission(String permission) {
    if (permission == null || permission.isEmpty() || this.player == null)
      return true; 
    return BukkitUtils.hasPermission((Entity)this.player, permission);
  }
  
  public final boolean isActive() {
    return (this.side != null && this.player != null && this.netHandler != null && this.active);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\server\ServerActionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
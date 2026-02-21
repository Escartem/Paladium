package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityWorker;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class WorkerData {
  public static final long WORKER_MIN_DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  public static final long MAX_WORKER_DURATION = TimeUnit.MINUTES.toMillis(60L);
  
  private final long expirationMillis;
  
  private long lastAppliedMillis;
  
  private final UUID targetUniqueId;
  
  private final Random random;
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public long getLastAppliedMillis() {
    return this.lastAppliedMillis;
  }
  
  public UUID getTargetUniqueId() {
    return this.targetUniqueId;
  }
  
  public Random getRandom() {
    return this.random;
  }
  
  public WorkerData(EntityPlayerMP player) {
    long now = System.currentTimeMillis();
    this.expirationMillis = now + MAX_WORKER_DURATION;
    this.targetUniqueId = player.func_110124_au();
    this.random = new Random();
    apply(now, MonthlyUtils.getOnlinePlayers());
  }
  
  public void apply(long now, List<EntityPlayerMP> players) {
    this.lastAppliedMillis = now;
    if (this.random.nextBoolean())
      return; 
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.targetUniqueId, players);
    if (player == null)
      return; 
    for (int i = 0; i < 10; i++) {
      EntityWorker worker = new EntityWorker(player.field_70170_p, player);
      worker.func_70634_a(player.field_70165_t, player.field_70163_u, player.field_70161_v);
      player.field_70170_p.func_72838_d((Entity)worker);
    } 
  }
  
  public boolean canApply(long now) {
    return (now - this.lastAppliedMillis >= WORKER_MIN_DURATION);
  }
  
  public boolean isExpired(long now) {
    return (now > this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\WorkerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
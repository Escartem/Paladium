package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class StormData {
  public void setCreationMillis(long creationMillis) {
    this.creationMillis = creationMillis;
  }
  
  public void setLastHitMillis(long lastHitMillis) {
    this.lastHitMillis = lastHitMillis;
  }
  
  public void setLastSalveMillis(long lastSalveMillis) {
    this.lastSalveMillis = lastSalveMillis;
  }
  
  public void setNextSalveMillis(long nextSalveMillis) {
    this.nextSalveMillis = nextSalveMillis;
  }
  
  public void setPlayerUniqueId(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
  }
  
  public void setCurrentSalve(int currentSalve) {
    this.currentSalve = currentSalve;
  }
  
  public void setCurrentMaxSalve(int currentMaxSalve) {
    this.currentMaxSalve = currentMaxSalve;
  }
  
  public static final long EXPIRATION_DELAY = TimeUnit.MINUTES.toMillis(20L);
  
  public static final long MIN_SALVE_DELAY = TimeUnit.MINUTES.toMillis(2L);
  
  public static final long MAX_SALVE_DELAY = TimeUnit.MINUTES.toMillis(5L);
  
  public static final long HIT_DELAY = TimeUnit.SECONDS.toMillis(1L);
  
  public static final int MAX_SALVE = 7;
  
  public static final int MIN_SALVE = 1;
  
  private static final int NONE = -1;
  
  private long creationMillis;
  
  private long lastHitMillis;
  
  private long lastSalveMillis;
  
  private long nextSalveMillis;
  
  private UUID playerUniqueId;
  
  private int currentSalve;
  
  private int currentMaxSalve;
  
  public long getCreationMillis() {
    return this.creationMillis;
  }
  
  public long getLastHitMillis() {
    return this.lastHitMillis;
  }
  
  public long getLastSalveMillis() {
    return this.lastSalveMillis;
  }
  
  public long getNextSalveMillis() {
    return this.nextSalveMillis;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getCurrentSalve() {
    return this.currentSalve;
  }
  
  public int getCurrentMaxSalve() {
    return this.currentMaxSalve;
  }
  
  public StormData(EntityPlayerMP player) {
    this.creationMillis = System.currentTimeMillis();
    this.lastHitMillis = System.currentTimeMillis();
    this.playerUniqueId = player.func_110124_au();
    pickupRandomSalve(new Random());
  }
  
  private boolean canHandleSalve(long now) {
    return (now - this.lastSalveMillis >= this.nextSalveMillis);
  }
  
  private boolean canHit(long now) {
    return (now - this.lastHitMillis >= HIT_DELAY);
  }
  
  private void pickupRandomSalve(Random random) {
    this.currentMaxSalve = random.nextInt(6) + 1;
    this.currentSalve = 0;
  }
  
  private void pickupRandomDelay(Random random) {
    this.nextSalveMillis = random.nextInt((int)(MAX_SALVE_DELAY - MIN_SALVE_DELAY)) + MIN_SALVE_DELAY;
  }
  
  public void handleSalve() {
    long now = System.currentTimeMillis();
    if (!canHandleSalve(now))
      return; 
    if (!canHit(now))
      return; 
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.playerUniqueId);
    if (player == null)
      return; 
    fire(player.field_70170_p, now, player.field_70165_t, player.field_70163_u, player.field_70161_v);
  }
  
  public void fire(World world, long now, double x, double y, double z) {
    double randomX = Math.random() * 5.0D - 2.5D;
    double randomZ = Math.random() * 5.0D - 2.5D;
    world.func_72942_c((Entity)new EntityLightningBolt(world, x + randomX, y - 1.0D, z + randomZ));
    this.lastHitMillis = now;
    this.currentSalve++;
    if (this.currentSalve >= this.currentMaxSalve) {
      this.lastSalveMillis = now;
      pickupRandomSalve(world.field_73012_v);
      pickupRandomDelay(world.field_73012_v);
    } 
  }
  
  public boolean isExpired() {
    return (System.currentTimeMillis() - this.creationMillis >= EXPIRATION_DELAY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\StormData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
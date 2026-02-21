package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCHydrationPacket;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;

public class HydrationData {
  public static final int MAX_HYDRATION = 100;
  
  public static final int MIN_HYDRATION = 0;
  
  public static final int HYDRATION_PER_SECOND = 1;
  
  public static final int WATER_BOTTLE_HYDRATION = 20;
  
  public void setCreationMillis(long creationMillis) {
    this.creationMillis = creationMillis;
  }
  
  public void setLastHitMillis(long lastHitMillis) {
    this.lastHitMillis = lastHitMillis;
  }
  
  public void setPlayerUniqueId(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
  }
  
  public void setCurrentHydration(int currentHydration) {
    this.currentHydration = currentHydration;
  }
  
  public static final long HYDRATION_HIT_DELAY = TimeUnit.SECONDS.toMillis(1L);
  
  public static final long EXPIRATION_DELAY = TimeUnit.MINUTES.toMillis(5L);
  
  private long creationMillis;
  
  private long lastHitMillis;
  
  private UUID playerUniqueId;
  
  private int currentHydration;
  
  public long getCreationMillis() {
    return this.creationMillis;
  }
  
  public long getLastHitMillis() {
    return this.lastHitMillis;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getCurrentHydration() {
    return this.currentHydration;
  }
  
  public HydrationData() {
    this.creationMillis = System.currentTimeMillis();
    this.lastHitMillis = System.currentTimeMillis();
    this.playerUniqueId = UUID.randomUUID();
    this.currentHydration = 100;
  }
  
  public HydrationData(long creationMillis, long lastHitMillis, UUID playerUniqueId, int currentHydration) {
    this.creationMillis = creationMillis;
    this.lastHitMillis = lastHitMillis;
    this.playerUniqueId = playerUniqueId;
    this.currentHydration = currentHydration;
  }
  
  public HydrationData(EntityPlayer player) {
    this.creationMillis = System.currentTimeMillis();
    this.lastHitMillis = System.currentTimeMillis();
    this.playerUniqueId = player.func_110124_au();
    this.currentHydration = 100;
  }
  
  public boolean isExpired() {
    return (System.currentTimeMillis() - this.creationMillis >= EXPIRATION_DELAY);
  }
  
  public boolean canHit() {
    return (System.currentTimeMillis() - this.lastHitMillis >= HYDRATION_HIT_DELAY);
  }
  
  public void hydrate(EntityPlayerMP player, int value) {
    int tmp = this.currentHydration + value;
    if (tmp > 100)
      tmp = 100; 
    this.currentHydration = tmp;
    PalaMod.network.sendTo((IMessage)new SCHydrationPacket(this), player);
  }
  
  public void dehydrate(EntityPlayerMP player) {
    if (player == null)
      return; 
    if (player.func_70090_H())
      return; 
    int tmp = this.currentHydration - 1;
    if (tmp < 0)
      tmp = 0; 
    this.currentHydration = tmp;
    if (canHit() && this.currentHydration == 0) {
      this.lastHitMillis = System.currentTimeMillis();
      player.func_70097_a(DamageSource.field_76366_f, 0.5F);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\HydrationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
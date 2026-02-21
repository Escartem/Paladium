package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects;

import fr.paladium.palajobs.utils.forge.location.Location;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;

public class SoundedLocation {
  public static final long DURATION_MILLIS = TimeUnit.SECONDS.toMillis(5L);
  
  private final UUID senderUniqueId;
  
  private final SoundType type;
  
  private final Location location;
  
  private final long expirationMillis;
  
  private boolean spawned;
  
  public UUID getSenderUniqueId() {
    return this.senderUniqueId;
  }
  
  public SoundType getType() {
    return this.type;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public void setSpawned(boolean spawned) {
    this.spawned = spawned;
  }
  
  public boolean isSpawned() {
    return this.spawned;
  }
  
  public SoundedLocation(EntityPlayer player, SoundType type, Location location) {
    this.senderUniqueId = player.func_110124_au();
    this.type = type;
    this.location = location;
    this.expirationMillis = System.currentTimeMillis() + DURATION_MILLIS;
    this.spawned = false;
  }
  
  public boolean isExpired() {
    return (System.currentTimeMillis() >= this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\objects\SoundedLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
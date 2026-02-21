package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.TimeIsMoneyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class FreezeData {
  public static final long DEFAULT_EXPIRATION = TimeUnit.SECONDS.toMillis(60L);
  
  public static final long DELIVER_TIME = TimeUnit.SECONDS.toMillis(90L);
  
  private long expirationMillis;
  
  private long creationMillis;
  
  private DoubleLocation location;
  
  private boolean frozen;
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public long getCreationMillis() {
    return this.creationMillis;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public boolean isFrozen() {
    return this.frozen;
  }
  
  public FreezeData(EntityPlayerMP player) {
    this(player, true, System.currentTimeMillis() + DEFAULT_EXPIRATION);
  }
  
  public FreezeData(EntityPlayerMP player, boolean frozen, long expirationMillis) {
    this.location = new DoubleLocation((Entity)player);
    this.expirationMillis = expirationMillis;
    this.creationMillis = System.currentTimeMillis();
    this.frozen = frozen;
  }
  
  public boolean isExpired() {
    if (this.expirationMillis == -1L)
      return false; 
    return (System.currentTimeMillis() >= this.expirationMillis);
  }
  
  public boolean canBeDelivered() {
    if (this.expirationMillis != -1L)
      return false; 
    return (this.creationMillis + DELIVER_TIME <= System.currentTimeMillis());
  }
  
  public void replace(EntityPlayerMP player, boolean message) {
    DoubleLocation currentLocation = new DoubleLocation((Entity)player);
    if (currentLocation.getBlockX() != this.location.getBlockX() || currentLocation.getBlockZ() != this.location.getBlockZ()) {
      player.field_70143_R = 0.0F;
      TeleportUtils.teleport((EntityPlayer)player, this.location.getX(), currentLocation.getY(), this.location.getZ());
      if (message)
        MonthlyUtils.sendMessage((EntityPlayer)player, TimeIsMoneyEvent.ALERT_MESSAGE); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\FreezeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
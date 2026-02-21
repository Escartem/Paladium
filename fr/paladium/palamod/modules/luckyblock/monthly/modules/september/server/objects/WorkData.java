package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.WorkIsHealthEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

public class WorkData {
  public static final long SECOND_EXPIRATION = 30L;
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setLastBreakMillis(long lastBreakMillis) {
    this.lastBreakMillis = lastBreakMillis;
  }
  
  public static final long DEFAULT_EXPIRATION = TimeUnit.SECONDS.toMillis(30L);
  
  public static final long DEFAULT_BREAK_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
  
  public static final float DEFAULT_DAMAGE = 1.0F;
  
  private long expirationMillis;
  
  private long lastBreakMillis;
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public long getLastBreakMillis() {
    return this.lastBreakMillis;
  }
  
  public WorkData(EntityPlayerMP player) {
    long now = System.currentTimeMillis();
    this.expirationMillis = now + DEFAULT_EXPIRATION;
    this.lastBreakMillis = now;
    MonthlyUtils.startTimedHeliosEvent(player, WorkIsHealthEvent.class, TimeUnit.SECONDS.toMillis(30L), System.currentTimeMillis());
  }
  
  public boolean canDamage(EntityPlayerMP player, long now) {
    if (!MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.WORK))
      return false; 
    float health = player.func_110143_aJ() - 1.0F;
    if (health <= 0.0D) {
      player.func_82170_o(PLuckyBlock.WORK.func_76396_c());
      return false;
    } 
    if (now - this.lastBreakMillis < DEFAULT_BREAK_INTERVAL)
      return false; 
    return true;
  }
  
  public void damage(EntityPlayerMP player) {
    player.func_70097_a(DamageSource.field_82727_n, 1.0F);
  }
  
  public boolean isExpired(long now) {
    return (now >= this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\WorkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
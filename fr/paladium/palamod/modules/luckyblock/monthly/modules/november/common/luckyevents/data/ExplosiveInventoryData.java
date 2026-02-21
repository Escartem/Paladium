package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.data;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.ExplosiveInventoryEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEndiumEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ExplosiveInventoryData {
  public static long THROWS_INTERVAL_MILLIS = 1000L;
  
  private final UUID playerUniqueId;
  
  private final long startMillis;
  
  private long lastThrowMillis;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public long getStartMillis() {
    return this.startMillis;
  }
  
  public long getLastThrowMillis() {
    return this.lastThrowMillis;
  }
  
  public ExplosiveInventoryData(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
    this.startMillis = System.currentTimeMillis();
    this.lastThrowMillis = 0L;
  }
  
  public boolean isExpired(long now) {
    return (now - this.startMillis >= ExplosiveInventoryEvent.DURATION_MILLIS);
  }
  
  public boolean canThrow(long now) {
    return (now - this.lastThrowMillis >= THROWS_INTERVAL_MILLIS);
  }
  
  public void throwDynamite(EntityPlayerMP player, long now) {
    if (player == null)
      return; 
    if (!canThrow(now))
      return; 
    if (!EventUtils.canInteract((EntityPlayer)player, 
        
        MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v)))
      return; 
    this.lastThrowMillis = now;
    World world = player.field_70170_p;
    world.func_72838_d((Entity)new DynamiteEndiumEntity(world, (EntityLivingBase)player, 40 + world.field_73012_v
          
          .nextInt(10), DynamiteEntity.DEFAULT));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\data\ExplosiveInventoryData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
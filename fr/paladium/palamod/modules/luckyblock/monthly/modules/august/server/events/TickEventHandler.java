package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.StormEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerBodyDilemmaEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;

public class TickEventHandler {
  private static final long SUMMER_BODY_INTERVAL = TimeUnit.SECONDS.toMillis(10L);
  
  private static final long STORM_INTERVAL = 250L;
  
  private static final long SUNBURN_INTERVAL = TimeUnit.SECONDS.toMillis(10L);
  
  private long lastSummerBodyMillis;
  
  private long lastStormMillis;
  
  private long lastSunburnMillis;
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    long now = System.currentTimeMillis();
    if (isSummerBodyTime(now))
      applySummerBody(now); 
    if (isStormTime(now))
      applyStorm(now); 
    if (isSunburnTime(now))
      applySunburn(now); 
  }
  
  private void applySunburn(long now) {
    this.lastSunburnMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    players.forEach(player -> {
          if (!MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.SUNBURN))
            return; 
          if (MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.SOLAR_CREAM))
            return; 
          player.func_70097_a(DamageSource.field_76370_b, 2.0F);
        });
  }
  
  private void applySummerBody(long now) {
    this.lastSummerBodyMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    players.forEach(player -> {
          PlayerLuckyBlockProperties data = PlayerLuckyBlockProperties.get((EntityPlayer)player);
          if (!SummerBodyDilemmaEvent.INSTANCE.canAddEffect(data))
            return; 
          SummerBodyDilemmaEvent.INSTANCE.addEffect((EntityPlayer)player);
        });
  }
  
  private void applyStorm(long now) {
    this.lastStormMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    players.forEach(player -> {
          PlayerLuckyBlockProperties data = PlayerLuckyBlockProperties.get((EntityPlayer)player);
          if (!StormEvent.INSTANCE.canSpawnParticle(data))
            return; 
          WorldServer worldServer = (WorldServer)player.field_70170_p;
          double x = player.field_70165_t;
          double y = player.field_70163_u;
          double z = player.field_70161_v;
          StormEvent.INSTANCE.spawnParticles(worldServer, x, y + 4.0D, z);
        });
  }
  
  private boolean isSunburnTime(long now) {
    return (now - this.lastSunburnMillis >= SUNBURN_INTERVAL);
  }
  
  private boolean isSummerBodyTime(long now) {
    return (now - this.lastSummerBodyMillis >= SUMMER_BODY_INTERVAL);
  }
  
  private boolean isStormTime(long now) {
    return (now - this.lastStormMillis >= 250L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\events\TickEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
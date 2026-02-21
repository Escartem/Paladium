package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.CareerEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.HabitMakesMonkEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.IsDreamJobEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.PracticeMakesPerfectEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.WorkIsHealthEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.BlackSmithData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.WorkData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.WorkerData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class TickEventHandler {
  private static final long WORK_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
  
  private static final long MONK_INTERVAL = TimeUnit.SECONDS.toMillis(10L);
  
  private static final long WORKER_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
  
  private static final long CAREER_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
  
  private static final long BLACKSMITH_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
  
  private long lastWorkMillis;
  
  private long lastWorkerMillis;
  
  private long lastMonkMillis;
  
  private long lastCareerMillis;
  
  private long lastBlackSmithMillis;
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    long now = System.currentTimeMillis();
    if (isWorkTime(now))
      applyWork(now); 
    if (isMonkTime(now))
      applyMonk(now); 
    if (isWorkerTime(now))
      applyWorker(now); 
    if (isCareerTime(now))
      applyCareer(MinecraftServer.func_71276_C().func_130014_f_(), now); 
    if (isBlackSmithTime(now))
      applyBlackSmith(now); 
  }
  
  private void applyBlackSmith(long now) {
    this.lastBlackSmithMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    PracticeMakesPerfectEvent instance = PracticeMakesPerfectEvent.INSTANCE;
    players.forEach(player -> {
          UUID uniqueId = player.func_110124_au();
          BlackSmithData data = instance.get(uniqueId);
          instance.cleanBlacklistedItems((EntityPlayer)player, data);
          if (data == null)
            return; 
          if (data.isExpired(now)) {
            instance.remove((EntityPlayer)player, uniqueId, data);
            return;
          } 
        });
  }
  
  private void applyCareer(World world, long now) {
    this.lastCareerMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    CareerEvent instance = CareerEvent.INSTANCE;
    instance.removeExpiredDatas(world, players, now);
  }
  
  private void applyWorker(long now) {
    this.lastWorkerMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    IsDreamJobEvent instance = IsDreamJobEvent.INSTANCE;
    instance.getWorkers().removeIf(data -> data.isExpired(now));
    instance.getWorkers().forEach(data -> {
          if (data.canApply(now))
            data.apply(now, players); 
        });
  }
  
  private void applyMonk(long now) {
    this.lastMonkMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    HabitMakesMonkEvent instance = HabitMakesMonkEvent.INSTANCE;
    players.forEach(player -> {
          PlayerLuckyBlockProperties data = PlayerLuckyBlockProperties.get((EntityPlayer)player);
          if (!instance.canAddEffect(data))
            return; 
          instance.addEffect((EntityPlayer)player, data.getMonkType());
        });
  }
  
  private void applyWork(long now) {
    this.lastWorkMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    WorkIsHealthEvent instance = WorkIsHealthEvent.INSTANCE;
    players.forEach(player -> {
          UUID uniqueId = player.func_110124_au();
          WorkData data = instance.get(uniqueId);
          if (data == null)
            return; 
          if (data.isExpired(now)) {
            instance.remove(uniqueId);
            return;
          } 
          if (data.canDamage(player, now))
            data.damage(player); 
        });
  }
  
  private boolean isBlackSmithTime(long now) {
    return (now - this.lastBlackSmithMillis >= BLACKSMITH_INTERVAL);
  }
  
  private boolean isWorkerTime(long now) {
    return (now - this.lastWorkerMillis >= WORKER_INTERVAL);
  }
  
  private boolean isMonkTime(long now) {
    return (now - this.lastMonkMillis >= MONK_INTERVAL);
  }
  
  private boolean isWorkTime(long now) {
    return (now - this.lastWorkMillis >= WORK_INTERVAL);
  }
  
  private boolean isCareerTime(long now) {
    return (now - this.lastCareerMillis >= CAREER_INTERVAL);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\events\TickEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
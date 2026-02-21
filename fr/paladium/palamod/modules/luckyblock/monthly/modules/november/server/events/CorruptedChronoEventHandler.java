package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.data.ChronoData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class CorruptedChronoEventHandler {
  public static CorruptedChronoEventHandler INSTANCE;
  
  public static final long CHECK_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1L);
  
  private List<ChronoData> dataList;
  
  private long lastCheckMillis;
  
  public CorruptedChronoEventHandler() {
    INSTANCE = this;
    this.dataList = new ArrayList<>();
  }
  
  @SubscribeEvent
  public void onDeath(LivingDeathEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
    this.dataList.removeIf(data -> data.getPlayerUniqueId().equals(player.func_110124_au()));
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    long now = System.currentTimeMillis();
    if (!canCheck(now))
      return; 
    this.lastCheckMillis = now;
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    List<ChronoData> validDataList = new ArrayList<>();
    this.dataList.forEach(data -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players);
          if (player == null)
            return; 
          if (data.isExpired()) {
            data.teleport(player);
          } else {
            validDataList.add(data);
          } 
        });
    this.dataList = validDataList;
  }
  
  public void use(EntityPlayerMP player) {
    ChronoData data = new ChronoData(player);
    this.dataList.add(data);
  }
  
  public boolean isWaiting(EntityPlayerMP player) {
    return this.dataList.stream().anyMatch(data -> data.getPlayerUniqueId().equals(player.func_110124_au()));
  }
  
  private boolean canCheck(long now) {
    return (now - this.lastCheckMillis >= CHECK_TIME_MILLIS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\server\events\CorruptedChronoEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
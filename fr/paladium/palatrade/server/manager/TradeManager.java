package fr.paladium.palatrade.server.manager;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.pb.PBManager;
import fr.paladium.palatrade.common.utils.Trade;
import fr.paladium.palatrade.common.utils.TradeRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class TradeManager {
  private static TradeManager instance;
  
  private final Map<UUID, List<TradeRequest>> waitingTrades = new HashMap<>();
  
  private final Map<UUID, Trade> trades = new HashMap<>();
  
  public static TradeManager getInstance() {
    if (instance == null)
      instance = new TradeManager(); 
    return instance;
  }
  
  public boolean isEnable(EntityPlayer player) {
    return player.getEntityData().func_74764_b("palatrade-enable") ? player.getEntityData().func_74767_n("palatrade-enable") : true;
  }
  
  public int getTotalXp(EntityPlayer player) {
    int lvl = player.field_71068_ca;
    int xp = 0;
    for (int i = 0; i < lvl; i++)
      xp += getXpForLevel(i); 
    xp = (int)(xp + getXpForLevel(lvl) * player.field_71106_cc);
    return xp;
  }
  
  private int getXpForLevel(int lvl) {
    return (lvl >= 30) ? (62 + (lvl - 30) * 7) : ((lvl >= 15) ? (17 + (lvl - 15) * 3) : 17);
  }
  
  public List<TradeRequest> getWaitingTrades(EntityPlayer player) {
    List<TradeRequest> requests = this.waitingTrades.getOrDefault(player.func_110124_au(), new ArrayList<>());
    requests.removeIf(request -> (System.currentTimeMillis() - request.getTime() > 300000L));
    return requests;
  }
  
  public TradeRequest generateTradeRequest(EntityPlayer player) {
    return new TradeRequest(player.func_110124_au(), player.func_70005_c_(), System.currentTimeMillis());
  }
  
  public boolean isWaiting(EntityPlayer p1, EntityPlayer p2) {
    return getWaitingTrades(p1).contains(generateTradeRequest(p2));
  }
  
  public boolean sendTradeRequest(EntityPlayer p1, EntityPlayer p2) {
    List<TradeRequest> requests = getWaitingTrades(p2);
    TradeRequest request = generateTradeRequest(p1);
    if (requests.contains(request))
      return false; 
    requests.add(request);
    this.waitingTrades.put(p2.func_110124_au(), requests);
    return true;
  }
  
  public boolean removeTradeRequest(EntityPlayer p1, EntityPlayer p2) {
    List<TradeRequest> requests = getWaitingTrades(p1);
    TradeRequest request = generateTradeRequest(p2);
    if (!requests.contains(request))
      return false; 
    requests.remove(request);
    this.waitingTrades.put(p1.func_110124_au(), requests);
    return true;
  }
  
  public boolean acceptTradeRequest(EntityPlayer p1, EntityPlayer p2) {
    if (!isWaiting(p1, p2) || !removeTradeRequest(p1, p2) || isTrading(p1) || isTrading(p2))
      return false; 
    final Trade t1 = Trade.createEmpty(p1, p2);
    t1.setMaxXp(getTotalXp(p1));
    CresusManager.getInstance().getBalanceAsync(p1.func_110124_au(), new CresusCallback<Double>() {
          public void onSuccess(Double value) {
            t1.setMaxMoney(value.doubleValue());
          }
          
          public void onFail(Double value, Throwable error) {
            error.printStackTrace();
          }
        });
    PBManager.get(UUIDUtils.toString((Entity)p1)).thenAccept(value -> t1.setMaxPb((value.longValue() < 10L) ? 0.0D : value.longValue()));
    final Trade t2 = Trade.createEmpty(p2, p1);
    t2.setMaxXp(getTotalXp(p2));
    CresusManager.getInstance().getBalanceAsync(p2.func_110124_au(), new CresusCallback<Double>() {
          public void onSuccess(Double value) {
            t2.setMaxMoney(value.doubleValue());
          }
          
          public void onFail(Double value, Throwable error) {
            error.printStackTrace();
          }
        });
    PBManager.get(UUIDUtils.toString((Entity)p2)).thenAccept(value -> t2.setMaxPb((value.longValue() < 10L) ? 0.0D : value.longValue()));
    this.trades.put(p1.func_110124_au(), t1);
    this.trades.put(p2.func_110124_au(), t2);
    return true;
  }
  
  public Trade getTrade(EntityPlayer player) {
    if (player == null)
      return null; 
    return this.trades.get(player.func_110124_au());
  }
  
  public boolean isTrading(EntityPlayer player) {
    if (player == null)
      return false; 
    return (getTrade(player) != null);
  }
  
  public EntityPlayer getTradePlayer(EntityPlayer player) {
    Trade trade = getTrade(player);
    if (trade == null)
      return null; 
    for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
      EntityPlayer target = worldServer.func_152378_a(trade.getTarget());
      if (target != null)
        return target; 
    } 
    return null;
  }
  
  public boolean removeTrade(EntityPlayer player) {
    Trade trade = getTrade(player);
    if (trade == null)
      return false; 
    this.trades.remove(trade.getPlayer());
    this.trades.remove(trade.getTarget());
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\server\manager\TradeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
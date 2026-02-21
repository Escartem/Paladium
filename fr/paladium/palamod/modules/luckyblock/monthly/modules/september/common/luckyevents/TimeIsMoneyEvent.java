package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.FreezeData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ServerChatEvent;

public class TimeIsMoneyEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Le temps c'est de l'argent";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 300;
  
  private static final String TEXTURE_PATH = "september/time_is_money";
  
  public static final long NEVER = -1L;
  
  private static final String MONEY = "argent";
  
  private static final String TIME = "temps";
  
  private static final double WITHDRAW_AMOUNT = 300.0D;
  
  public static final String[] ALERT_MESSAGE = new String[] { "&eLe temps c’est de l’argent ! Tu préfères offrir 60 secondes de ton temps ou 300$ de ton argent ?", "&cÉcrivez ARGENT ou TEMPS dans le chat pour choisir !" };
  
  private static final String WITHDRAW_MESSAGE = "&aVous avez choisi de payer 300$ pour ne pas perdre de temps !";
  
  private static final String WAITING_MESSAGE = "&aVous avez choisi de perdre 60 secondes de votre temps !";
  
  private static final String UNSUCCESSFUL_WITHDRAW_MESSAGE = "&cVous n'avez pas assez d'argent pour payer !";
  
  public static TimeIsMoneyEvent INSTANCE;
  
  private final Map<UUID, FreezeData> freezes;
  
  public TimeIsMoneyEvent() {
    INSTANCE = this;
    this.freezes = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, ALERT_MESSAGE);
    FreezeData data = new FreezeData(player, true, -1L);
    this.freezes.put(player.func_110124_au(), data);
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    UUID uniqueId = player.func_110124_au();
    FreezeData data = getFreezeData(uniqueId);
    if (data == null)
      return; 
    if (data.canBeDelivered()) {
      this.freezes.remove(uniqueId);
      return;
    } 
    if (data.isExpired()) {
      this.freezes.remove(uniqueId);
      return;
    } 
    if (data.isFrozen())
      data.replace(player, (data.getExpirationMillis() == -1L)); 
  }
  
  @SubscribeEvent
  public void onPlayerChat(ServerChatEvent event) {
    EntityPlayerMP player = event.player;
    UUID uniqueId = player.func_110124_au();
    if (!shouldHandle(player))
      return; 
    String message = event.message.toLowerCase();
    if (message.equalsIgnoreCase("argent")) {
      removeMoney(player);
      event.setCanceled(true);
    } else if (message.equalsIgnoreCase("temps")) {
      FreezeData newData = new FreezeData(player);
      this.freezes.put(uniqueId, newData);
      MonthlyUtils.startTimedHeliosEvent(player, getClass(), FreezeData.DEFAULT_EXPIRATION, System.currentTimeMillis());
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez choisi de perdre 60 secondes de votre temps !" });
      event.setCanceled(true);
    } else {
      MonthlyUtils.sendMessage((EntityPlayer)player, ALERT_MESSAGE);
    } 
  }
  
  public boolean shouldHandle(EntityPlayerMP player) {
    FreezeData data = getFreezeData(player.func_110124_au());
    if (data == null || data.isExpired())
      return false; 
    if (data.getExpirationMillis() != -1L)
      return false; 
    return true;
  }
  
  public void removeMoney(final EntityPlayerMP player) {
    CresusCallback<CresusResponse> callback = new CresusCallback<CresusResponse>() {
        public void onSuccess(CresusResponse cresusResponse) {
          if (!cresusResponse.transactionSuccess()) {
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
            return;
          } 
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez choisi de payer 300$ pour ne pas perdre de temps !" });
          TimeIsMoneyEvent.this.freezes.remove(player.func_110124_au());
        }
        
        public void onFail(CresusResponse cresusResponse, Throwable throwable) {
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
        }
      };
    CresusManager.getInstance().withdrawPlayerAsync(player
        .func_110124_au(), 300.0D, "Le temps c'est de l'argent", callback);
  }
  
  public FreezeData getFreezeData(UUID uniqueId) {
    return this.freezes.get(uniqueId);
  }
  
  public String getName() {
    return "Le temps c'est de l'argent";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "september/time_is_money";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Ton temps t'es pris!" };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\TimeIsMoneyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
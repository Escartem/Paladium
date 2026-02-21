package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.VitalSpaceData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;

public class VitalSpaceEvent extends ATickable<HashSet<VitalSpaceData>> implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "L'espace vital";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 120;
  
  private static final String TEXTURE_PATH = "march/vital_space";
  
  public static final long DURATION_MILLIS = TimeUnit.MINUTES.toMillis(5L);
  
  private static final String[] HELIOS_MESSAGES = new String[] { "§eÉvites les autres joueurs à tout prix !" };
  
  private static VitalSpaceEvent instance;
  
  public static VitalSpaceEvent getInstance() {
    return instance;
  }
  
  public VitalSpaceEvent() {
    super(new HashSet(), 250L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    add(player);
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), DURATION_MILLIS);
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    ((HashSet)this.data).forEach(data -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players);
          if (player == null)
            return; 
          data.perform(player);
          if (data.isExpired(now)) {
            data.expire(player);
            return;
          } 
        });
    ((HashSet)this.data).removeIf(data -> data.isExpired(now));
  }
  
  public String getName() {
    return "L'espace vital";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 120;
  }
  
  public String getTexture() {
    return "march/vital_space";
  }
  
  public void add(EntityPlayerMP player) {
    ((HashSet)this.data).removeIf(data -> data.getPlayerUniqueId().equals(player.func_110124_au()));
    ((HashSet<VitalSpaceData>)this.data).add(new VitalSpaceData(player));
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return HELIOS_MESSAGES;
  }
  
  public String getAction() {
    return "Temps restant:";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\VitalSpaceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
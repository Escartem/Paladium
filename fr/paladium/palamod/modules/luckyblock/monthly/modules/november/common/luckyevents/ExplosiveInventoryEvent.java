package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.data.ExplosiveInventoryData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ExplosiveInventoryEvent extends ATickable<HashSet<ExplosiveInventoryData>> implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Inventaire explosif";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 200;
  
  private static final String TEXTURE_PATH = "november/explosive_inventory";
  
  public static final long DURATION_MILLIS = TimeUnit.MINUTES.toMillis(5L);
  
  private static final String PERFORM_MESSAGE = "§cJe te déconseille fortement d’ouvrir ton inventaire pendant quelque temps.";
  
  private static final String[] HELIOS_MESSAGES = new String[] { "§eAttention, ton inventaire est explosif !" };
  
  private static ExplosiveInventoryEvent instance;
  
  public static ExplosiveInventoryEvent getInstance() {
    return instance;
  }
  
  public ExplosiveInventoryEvent() {
    super(new HashSet(), 1000L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cJe te déconseille fortement d’ouvrir ton inventaire pendant quelque temps." });
    ExplosiveInventoryData data = new ExplosiveInventoryData(player.func_110124_au());
    ((HashSet<ExplosiveInventoryData>)this.data).add(data);
    MonthlyUtils.startTimedHeliosEvent(player, ExplosiveInventoryEvent.class, DURATION_MILLIS);
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    ((HashSet)this.data).removeIf(d -> d.isExpired(now));
  }
  
  public void throwDynamite(EntityPlayerMP playerMP, long now) {
    ExplosiveInventoryData data = ((HashSet<ExplosiveInventoryData>)this.data).stream().filter(d -> d.getPlayerUniqueId().equals(playerMP.func_110124_au())).findFirst().orElse(null);
    if (data == null)
      return; 
    data.throwDynamite(playerMP, now);
  }
  
  public String getName() {
    return "Inventaire explosif";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public String getTexture() {
    return "november/explosive_inventory";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return HELIOS_MESSAGES;
  }
  
  public String getAction() {
    return "Temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\ExplosiveInventoryEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
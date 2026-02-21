package fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework;

import fr.paladium.palamod.modules.luckyblock.data.event.SoftLikeLambData;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SoftLikeALambEvent extends ATickable<HashSet<SoftLikeLambData>> implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Doux comme un agneau";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 90;
  
  private static final String TEXTURE_PATH = "easter/soft_like_a_lamb";
  
  private static final String ACTIVATE_MESSAGE = "Soit doux comme un agneau et n’inflige pas de dégâts pendant 30min pour obtenir une récompense.";
  
  private static SoftLikeALambEvent instance;
  
  public static SoftLikeALambEvent getInstance() {
    return instance;
  }
  
  public SoftLikeALambEvent() {
    super(new HashSet(), 500L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Soit doux comme un agneau et n’inflige pas de dégâts pendant 30min pour obtenir une récompense." });
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), SoftLikeLambData.DURATION);
    ((HashSet<SoftLikeLambData>)this.data).add(new SoftLikeLambData(player));
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    ((HashSet)this.data).forEach(data -> {
          if (data.isExpired(now))
            data.handleFinish(MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players)); 
        });
    ((HashSet)this.data).removeIf(data -> data.isExpired(now));
  }
  
  public SoftLikeLambData getData(EntityPlayerMP player) {
    return ((HashSet<SoftLikeLambData>)this.data).stream()
      .filter(data -> data.getPlayerUniqueId().equals(player.func_110124_au()))
      .findFirst()
      .orElse(null);
  }
  
  public String getName() {
    return "Doux comme un agneau";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 90;
  }
  
  public String getTexture() {
    return "easter/soft_like_a_lamb";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Soit doux comme un agneau" };
  }
  
  public String getAction() {
    return "Temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\rework\SoftLikeALambEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
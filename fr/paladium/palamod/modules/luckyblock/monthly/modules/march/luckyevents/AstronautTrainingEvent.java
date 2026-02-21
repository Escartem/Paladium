package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.AstronautTrainingData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class AstronautTrainingEvent extends ATickable<HashSet<AstronautTrainingData>> implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Entrainement d'astronaute";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 130;
  
  private static final String TEXTURE_PATH = "march/astronaut_training";
  
  private static AstronautTrainingEvent instance;
  
  public static AstronautTrainingEvent getInstance() {
    return instance;
  }
  
  public AstronautTrainingEvent() {
    super(new HashSet(), 200L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ((HashSet<AstronautTrainingData>)this.data).add(new AstronautTrainingData(player));
    MonthlyUtils.startHeliosEvent(getClass(), player);
  }
  
  protected void onTick(long now) {
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    ((HashSet)this.data).forEach(data -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players);
          data.perform(player, now);
        });
    ((HashSet)this.data).removeIf(AstronautTrainingData::isExpired);
  }
  
  public String getName() {
    return "Entrainement d'astronaute";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "march/astronaut_training";
  }
  
  public boolean isTimerType() {
    return false;
  }
  
  public String[] getDescription() {
    return new String[] { "Soyez prêt à sauter le plus haut possible !" };
  }
  
  public String getAction() {
    return "";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\AstronautTrainingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
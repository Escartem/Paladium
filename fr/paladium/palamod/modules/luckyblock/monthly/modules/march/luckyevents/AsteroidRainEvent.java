package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.AsteroidRainData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class AsteroidRainEvent extends ATickable<HashSet<AsteroidRainData>> {
  private static final String EVENT_NAME = "Pluie d'astéroïdes";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 90;
  
  private static final String TEXTURE_PATH = "march/asteroid_rain";
  
  private static AsteroidRainEvent instance;
  
  public static AsteroidRainEvent getInstance() {
    return instance;
  }
  
  public AsteroidRainEvent() {
    super(new HashSet(), 500L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ((HashSet<AsteroidRainData>)this.data).add(new AsteroidRainData(player));
  }
  
  protected void onTick(long now) {
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    ((HashSet)this.data).forEach(data -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players);
          data.perform(player, now);
        });
    ((HashSet)this.data).removeIf(AsteroidRainData::isExpired);
  }
  
  public String getName() {
    return "Pluie d'astéroïdes";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 90;
  }
  
  public String getTexture() {
    return "march/asteroid_rain";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\AsteroidRainEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
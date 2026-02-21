package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.RocketData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class LikeARocketEvent extends ATickable<HashSet<RocketData>> {
  private static final String EVENT_NAME = "Comme une fusée";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 700;
  
  private static final String TEXTURE_PATH = "march/like_a_rocket";
  
  private static LikeARocketEvent instance;
  
  public static LikeARocketEvent getInstance() {
    return instance;
  }
  
  public LikeARocketEvent() {
    super(new HashSet(), 500L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    RocketData rocketData = new RocketData(player);
    rocketData.playSound(player);
    ((HashSet<RocketData>)this.data).add(rocketData);
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    ((HashSet)this.data).forEach(data -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players);
          if (data.isExpired(now))
            data.giveItem(player); 
        });
    ((HashSet)this.data).removeIf(data -> data.isExpired(now));
  }
  
  public String getName() {
    return "Comme une fusée";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "march/like_a_rocket";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\LikeARocketEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data.BestFriendData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class MeilleurAmi extends ATickable<HashSet<BestFriendData>> implements IHeliosLuckyEventWidget {
  private static MeilleurAmi instance;
  
  public static MeilleurAmi getInstance() {
    return instance;
  }
  
  public MeilleurAmi() {
    super(new HashSet(), 500L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), BestFriendData.START_EXPIRE_DURATION);
    ((HashSet<BestFriendData>)this.data).add(new BestFriendData(player));
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    List<EntityPlayerMP> players = MonthlyUtils.getOnlinePlayers();
    ((HashSet)this.data).removeIf(friend -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(friend.getPlayerUniqueId(), players);
          return friend.isExpired(player, now);
        });
  }
  
  public String getName() {
    return "Meilleur ami";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public String getTexture() {
    return "may/meilleur_ami";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Place toi à proximité d’un joueur et reste proche de lui." };
  }
  
  public String getAction() {
    return "Temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\MeilleurAmi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
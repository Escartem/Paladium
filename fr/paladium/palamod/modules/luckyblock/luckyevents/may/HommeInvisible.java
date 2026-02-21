package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data.InvisibleData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class HommeInvisible extends ATickable<HashSet<InvisibleData>> implements IHeliosLuckyEventWidget {
  private static HommeInvisible instance;
  
  public static HommeInvisible getInstance() {
    return instance;
  }
  
  public HommeInvisible() {
    super(new HashSet(), 1000L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Sois invisible comme un fantôme pour obtenir une récompense ! Si tu n’es pas vu pendant 15 minutes tu obtiendras une récompense." });
    ((HashSet<InvisibleData>)this.data).add(new InvisibleData(player));
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), InvisibleData.DURATION);
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    ((HashSet)this.data).removeIf(lavaData -> lavaData.isExpired(MonthlyUtils.getPlayer(lavaData.getPlayerUniqueId()), now));
  }
  
  public String getName() {
    return "L'homme invisible";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 210;
  }
  
  public String getTexture() {
    return "may/homme_invisible";
  }
  
  public String[] getDescription() {
    return new String[] { "Reste invisible pour obtenir une récompense" };
  }
  
  public String getAction() {
    return "Temps restant :";
  }
  
  public boolean isTimerType() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\HommeInvisible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
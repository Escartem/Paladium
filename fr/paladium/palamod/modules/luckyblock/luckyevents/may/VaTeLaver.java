package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data.LavaData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class VaTeLaver extends ATickable<HashSet<LavaData>> implements IHeliosLuckyEventWidget {
  private static VaTeLaver instance;
  
  public static VaTeLaver getInstance() {
    return instance;
  }
  
  private static final HashSet<LavaData> DATA = new HashSet<>();
  
  public VaTeLaver() {
    super(null, 1000L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Va vite te baigner dans la lave !" });
    DATA.add(new LavaData(player));
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), LavaData.DURATION);
  }
  
  protected void onTick(long now) {
    if (DATA.isEmpty())
      return; 
    DATA.removeIf(lavaData -> {
          EntityPlayerMP player = MonthlyUtils.getPlayer(lavaData.getPlayerUniqueId());
          return lavaData.isExpired(player, now);
        });
  }
  
  public String getName() {
    return "Va te laver !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 170;
  }
  
  public String getTexture() {
    return "may/va_te_laver";
  }
  
  public String[] getDescription() {
    return new String[] { "Rentre en contact avec de la lave." };
  }
  
  public String getAction() {
    return "Temps restant :";
  }
  
  public boolean isTimerType() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\VaTeLaver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
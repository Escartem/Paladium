package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class SunburnEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Coup de soleil";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "august/sunburn";
  
  public static final int DURATION = 2400;
  
  private static final String ALERT_MESSAGE = "&eVous avez attrapé un coup de soleil ! Utilisez de la &dcrème solaire &epour vous soigner !";
  
  public static SunburnEvent INSTANCE;
  
  public SunburnEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous avez attrapé un coup de soleil ! Utilisez de la &dcrème solaire &epour vous soigner !" });
    player.func_70690_d(getPotionEffect());
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), TimeUnit.MINUTES
        .toMillis(2L), System.currentTimeMillis());
  }
  
  public PotionEffect getPotionEffect() {
    PotionEffect effect = new PotionEffect(PLuckyBlock.SUNBURN.func_76396_c(), 2400, 0, false);
    effect.getCurativeItems().clear();
    return effect;
  }
  
  public String getName() {
    return "Coup de soleil";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "august/sunburn";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "On dirait que tu n'as pas mis de crème solaire, tu es tout rouge !" };
  }
  
  public String getAction() {
    return "temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SunburnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
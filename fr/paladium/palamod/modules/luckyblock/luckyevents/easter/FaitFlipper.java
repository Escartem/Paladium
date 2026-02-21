package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class FaitFlipper extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.SECONDS.toMillis(60L));
    PotionEffect effect = new PotionEffect(PLuckyBlock.FLIP.field_76415_H, 1200);
    effect.setCurativeItems(new ArrayList());
    player.func_70690_d(effect);
  }
  
  public String getName() {
    return "Ça fait flipper";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public String getTexture() {
    return "easter/fait_flipper";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Ton écran est retourné" };
  }
  
  public String getAction() {
    return "Temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\FaitFlipper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
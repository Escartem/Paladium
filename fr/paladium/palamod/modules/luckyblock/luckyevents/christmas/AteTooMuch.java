package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AteTooMuch extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int ticks = 900;
    player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, ticks, 0));
    player.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, ticks, 0));
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.SECONDS.toMillis((ticks / 20)));
  }
  
  public String getName() {
    return "Trop mangé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "ate_too_much";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tu as trop mangé et tu te sens mal." };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\AteTooMuch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
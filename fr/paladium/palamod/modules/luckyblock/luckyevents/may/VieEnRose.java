package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class VieEnRose extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PotionsRegister.PINK_EFFECT.getPotionId(), 6000));
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aVois la vie en rose jusqu’au bout pour obtenir une récompense." });
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.MINUTES.toMillis(5L));
  }
  
  public String getName() {
    return "La vie en rose";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 180;
  }
  
  public String getTexture() {
    return "may/vie_en_rose";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Voit la vie en rose jusqu’au bout pour obtenir une récompense. " };
  }
  
  public String getAction() {
    return "Temps restant :";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\VieEnRose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
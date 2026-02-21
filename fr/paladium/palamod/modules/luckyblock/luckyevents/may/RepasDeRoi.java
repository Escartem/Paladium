package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.entity.EntityPlayerKing;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class RepasDeRoi extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public static final long DURATION = TimeUnit.MINUTES.toMillis(2L);
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityPlayerKing king = new EntityPlayerKing((EntityPlayer)player, x, y, z, DURATION);
    king.spawn(player.field_70170_p);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aLe Roi de la galette est apparu ! Jetez lui 8 galettes pour obtenir une récompense." });
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), DURATION);
  }
  
  public String getName() {
    return "Un repas de roi";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "may/repas_de_roi";
  }
  
  public String[] getDescription() {
    return new String[] { "Amène 8 Galette des Rois", "au Roi de la galette." };
  }
  
  public String getAction() {
    return "Temps restant :";
  }
  
  public boolean isTimerType() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\RepasDeRoi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
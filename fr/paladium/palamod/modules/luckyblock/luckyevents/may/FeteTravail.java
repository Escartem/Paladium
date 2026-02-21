package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FeteTravail extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public static final int DURATION = 1800000;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!PlayerLuckyBlockProperties.get((EntityPlayer)player).isLaborDayActive()) {
      PlayerLuckyBlockProperties.get((EntityPlayer)player).setLaborDayActive(true);
      long startTime = System.currentTimeMillis();
      PlayerLuckyBlockProperties.get((EntityPlayer)player).setLaborDayDate(startTime);
      int tickDuration = 36000;
      player.func_70690_d(new PotionEffect(Potion.field_76443_y.func_76396_c(), tickDuration));
      ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(getClass().getName(), 1800000L, startTime), player);
      PlayerUtils.sendMessage((EntityPlayer)player, "C'est la fête du travail et tu ne dois donc pas travailler ! N'obtiens pas d’expérience métier pendant 30 minutes pour avoir une récompense.");
    } else {
      PlayerUtils.sendMessage((EntityPlayer)player, "Cet event est déjà en cours.");
    } 
  }
  
  public String getName() {
    return "Fête du travail";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 750;
  }
  
  public String getTexture() {
    return "may/fete_travail";
  }
  
  public String[] getDescription() {
    return new String[] { "Ne gagne pas d'expérience", "de métier pendant 30 minutes." };
  }
  
  public String getAction() {
    return "Temps restant :";
  }
  
  public boolean isTimerType() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\FeteTravail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
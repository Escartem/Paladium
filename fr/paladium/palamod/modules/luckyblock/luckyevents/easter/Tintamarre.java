package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketEasterTintamarre;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;

public class Tintamarre extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.MINUTES.toMillis(2L));
    PalaMod.getNetwork().sendTo((IMessage)new PacketEasterTintamarre(), player);
  }
  
  public String getName() {
    return "Tintamarre";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public String getTexture() {
    return "easter/tintamarre";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Les cloches vont bientôt se taire" };
  }
  
  public String getAction() {
    return "Temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\Tintamarre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.events;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;

class null implements CresusCallback<Boolean> {
  public void onSuccess(Boolean arg0) {
    if (arg0.booleanValue() == true) {
      Random r = player.field_70170_p.field_73012_v;
      if (r.nextBoolean()) {
        CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), credit, "onConsumerCredit", new CresusCallback<CresusResponse>() {
              public void onSuccess(CresusResponse arg0) {
                PlayerUtils.sendMessage(player, "§aVoilà votre virement");
                UsersManager.getConsumerCredit().remove(player);
              }
              
              public void onFail(CresusResponse arg0, Throwable arg1) {}
            });
      } else {
        CresusManager.getInstance().withdrawPlayerAsync(player.func_110124_au(), credit, "onConsumerCredit", new CresusCallback<CresusResponse>() {
              public void onSuccess(CresusResponse arg0) {
                PlayerUtils.sendMessage(player, "§cLes intérêts de la banque étaient un peu trop élevés, ils vous ont débités");
                UsersManager.getConsumerCredit().remove(player);
              }
              
              public void onFail(CresusResponse arg0, Throwable arg1) {}
            });
      } 
    } else {
      PlayerUtils.sendMessage(player, "§cVous n'avez pas assez d'argent.");
    } 
  }
  
  public void onFail(Boolean arg0, Throwable arg1) {
    PlayerUtils.sendMessage(player, "§cVous n'avez pas assez d'argent.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
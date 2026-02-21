package fr.paladium.palamod.modules.luckyblock.events;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    PlayerUtils.sendMessage(player, "§aVoilà votre virement");
    UsersManager.getConsumerCredit().remove(player);
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$7$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
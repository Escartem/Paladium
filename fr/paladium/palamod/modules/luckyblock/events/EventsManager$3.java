package fr.paladium.palamod.modules.luckyblock.events;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;

class null implements CresusCallback<CresusResponse> {
  public void onFail(CresusResponse response, Throwable error) {
    PlayerUtils.sendMessage(player, "§cUne erreur est survenue");
  }
  
  public void onSuccess(CresusResponse response) {
    PlayerUtils.sendMessage(player, "§aVous venez de gagner §e1000$");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
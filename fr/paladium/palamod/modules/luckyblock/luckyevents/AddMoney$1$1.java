package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    PlayerUtils.sendMessage((EntityPlayer)player, "§aVous venez de gagner §e" + (int)toDeposit + "$");
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\AddMoney$1$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
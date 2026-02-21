package fr.paladium.palatrade.common.network;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palatrade.common.utils.Trade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse response) {
    CresusManager.getInstance().depositPlayer(target.func_110124_au(), trade.getMoney(), "Trade -> " + player.func_70005_c_() + " (" + player.func_110124_au().toString() + ")");
  }
  
  public void onFail(CresusResponse response, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\CSValidateTradePacket$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
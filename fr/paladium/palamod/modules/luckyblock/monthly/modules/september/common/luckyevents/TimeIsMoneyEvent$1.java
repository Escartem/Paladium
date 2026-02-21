package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse cresusResponse) {
    if (!cresusResponse.transactionSuccess()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
      return;
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez choisi de payer 300$ pour ne pas perdre de temps !" });
    TimeIsMoneyEvent.access$000(TimeIsMoneyEvent.this).remove(player.func_110124_au());
  }
  
  public void onFail(CresusResponse cresusResponse, Throwable throwable) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\TimeIsMoneyEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
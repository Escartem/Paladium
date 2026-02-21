package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse cresusResponse) {
    if (!cresusResponse.transactionSuccess()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
      return;
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&aVous venez de payer %s$ !", new Object[] { Double.valueOf(this.val$amount) }) });
    SandmanDialogManager.this.handleTask(player, entity, index);
  }
  
  public void onFail(CresusResponse cresusResponse, Throwable throwable) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\SandmanDialogManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
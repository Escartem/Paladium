package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&6%s $ &aont été retirés de votre compte en banque.", new Object[] { Double.valueOf(this.val$amount) }) });
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\GoodPayEvent$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
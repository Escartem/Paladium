package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    MonthlyUtils.sendMessage((EntityPlayer)killerMP, new String[] { String.format("&eIl y avait une prime sur la tête de &d%s &e, vous venew de gagner &63000.0 &e$ !", new Object[] { this.val$target.func_70005_c_() }) });
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {
    MonthlyUtils.sendMessage((EntityPlayer)killerMP, new String[] { "&cUne erreur est survenue lors du transfert de la prime." });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\FinallyABonusEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
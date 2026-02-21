package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous venez de recevoir 7500$." });
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\dialog\ShootingStarDialogManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
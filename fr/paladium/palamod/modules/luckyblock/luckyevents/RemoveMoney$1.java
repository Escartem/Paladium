package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<Double> {
  public void onFail(Double arg0, Throwable arg1) {}
  
  public void onSuccess(Double arg0) {
    final double toDeposit = arg0.doubleValue() / 10.0D;
    CresusManager.getInstance().withdrawPlayerAsync(player.func_110124_au(), toDeposit, "ALuckyEvent RemoveMoney", new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse arg0) {
            PlayerUtils.sendMessage((EntityPlayer)player, "§cVous venez de perdre §e" + (int)toDeposit + "$");
          }
          
          public void onFail(CresusResponse arg0, Throwable arg1) {}
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\RemoveMoney$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
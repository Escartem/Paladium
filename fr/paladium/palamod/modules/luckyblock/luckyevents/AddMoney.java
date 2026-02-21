package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AddMoney extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    CresusManager.getInstance().getBalanceAsync(player.func_110124_au(), new CresusCallback<Double>() {
          public void onFail(Double arg0, Throwable arg1) {}
          
          public void onSuccess(Double arg0) {
            final double toDeposit = arg0.doubleValue() / 10.0D;
            CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), toDeposit, "ALuckyEvent AddMoney", new CresusCallback<CresusResponse>() {
                  public void onSuccess(CresusResponse arg0) {
                    PlayerUtils.sendMessage((EntityPlayer)player, "§aVous venez de gagner §e" + (int)toDeposit + "$");
                  }
                  
                  public void onFail(CresusResponse arg0, Throwable arg1) {}
                });
          }
        });
  }
  
  public String getName() {
    return "Et paf + de sous";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 5000;
  }
  
  public String getTexture() {
    return "add_money";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\AddMoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
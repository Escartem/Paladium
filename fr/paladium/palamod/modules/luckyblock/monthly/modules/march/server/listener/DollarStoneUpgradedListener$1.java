package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    player.func_146105_b((IChatComponent)new ChatComponentText("§aVous venez de récupérer " + winningAmount + "$ avec votre Dollar Stone améliorée."));
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Dollar Stone§8] §6Impossible de récupérer §e" + winningAmount + "$"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\listener\DollarStoneUpgradedListener$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
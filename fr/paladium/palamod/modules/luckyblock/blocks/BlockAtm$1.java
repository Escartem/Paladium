package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez récupéré §e" + money + "$."));
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {
    new ChatComponentText("§cUne erreur est survenue lors de la récupération de §e" + money + "$.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockAtm$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
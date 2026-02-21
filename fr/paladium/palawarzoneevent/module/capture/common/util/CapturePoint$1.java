package fr.paladium.palawarzoneevent.module.capture.common.util;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onFail(CresusResponse response, Throwable throwable) {
    player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§cUne erreur est survenue lors du gain de money."));
  }
  
  public void onSuccess(CresusResponse response) {
    player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§a" + response.amount + "$ ont été ajouté à votre solde."));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\commo\\util\CapturePoint$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
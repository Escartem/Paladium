package fr.paladium.palamod.modules.luckyblock.events;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.events.EditSignEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    event.player
      .func_146105_b((IChatComponent)new ChatComponentText("Bien joué ! Voilà 10000$."));
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
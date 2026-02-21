package fr.paladium.palamod.modules.end.server.command;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import net.minecraft.command.ICommandSender;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned planned) {
    EndManager.getInstance().stop(null);
  }
  
  public void onFail(Planned planned, Throwable error) {
    error.printStackTrace();
    CommandEnd.access$000(CommandEnd.this, sender, "§cImpossible d'arrêter l'event.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\command\CommandEnd$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
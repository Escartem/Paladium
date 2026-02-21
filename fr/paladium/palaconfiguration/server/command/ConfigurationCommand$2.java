package fr.paladium.palaconfiguration.server.command;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<Void> {
  public void onResponse(Call<Void> call, Response<Void> response) {
    if (!response.isSuccessful()) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors du rechargement des fichiers" });
      return;
    } 
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§aLes fichiers ont été rechargés avec succès" });
  }
  
  public void onFailure(Call<Void> call, Throwable t) {
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors du rechargement des fichiers" });
    t.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\command\ConfigurationCommand$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
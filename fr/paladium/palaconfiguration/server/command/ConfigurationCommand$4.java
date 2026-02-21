package fr.paladium.palaconfiguration.server.command;

import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<Map<String, RemoteObject>> {
  public void onResponse(Call<Map<String, RemoteObject>> call, Response<Map<String, RemoteObject>> response) {
    if (!response.isSuccessful() || response.body() == null) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
      return;
    } 
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Liste des fichiers disponibles :" });
    ((Map)response.body()).values().forEach(file -> ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §e" + file.getPath() + " §7(§d" + (file.isDirectory() ? "Dossier" : "Fichier") + "§7)" }));
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
  }
  
  public void onFailure(Call<Map<String, RemoteObject>> call, Throwable t) {
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
    t.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\command\ConfigurationCommand$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
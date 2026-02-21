package fr.paladium.palaconfiguration.server.command;

import fr.paladium.palaconfiguration.server.dto.file.RemoteDirectory;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<RemoteObject> {
  public void onResponse(Call<RemoteObject> call, Response<RemoteObject> response) {
    if (!response.isSuccessful() || response.body() == null) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
      return;
    } 
    RemoteObject object = (RemoteObject)response.body();
    if (!object.isDirectory()) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cLe fichier spécifié n'est pas un dossier" });
      return;
    } 
    RemoteDirectory directory = object.asDirectory();
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Liste des fichiers disponibles :" });
    directory.listFiles().forEach(file -> ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §e" + file.getPath() + " §7(§d" + (file.isDirectory() ? "Dossier" : "Fichier") + "§7)" }));
  }
  
  public void onFailure(Call<RemoteObject> call, Throwable t) {
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
    t.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\command\ConfigurationCommand$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
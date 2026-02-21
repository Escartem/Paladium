package fr.paladium.palaconfiguration.server.command;

import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import org.apache.commons.io.FileUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<RemoteObject> {
  public void onResponse(Call<RemoteObject> call, Response<RemoteObject> response) {
    if (!response.isSuccessful() || response.body() == null) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération du fichier" });
      return;
    } 
    RemoteObject file = (RemoteObject)response.body();
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Informations sur le fichier :" });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eNom : §7" + file.getName() });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eChemin : §7" + file.getPath() });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eTaille : §7" + FileUtils.byteCountToDisplaySize(file.getSize()) });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eType : §7" + (file.isDirectory() ? "Dossier" : "Fichier") });
    if (file.isDirectory()) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eFichiers : §7" + file.asDirectory().listFiles().size() });
    } else {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eSHA1 : §7" + file.getSha1() });
    } 
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
  }
  
  public void onFailure(Call<RemoteObject> call, Throwable t) {
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération du fichier" });
    t.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\command\ConfigurationCommand$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
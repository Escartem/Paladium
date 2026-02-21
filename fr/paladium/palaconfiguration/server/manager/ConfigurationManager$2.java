package fr.paladium.palaconfiguration.server.manager;

import fr.paladium.palaconfiguration.server.command.IReloadCallback;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaconfiguration.server.utils.ConfigurationModUtils;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import java.util.List;
import net.minecraft.command.ICommandSender;

class null implements IRetrofitCallback<IConfig> {
  public void onSuccess(IConfig c) {
    if (c == null)
      return; 
    if (callback != null)
      callback.success(); 
    admins.forEach(admin -> {
          ChatUtils.sendColoredMessage(admin, new String[] { String.format("§aLa configuration §e%s§a a été rechargée avec succès ! §7(§d%s§7)", new Object[] { config.getClass().getSimpleName(), "API" }) });
          if (debug)
            ConfigurationModUtils.sendJsonObject(admin, configFile.path(), c); 
        });
  }
  
  public void onFail(IConfig c, Throwable throwable) {
    admins.forEach(admin -> ChatUtils.sendColoredMessage(admin, new String[] { "§cImpossible de recharger la configuration !" }));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\manager\ConfigurationManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
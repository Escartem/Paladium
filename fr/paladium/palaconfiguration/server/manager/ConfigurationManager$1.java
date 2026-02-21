package fr.paladium.palaconfiguration.server.manager;

import fr.paladium.palaconfiguration.ConfigurationLogger;
import fr.paladium.palaconfiguration.server.dto.config.Config;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;

class null implements IRetrofitCallback<Config> {
  public void onSuccess(Config configDTO) {
    try {
      ConfigurationManager.access$000(ConfigurationManager.this, configDTO, config, false);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load configuration " + path + "!", e);
    } 
    ConfigurationLogger.info("Loaded configuration " + path + "!");
  }
  
  public void onFail(Config configDTO, Throwable throwable) {
    config.onFailed();
    throw new RuntimeException("Failed to load configuration " + path + "!", throwable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\manager\ConfigurationManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
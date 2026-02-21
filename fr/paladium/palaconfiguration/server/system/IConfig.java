package fr.paladium.palaconfiguration.server.system;

import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import lombok.NonNull;

public interface IConfig {
  default boolean isValid() {
    return true;
  }
  
  void onLoaded();
  
  void onReloaded();
  
  void onFailed();
  
  @NonNull
  default ConfigFile getConfigFile() {
    return getClass().<ConfigFile>getAnnotation(ConfigFile.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\system\IConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
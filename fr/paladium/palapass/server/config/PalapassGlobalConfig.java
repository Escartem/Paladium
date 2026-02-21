package fr.paladium.palapass.server.config;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palapass.common.manager.PalapassManager;

@ConfigFile(path = "palapass/global.json", blocking = true)
public class PalapassGlobalConfig implements IConfig {
  public void setFirstMonth(int firstMonth) {
    this.firstMonth = firstMonth;
  }
  
  private int firstMonth = -1;
  
  public int getFirstMonth() {
    return this.firstMonth;
  }
  
  public void onLoaded() {
    PalapassManager.getInstance().setFirstMonth(this.firstMonth);
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\config\PalapassGlobalConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
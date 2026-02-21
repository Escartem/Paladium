package fr.paladium.palashop.server.config;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palashop.server.config.tebex.TebexConfig;
import java.net.InetSocketAddress;
import java.net.Socket;

@ConfigFile(path = "palashop/config.json", blocking = true)
public class PalaShopConfig implements IConfig {
  private String api;
  
  private TebexConfig tebex;
  
  public String getApi() {
    return this.api;
  }
  
  public TebexConfig getTebex() {
    return this.tebex;
  }
  
  public void onLoaded() {
    if (this.api == null)
      throw new RuntimeException("The API key is not defined"); 
    if (this.tebex == null || this.tebex.getToken() == null)
      throw new RuntimeException("The Tebex configuration is not defined"); 
    if (ForgeEnv.isIDE()) {
      try {
        InetSocketAddress sa = new InetSocketAddress("127.0.0.1", 7080);
        Socket ss = new Socket();
        ss.connect(sa, 1);
        ss.close();
        this.api = "http://127.0.0.1:7080";
      } catch (Exception exception) {}
      System.out.println("[PalaShop] API: " + this.api);
      System.out.println("[PalaShop] Tebex: " + this.tebex.getToken());
    } 
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {
    throw new RuntimeException("Failed to load the configuration file");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\config\PalaShopConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
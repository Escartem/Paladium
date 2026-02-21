package fr.paladium.palamod.modules.luckyblock.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class WishCardConfig {
  private String host;
  
  private String username;
  
  private String password;
  
  private String webhook;
  
  private Configuration config;
  
  public static WishCardConfig instance;
  
  public String getHost() {
    return this.host;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getWebhook() {
    return this.webhook;
  }
  
  public static WishCardConfig getInstance() {
    return instance;
  }
  
  public WishCardConfig(File location) {
    initConfigs(location);
    instance = this;
  }
  
  public void initConfigs(File location) {
    File configFile = new File(location + "/" + "palamod" + "_WISHCARD.cfg");
    this.config = new Configuration(configFile);
    this.host = this.config.getString("host", "mongo", "localhost", "Mongo Host");
    this.username = this.config.getString("username", "mongo", "admin", "Mongo Username");
    this.password = this.config.getString("password", "mongo", "password", "Mongo Password");
    this
      .webhook = this.config.getString("webhook", "discord", "https://discord.com/api/webhooks/", "Webhook");
    if (this.config.hasChanged())
      this.config.save(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\config\WishCardConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
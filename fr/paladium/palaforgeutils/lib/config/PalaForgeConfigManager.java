package fr.paladium.palaforgeutils.lib.config;

import fr.paladium.palaforgeutils.lib.mongo.MongoDBCredentials;
import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitCredentials;

public class PalaForgeConfigManager {
  private static PalaForgeConfigManager instance;
  
  private final String publicApiToken;
  
  private final String hastePublicURL;
  
  private final String hasteInternalURL;
  
  private final RabbitCredentials rabbitCredentials;
  
  private final MongoDBCredentials mongoDBCredentials;
  
  private final boolean enablePalaGive;
  
  private final String serverType;
  
  private final String serverName;
  
  public String getPublicApiToken() {
    return this.publicApiToken;
  }
  
  public String getHastePublicURL() {
    return this.hastePublicURL;
  }
  
  public String getHasteInternalURL() {
    return this.hasteInternalURL;
  }
  
  public RabbitCredentials getRabbitCredentials() {
    return this.rabbitCredentials;
  }
  
  public MongoDBCredentials getMongoDBCredentials() {
    return this.mongoDBCredentials;
  }
  
  public boolean isEnablePalaGive() {
    return this.enablePalaGive;
  }
  
  public String getServerType() {
    return this.serverType;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public PalaForgeConfigManager() {
    instance = this;
    this.publicApiToken = "00000000-0000-0000-0000-000000000000";
    this.hastePublicURL = "https://haste.paladium.games/";
    this.hasteInternalURL = "https://haste.paladium.games/";
    this.rabbitCredentials = null;
    this.mongoDBCredentials = null;
    this.enablePalaGive = true;
    this.serverType = "FACTION";
    this.serverName = "local";
  }
  
  public static PalaForgeConfigManager getInstance() {
    return instance;
  }
  
  public void setInstance() {
    instance = this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\config\PalaForgeConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
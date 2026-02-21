package fr.paladium.palamod.modules.luckyblock.luckystats.server.manager;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import fr.paladium.palamod.modules.luckyblock.config.ConfigManager;

public class LuckyStatsDatabaseManager {
  private String token;
  
  private String bucket;
  
  private String org;
  
  private String ip;
  
  private String port;
  
  public String getToken() {
    return this.token;
  }
  
  public String getBucket() {
    return this.bucket;
  }
  
  public String getOrg() {
    return this.org;
  }
  
  public String getIp() {
    return this.ip;
  }
  
  public String getPort() {
    return this.port;
  }
  
  private InfluxDBClient client = null;
  
  public InfluxDBClient getClient() {
    return this.client;
  }
  
  public void init() {
    this.token = ConfigManager.getString("influxdb", "token");
    this.bucket = ConfigManager.getString("influxdb", "bucket");
    this.org = ConfigManager.getString("influxdb", "org");
    this.ip = ConfigManager.getString("influxdb", "ip");
    this.port = ConfigManager.getString("influxdb", "port");
    try {
      this.client = InfluxDBClientFactory.create(this.ip + ":" + this.port, this.token.toCharArray());
      if (this.client.ready() == null)
        throw new Exception("Unable to connect"); 
      System.err.println("Initialisation du bucket : " + this.bucket + " (influxdb)");
    } catch (Exception e) {
      this.client = null;
      System.err.println("Impossible d'établir le bucket : " + this.bucket + " (influxdb)");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\server\manager\LuckyStatsDatabaseManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
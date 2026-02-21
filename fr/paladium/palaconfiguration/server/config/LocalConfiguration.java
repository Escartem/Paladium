package fr.paladium.palaconfiguration.server.config;

import fr.paladium.palaconfiguration.ConfigurationLogger;
import fr.paladium.palaconfiguration.server.dto.Environment;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitCredentials;

@ConfigFile(path = "palaconfigurations.json", local = true, hidden = true, reloadable = false)
public class LocalConfiguration implements IConfig {
  private final String apiUrl;
  
  private final Environment environment;
  
  private final String rabbitQueueName;
  
  private final RabbitCredentials rabbitCredentials;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof LocalConfiguration))
      return false; 
    LocalConfiguration other = (LocalConfiguration)o;
    if (!other.canEqual(this))
      return false; 
    Object this$apiUrl = getApiUrl(), other$apiUrl = other.getApiUrl();
    if ((this$apiUrl == null) ? (other$apiUrl != null) : !this$apiUrl.equals(other$apiUrl))
      return false; 
    Object this$environment = getEnvironment(), other$environment = other.getEnvironment();
    if ((this$environment == null) ? (other$environment != null) : !this$environment.equals(other$environment))
      return false; 
    Object this$rabbitQueueName = getRabbitQueueName(), other$rabbitQueueName = other.getRabbitQueueName();
    if ((this$rabbitQueueName == null) ? (other$rabbitQueueName != null) : !this$rabbitQueueName.equals(other$rabbitQueueName))
      return false; 
    Object this$rabbitCredentials = getRabbitCredentials(), other$rabbitCredentials = other.getRabbitCredentials();
    return !((this$rabbitCredentials == null) ? (other$rabbitCredentials != null) : !this$rabbitCredentials.equals(other$rabbitCredentials));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof LocalConfiguration;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $apiUrl = getApiUrl();
    result = result * 59 + (($apiUrl == null) ? 43 : $apiUrl.hashCode());
    Object $environment = getEnvironment();
    result = result * 59 + (($environment == null) ? 43 : $environment.hashCode());
    Object $rabbitQueueName = getRabbitQueueName();
    result = result * 59 + (($rabbitQueueName == null) ? 43 : $rabbitQueueName.hashCode());
    Object $rabbitCredentials = getRabbitCredentials();
    return result * 59 + (($rabbitCredentials == null) ? 43 : $rabbitCredentials.hashCode());
  }
  
  public String toString() {
    return "LocalConfiguration(apiUrl=" + getApiUrl() + ", environment=" + getEnvironment() + ", rabbitQueueName=" + getRabbitQueueName() + ", rabbitCredentials=" + getRabbitCredentials() + ")";
  }
  
  public LocalConfiguration(String apiUrl, Environment environment, String rabbitQueueName, RabbitCredentials rabbitCredentials) {
    this.apiUrl = apiUrl;
    this.environment = environment;
    this.rabbitQueueName = rabbitQueueName;
    this.rabbitCredentials = rabbitCredentials;
  }
  
  public String getApiUrl() {
    return this.apiUrl;
  }
  
  public Environment getEnvironment() {
    return this.environment;
  }
  
  public String getRabbitQueueName() {
    return this.rabbitQueueName;
  }
  
  public RabbitCredentials getRabbitCredentials() {
    return this.rabbitCredentials;
  }
  
  public LocalConfiguration() {
    this.apiUrl = "http://localhost:8081/";
    this.environment = Environment.DEV;
    this.rabbitQueueName = "paladium.config";
    this.rabbitCredentials = new RabbitCredentials();
  }
  
  public boolean isValid() {
    if (this.apiUrl == null || this.environment == null || this.rabbitQueueName == null || this.rabbitCredentials == null) {
      ConfigurationLogger.error("Invalid configuration file! apiUrl, environment, rabbitQueueName or rabbitCredentials is null!");
      return false;
    } 
    return true;
  }
  
  public void onLoaded() {}
  
  public void onReloaded() {}
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\config\LocalConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
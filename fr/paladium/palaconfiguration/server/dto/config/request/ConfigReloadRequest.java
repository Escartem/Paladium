package fr.paladium.palaconfiguration.server.dto.config.request;

import fr.paladium.palaconfiguration.server.dto.Environment;

public class ConfigReloadRequest {
  private String path;
  
  private Environment environment;
  
  private boolean reload;
  
  public void setPath(String path) {
    this.path = path;
  }
  
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
  
  public void setReload(boolean reload) {
    this.reload = reload;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof ConfigReloadRequest))
      return false; 
    ConfigReloadRequest other = (ConfigReloadRequest)o;
    if (!other.canEqual(this))
      return false; 
    if (isReload() != other.isReload())
      return false; 
    Object this$path = getPath(), other$path = other.getPath();
    if ((this$path == null) ? (other$path != null) : !this$path.equals(other$path))
      return false; 
    Object this$environment = getEnvironment(), other$environment = other.getEnvironment();
    return !((this$environment == null) ? (other$environment != null) : !this$environment.equals(other$environment));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof ConfigReloadRequest;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isReload() ? 79 : 97);
    Object $path = getPath();
    result = result * 59 + (($path == null) ? 43 : $path.hashCode());
    Object $environment = getEnvironment();
    return result * 59 + (($environment == null) ? 43 : $environment.hashCode());
  }
  
  public String toString() {
    return "ConfigReloadRequest(path=" + getPath() + ", environment=" + getEnvironment() + ", reload=" + isReload() + ")";
  }
  
  public ConfigReloadRequest() {}
  
  public ConfigReloadRequest(String path, Environment environment, boolean reload) {
    this.path = path;
    this.environment = environment;
    this.reload = reload;
  }
  
  public String getPath() {
    return this.path;
  }
  
  public Environment getEnvironment() {
    return this.environment;
  }
  
  public boolean isReload() {
    return this.reload;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\config\request\ConfigReloadRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
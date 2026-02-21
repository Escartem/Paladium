package fr.paladium.palashop.server.config.tebex;

import java.util.List;

public class TebexConfig {
  private String token;
  
  private List<TebexPackageConfig> packages;
  
  public String toString() {
    return "TebexConfig(token=" + getToken() + ", packages=" + getPackages() + ")";
  }
  
  public String getToken() {
    return this.token;
  }
  
  public List<TebexPackageConfig> getPackages() {
    return this.packages;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\config\tebex\TebexConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
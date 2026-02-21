package fr.paladium.palashop.server.config.tebex;

public class TebexPackageConfig {
  private int packageId;
  
  private int quantity;
  
  private int bonus;
  
  private boolean subscription;
  
  private String thumbnail;
  
  public String toString() {
    return "TebexPackageConfig(packageId=" + getPackageId() + ", quantity=" + getQuantity() + ", bonus=" + getBonus() + ", subscription=" + isSubscription() + ", thumbnail=" + getThumbnail() + ")";
  }
  
  public int getPackageId() {
    return this.packageId;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public int getBonus() {
    return this.bonus;
  }
  
  public boolean isSubscription() {
    return this.subscription;
  }
  
  public String getThumbnail() {
    return this.thumbnail;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\config\tebex\TebexPackageConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
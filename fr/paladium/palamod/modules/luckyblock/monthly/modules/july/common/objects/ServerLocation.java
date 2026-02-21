package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;

public class ServerLocation {
  private String serverName;
  
  private DoubleLocation location;
  
  public ServerLocation(String serverName, DoubleLocation location) {
    this.serverName = serverName;
    this.location = location;
  }
  
  public static ServerLocationBuilder builder() {
    return new ServerLocationBuilder();
  }
  
  public static class ServerLocationBuilder {
    private String serverName;
    
    private DoubleLocation location;
    
    public ServerLocationBuilder serverName(String serverName) {
      this.serverName = serverName;
      return this;
    }
    
    public ServerLocationBuilder location(DoubleLocation location) {
      this.location = location;
      return this;
    }
    
    public ServerLocation build() {
      return new ServerLocation(this.serverName, this.location);
    }
    
    public String toString() {
      return "ServerLocation.ServerLocationBuilder(serverName=" + this.serverName + ", location=" + this.location + ")";
    }
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public ServerLocation() {
    this.serverName = "NONE";
    this.location = new DoubleLocation(0.0D, 0.0D, 0.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\objects\ServerLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
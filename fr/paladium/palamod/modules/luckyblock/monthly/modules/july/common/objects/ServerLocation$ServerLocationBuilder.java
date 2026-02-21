package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;

public class ServerLocationBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\objects\ServerLocation$ServerLocationBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
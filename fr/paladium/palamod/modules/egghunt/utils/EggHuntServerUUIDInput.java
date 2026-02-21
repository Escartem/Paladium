package fr.paladium.palamod.modules.egghunt.utils;

import fr.paladium.common.CommonModule;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EggHuntServerUUIDInput {
  private String serverUUID;
  
  public String getServerUUID() {
    return this.serverUUID;
  }
  
  public EggHuntServerUUIDInput() {
    try {
      this.serverUUID = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      this.serverUUID = CommonModule.getInstance().getConfig().getServerName();
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghun\\utils\EggHuntServerUUIDInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
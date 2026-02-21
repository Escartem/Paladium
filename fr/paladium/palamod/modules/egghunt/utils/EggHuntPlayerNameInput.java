package fr.paladium.palamod.modules.egghunt.utils;

import fr.paladium.common.CommonModule;
import fr.paladium.palamod.util.FastUUID;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class EggHuntPlayerNameInput {
  private String serverUUID;
  
  private String playerUUID;
  
  private String playerName;
  
  public String getServerUUID() {
    return this.serverUUID;
  }
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public EggHuntPlayerNameInput(EntityPlayer player) {
    this(player.func_70005_c_(), player.func_110124_au());
  }
  
  public EggHuntPlayerNameInput(String playerName, UUID playerUUID) {
    try {
      this.serverUUID = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      this.serverUUID = CommonModule.getInstance().getConfig().getServerName();
      e.printStackTrace();
    } 
    this.playerUUID = (playerUUID != null) ? FastUUID.toString(playerUUID) : null;
    this.playerName = playerName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghun\\utils\EggHuntPlayerNameInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
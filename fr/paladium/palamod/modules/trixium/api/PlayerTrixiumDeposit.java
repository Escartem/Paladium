package fr.paladium.palamod.modules.trixium.api;

import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerTrixiumDeposit {
  public String playerName;
  
  public int amount;
  
  public String factionUUID;
  
  public PlayerTrixiumDeposit() {}
  
  public PlayerTrixiumDeposit(String playerName) {
    this.playerName = playerName;
  }
  
  public PlayerTrixiumDeposit(EntityPlayerMP player) {
    this.playerName = player.func_70005_c_();
    Player iPlayer = PlayerController.getInstance().getLoadedPlayer(player);
    if (iPlayer != null && iPlayer.hasFaction() && !iPlayer.getFaction().isDefaultFaction())
      this.factionUUID = iPlayer.getFactionUuid().toString(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\api\PlayerTrixiumDeposit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
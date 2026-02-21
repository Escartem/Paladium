package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.player.FactionPlayer;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.factions.core.utils.ControlResult;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class FactionLeave extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    try {
      PlayerController controller = PlayerController.getInstance();
      Player player1 = controller.getLoadedPlayer(player);
      if (player1 != null && player1.hasFaction()) {
        IFaction faction = player1.getFaction();
        if (faction != null) {
          FactionPlayer factionPlayer = player1.getFactionPlayer();
          if (faction.getMemberEntity().getLeader().getPlayer().getUuid().equals(player1.getUuid())) {
            IFactionPlayer randomOther = faction.getMemberEntity().getPlayers().stream().filter(p -> !p.getPlayer().getUuid().equals(factionPlayer.getUuid())).findAny().orElse(null);
            if (randomOther != null) {
              faction.getMemberEntity().removePlayer((IFactionPlayer)factionPlayer, randomOther.getPlayer(), new ControlResult<IFaction>() {
                    public void onCallback() {
                      PlayerUtils.sendActionBar(player, "§cVous avez kické un membre de votre faction… Ooops");
                    }
                  });
            } else {
              PlayerUtils.sendActionBar(player, "§cPas d'autres membres dans ta faction... Tu as de la chance...");
            } 
          } else {
            faction.getMemberEntity().removePlayer((IFactionPlayer)factionPlayer, new ControlResult<IFaction>() {
                  public void onCallback() {
                    PlayerUtils.sendActionBar(player, "§cVous avez quitté votre faction… Ooops");
                  }
                });
          } 
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public String getName() {
    return "Adieu faction";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 2000;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FactionLeave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
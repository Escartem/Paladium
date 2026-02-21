package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.core.utils.ControlResult;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;

class null extends ControlResult<IFaction> {
  public void onCallback() {
    PlayerUtils.sendActionBar(player, "§cVous avez quitté votre faction… Ooops");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FactionLeave$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
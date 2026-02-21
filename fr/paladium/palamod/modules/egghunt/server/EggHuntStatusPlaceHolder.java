package fr.paladium.palamod.modules.egghunt.server;

import fr.paladium.hologram.client.placeholder.IPlaceHolder;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntData;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import java.util.UUID;
import org.bukkit.entity.Player;

public class EggHuntStatusPlaceHolder implements IPlaceHolder {
  public String getName() {
    return "egghunt.status";
  }
  
  public String perform(Player bukkitPlayer, UUID uuid, String rawLine) {
    EggHuntStatus status = PEggHunt.status;
    EggHuntData data = PEggHunt.data;
    if (status == null || data == null)
      return "Recherche de dragon en cours dans les différentes contrées"; 
    if (data.isActive()) {
      if (status.getDragonDeathTime() <= -1L)
        return "Tuer le dragon en §c" + PEggHunt.config.getDragonPosition().format(); 
      if (status.getEggOwner() == null)
        return "Récupérer l'oeuf"; 
      return "Chasser l'oeuf";
    } 
    return "Recherche de dragon en cours dans les différentes contrées";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\server\EggHuntStatusPlaceHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
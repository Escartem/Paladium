package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Omniscience implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    ServerManager.addActiveSpell(getId(), FastUUID.toString((Entity)player));
    PlayerUtils.sendActionBar(player, "§eFaites clic droit sur un joueur pour voir son inventaire");
  }
  
  public int getId() {
    return 7;
  }
  
  public String getName() {
    return "Omniscience";
  }
  
  public int getMaxTiers() {
    return 1;
  }
  
  public int getCost() {
    return 2;
  }
  
  public int getCooldown() {
    return 20;
  }
  
  public int getLevel() {
    return 30;
  }
  
  public String[] getDescription() {
    return new String[] { "Une fois le sort activé et un clique droit effectué sur le joueur de notre choix, le contenu de l’inventaire du joueur en question est affiché pendant 10 secondes au joueur (évidemment sans possibilité de récupérer le contenu de l’inventaire)." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Omniscience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
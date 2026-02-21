package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Explosif implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    String uuid = FastUUID.toString((Entity)player);
    ServerManager.addActiveSpell(getId(), uuid);
    player.func_130014_f_().func_72876_a((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 2.5F, true);
    ServerManager.removeActiveSpell(getId(), uuid);
  }
  
  public int getId() {
    return 5;
  }
  
  public String getName() {
    return "Explosif";
  }
  
  public int getMaxTiers() {
    return 1;
  }
  
  public int getCost() {
    return 5;
  }
  
  public int getCooldown() {
    return 5;
  }
  
  public int getLevel() {
    return 30;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de faire une explosion autour de soi (on ne prend pas de dégâts)." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Explosif.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
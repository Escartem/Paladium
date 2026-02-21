package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityPumpkinGolem;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SoupeDeCitrouille extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!(player.func_130014_f_()).field_72995_K) {
      EntityPumpkinGolem golem = new EntityPumpkinGolem(player.func_130014_f_());
      golem.func_70107_b(x, y, z);
      player.field_70170_p.func_72838_d((Entity)golem);
    } 
  }
  
  public String getName() {
    return "Soupe de citrouille";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\SoupeDeCitrouille.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
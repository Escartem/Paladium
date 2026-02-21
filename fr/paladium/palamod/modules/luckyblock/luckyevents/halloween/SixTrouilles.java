package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.modules.luckyblock.entity.EntityZombieHalloween;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SixTrouilles extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!(player.func_130014_f_()).field_72995_K) {
      EntityZombieHalloween golem = new EntityZombieHalloween(player.func_130014_f_());
      golem.func_70107_b(x, y, z);
      golem.func_82164_bB();
      player.field_70170_p.func_72838_d((Entity)golem);
    } 
  }
  
  public String getName() {
    return "6 Trouilles";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\SixTrouilles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
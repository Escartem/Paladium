package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityGhostHalloween;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Bouh extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!(player.func_130014_f_()).field_72995_K)
      for (int i = 0; i < 10; i++) {
        EntityGhostHalloween ghost = new EntityGhostHalloween(player.func_130014_f_());
        ghost.func_70107_b(x + Math.random() * 2.0D, (y + 4), z + Math.random() * 2.0D);
        player.field_70170_p.func_72838_d((Entity)ghost);
      }  
  }
  
  public String getName() {
    return "Bouh";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\Bouh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(10000L);
      pig.field_70170_p.func_72876_a((Entity)pig, pig.field_70165_t, pig.field_70163_u, pig.field_70161_v, 15.0F, true);
      if (!pig.field_70128_L)
        pig.func_70106_y(); 
      UsersManager.getPiggyRodeo().remove(player);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PiggyRodeo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayerMP;

public class Sherpa extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = new Random();
    int j = rand.nextInt(10) + rand.nextInt(10);
    for (int i = 0; i < j; i++) {
      EntityCow cow = new EntityCow(player.field_70170_p);
      cow.func_70107_b(player.field_70165_t - 2.0D + rand.nextInt(5), player.field_70163_u, player.field_70161_v - 2.0D + rand.nextInt(5));
      cow.func_110162_b((Entity)player, true);
      player.field_70170_p.func_72838_d((Entity)cow);
    } 
  }
  
  public String getName() {
    return "Sherpa";
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "sherpa";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Sherpa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
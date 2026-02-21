package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.entity.black.EntityPhantom;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Cauchemardesque extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = new Random();
    int nb = 4 + rand.nextInt(4);
    for (int i = 0; i < nb; i++) {
      EntityPhantom phantom = new EntityPhantom(player.field_70170_p);
      phantom.func_70107_b(player.field_70165_t + rand.nextInt(6) - 3.0D, player.field_70163_u, player.field_70161_v + rand.nextInt(6) - 3.0D);
      player.field_70170_p.func_72838_d((Entity)phantom);
    } 
  }
  
  public String getName() {
    return "Cauchemardesque";
  }
  
  public int getRarity() {
    return 200;
  }
  
  public String getTexture() {
    return "cauchemardesque";
  }
  
  public boolean isBad() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Cauchemardesque.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
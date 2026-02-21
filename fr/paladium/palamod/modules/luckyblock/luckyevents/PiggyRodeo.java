package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayerMP;

public class PiggyRodeo extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final EntityPig pig = new EntityPig(player.func_130014_f_());
    pig.func_70634_a(x, y, z);
    player.func_130014_f_().func_72838_d((Entity)pig);
    player.func_70078_a((Entity)pig);
    UsersManager.getPiggyRodeo().put(player, pig);
    (new Thread(new Runnable() {
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
        })).start();
  }
  
  public String getName() {
    return "Piggy Rodéo";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "piggy_rodeo";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PiggyRodeo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
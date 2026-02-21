package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Armaguedon extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, final int x, int y, final int z) {
    final Random r = player.field_70170_p.field_73012_v;
    (new Thread(new Runnable() {
          public void run() {
            try {
              for (int i = 0; i < 10; i++) {
                Thread.sleep(1000L);
                for (int k = 0; k < 10; k++) {
                  int ox = r.nextInt(20) - 10;
                  int oz = r.nextInt(20) - 10;
                  DynamiteEntity ent = new DynamiteEntity(player.field_70170_p, (x + ox), (player.field_70170_p.func_72976_f(x + ox, z + oz) + 10), (z + oz));
                  ent.type = DynamiteEntity.BIG;
                  ent.explodefuse = 30;
                  player.field_70170_p.func_72838_d((Entity)ent);
                } 
              } 
            } catch (Exception exception) {}
          }
        })).start();
  }
  
  public String getName() {
    return "Armaguedon";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 2000;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Armaguedon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
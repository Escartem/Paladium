package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class Princess extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, final int x, final int y, final int z) {
    EventUtils.spawnProtectedAnimatedStructure(player.field_70170_p, x, y + 4, z, 9, 9, 9, Blocks.field_150435_aG, "heart", 5, (Entity)player, player);
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(5000L);
              EventUtils.spawnStructure(player.field_70170_p, x, y + 4, z, 7, 7, 7, Blocks.field_150350_a, player);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } 
          }
        })).start();
  }
  
  public String getName() {
    return "Princesse";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Princess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
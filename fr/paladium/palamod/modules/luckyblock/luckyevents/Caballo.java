package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntitySkeletonHorse;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Caballo extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntitySkeletonHorse entity = new EntitySkeletonHorse(player.field_70170_p);
    entity.func_94058_c("§bCaballo");
    entity.func_94061_f(true);
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Caballo de la muerte";
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Caballo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
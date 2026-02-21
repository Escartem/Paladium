package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPricklySheep;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PricklySheep extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityPricklySheep sheep = new EntityPricklySheep(player.field_70170_p);
    sheep.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)sheep);
  }
  
  public String getName() {
    return "Titanesque";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PricklySheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
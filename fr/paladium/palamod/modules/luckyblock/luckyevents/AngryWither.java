package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.entity.EntityAngryWither;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class AngryWither extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityAngryWither entityAngryWither = new EntityAngryWither(player.field_70170_p);
    entityAngryWither.func_70107_b(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entityAngryWither);
  }
  
  public String getName() {
    return "Whiter énervé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "angry_wither";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\AngryWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
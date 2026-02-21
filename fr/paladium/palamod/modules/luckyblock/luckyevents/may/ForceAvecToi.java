package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.entity.may.EntityDarkKnight;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class ForceAvecToi extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityDarkKnight entity = new EntityDarkKnight(player.field_70170_p);
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Que la force soit avec toi";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 180;
  }
  
  public String getTexture() {
    return "may/force_avec_toi";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\ForceAvecToi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
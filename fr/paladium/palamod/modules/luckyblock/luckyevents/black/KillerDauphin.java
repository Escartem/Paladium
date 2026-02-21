package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class KillerDauphin extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityDolphin dolphin = new EntityDolphin(player.field_70170_p, false);
    dolphin.func_70107_b(x, y, z);
    player.field_70170_p.func_72838_d((Entity)dolphin);
  }
  
  public String getName() {
    return "Killer Dauphin";
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "killer_dauphin";
  }
  
  public boolean isBad() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\KillerDauphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
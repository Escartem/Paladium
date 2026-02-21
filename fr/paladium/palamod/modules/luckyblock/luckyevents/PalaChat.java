package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.entity.EntityPalachat;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PalaChat extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityPalachat entity = new EntityPalachat(player.field_70170_p, FastUUID.toString((Entity)player));
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Palachat";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PalaChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
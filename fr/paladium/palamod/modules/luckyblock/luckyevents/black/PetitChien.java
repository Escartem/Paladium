package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.entity.black.EntityClone;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PetitChien extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityClone clone = new EntityClone(player, player.field_70170_p, x, y, z);
    clone.func_152115_b(player.func_110124_au().toString());
    clone.func_70903_f(true);
    clone.func_70784_b(null);
    player.field_70170_p.func_72838_d((Entity)clone);
  }
  
  public String getName() {
    return "Petit Chien Chien";
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "petit_chien_chien";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\PetitChien.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
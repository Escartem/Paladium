package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class SpaceFood extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_145779_a(ItemsRegister.SPACE_FOOD, 1);
  }
  
  public String getName() {
    return "Vers l’infini et l’au delà";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public String getTexture() {
    return "space_food";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SpaceFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
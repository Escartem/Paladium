package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityGoldenChicken;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PouleOeufsOr extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityGoldenChicken entity = new EntityGoldenChicken(player.field_70170_p);
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "La poule aux œufs d’or";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 220;
  }
  
  public String getTexture() {
    return "easter/poule_oeufs_or";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\PouleOeufsOr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
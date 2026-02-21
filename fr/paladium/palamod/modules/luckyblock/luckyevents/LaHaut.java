package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.entity.EntityBalloon;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class LaHaut extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_72838_d((Entity)new EntityBalloon(player.field_70170_p, (Entity)player, player.field_70165_t, player.field_70163_u + 2.0D, player.field_70161_v));
    player.field_70170_p.func_72838_d((Entity)new EntityBalloon(player.field_70170_p, (Entity)player, player.field_70165_t + 0.5D, player.field_70163_u + 2.0D, player.field_70161_v));
    player.field_70170_p.func_72838_d((Entity)new EntityBalloon(player.field_70170_p, (Entity)player, player.field_70165_t + 0.5D, player.field_70163_u + 2.0D, player.field_70161_v + 0.5D));
  }
  
  public String getName() {
    return "Là-Haut";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
  
  public String getTexture() {
    return "la-haut";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\LaHaut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
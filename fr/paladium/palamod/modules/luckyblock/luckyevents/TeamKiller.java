package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class TeamKiller extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 10; i++) {
      EntityRabbit ent = new EntityRabbit(player.field_70170_p);
      ent.func_70107_b(x, y, z);
      ent.setRabbitType(99);
      ent.func_70624_b((EntityLivingBase)player);
      player.field_70170_p.func_72838_d((Entity)ent);
    } 
  }
  
  public String getName() {
    return "TeamKiller";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 800;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\TeamKiller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
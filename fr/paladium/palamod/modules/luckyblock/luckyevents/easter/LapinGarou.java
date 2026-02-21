package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class LapinGarou extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityRabbit entity = new EntityRabbit(player.field_70170_p);
    entity.func_70634_a(x, y, z);
    entity.setRabbitType(98);
    entity.func_70624_b((EntityLivingBase)player);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Le lapin de Pâques, enfin je crois...";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 190;
  }
  
  public String getTexture() {
    return "easter/lapin_garou";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\LapinGarou.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
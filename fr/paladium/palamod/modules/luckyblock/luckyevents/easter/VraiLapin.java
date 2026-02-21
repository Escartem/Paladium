package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityEasterRabbit;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class VraiLapin extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityEasterRabbit entity = new EntityEasterRabbit(player.field_70170_p);
    entity.setRabbitType(4);
    entity.func_70634_a(x, y, z);
    entity.setItemToDropEachJump(ItemsRegister.CHOCOLATE_EGG);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Le vrai lapin de Pâques";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public String getTexture() {
    return "easter/vrai_lapin";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\VraiLapin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
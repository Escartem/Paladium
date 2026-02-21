package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class ChristmasVillage extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityGodVillager villager = new EntityGodVillager(player.field_70170_p);
    villager.func_70107_b(x, y, z);
    villager.setType(0);
    player.field_70170_p.func_72838_d((Entity)villager);
  }
  
  public String getName() {
    return "Village de Noel";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "christmas_village";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\ChristmasVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
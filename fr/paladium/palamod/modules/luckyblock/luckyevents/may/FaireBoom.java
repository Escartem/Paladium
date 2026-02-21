package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.entity.may.EntityCustomCreeper;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;

public class FaireBoom extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityCustomCreeper entity = new EntityCustomCreeper(player.field_70170_p, true, false, 4.5F);
    entity.func_70634_a(x, y, z);
    entity.func_94058_c("§bMega Creeper");
    player.field_70170_p.func_72838_d((Entity)entity);
    entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100000.0D);
    entity.func_70606_j(100000.0F);
    entity.func_70096_w().func_75692_b(17, Byte.valueOf((byte)1));
  }
  
  public String getName() {
    return "Ça va faire BOOM";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 140;
  }
  
  public String getTexture() {
    return "may/faire_boom";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\FaireBoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
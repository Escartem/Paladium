package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class Boom extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_72838_d((Entity)new DynamiteEntity(player.field_70170_p, (EntityLivingBase)player, 40, DynamiteEntity.DEFAULT));
  }
  
  public String getName() {
    return "Boom";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Boom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
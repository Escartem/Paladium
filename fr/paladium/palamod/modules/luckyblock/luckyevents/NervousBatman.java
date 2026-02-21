package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityNervousBat;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class NervousBatman extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 20; i++) {
      EntityNervousBat nervousbat = new EntityNervousBat(player.field_70170_p);
      nervousbat.func_70634_a(x, y, z);
      nervousbat.func_70624_b((EntityLivingBase)player);
      nervousbat.func_70604_c((EntityLivingBase)player);
      player.field_70170_p.func_72838_d((Entity)nervousbat);
    } 
  }
  
  public String getName() {
    return "Batman nerveux";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\NervousBatman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
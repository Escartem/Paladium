package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class SuperMan extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.SUPER_MAN.field_76415_H, 2400, 0));
    player.field_71075_bZ.field_75101_c = true;
    player.field_71075_bZ.field_75100_b = true;
    player.func_71016_p();
  }
  
  public String getName() {
    return "SuperMan";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SuperMan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
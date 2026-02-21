package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class OnTheMoon extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 6000, 9));
  }
  
  public String getName() {
    return "Sur la Lune";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 40;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\OnTheMoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
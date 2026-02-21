package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class FireMan extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.FIRE_MAN.field_76415_H, 99999999, 0));
    PlayerUtils.sendActionBar(player, "§cHey ! Attention tu chauffes ㋡");
  }
  
  public String getName() {
    return "Tout feu tout flamme";
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FireMan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
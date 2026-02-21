package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Lag extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.LAG.field_76415_H, 1200, 0));
    PlayerUtils.sendActionBar(player, "§eAttention aux lags");
  }
  
  public String getName() {
    return "56K";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Lag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
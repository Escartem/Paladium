package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class OneShot extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 4; i++) {
      if (player.func_82169_q(i) != null)
        player.func_82169_q(i).func_77964_b(player
            .func_82169_q(i).func_77958_k() - player.func_82169_q(i).func_77960_j() - 1); 
    } 
    PlayerUtils.sendActionBar(player, "Ton armure me semble bien fragile");
  }
  
  public String getName() {
    return "One Shot";
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\OneShot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
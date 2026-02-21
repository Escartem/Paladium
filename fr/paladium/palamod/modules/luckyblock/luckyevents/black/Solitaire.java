package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Solitaire extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.SOLITAIRE.func_76396_c(), 2400));
  }
  
  public String getName() {
    return "Solitaire";
  }
  
  public int getRarity() {
    return 150;
  }
  
  public String getTexture() {
    return "solitaire";
  }
  
  public boolean isBad() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Solitaire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class CinemaMuet extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.CINEMA_MUET.field_76415_H, 2400));
  }
  
  public String getName() {
    return "Cinéma muet";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "june/cinema_muet";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\CinemaMuet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
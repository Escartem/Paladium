package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Rotate extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.ROTATE.field_76415_H, 999999999, 0));
  }
  
  public String getName() {
    return "Le monde à l’envers";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "reverse";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Rotate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
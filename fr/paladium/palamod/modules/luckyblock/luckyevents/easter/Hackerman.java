package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Hackerman extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.H4CK3D.field_76415_H, 1200));
  }
  
  public String getName() {
    return "Hackerman";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "easter/hackerman";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\Hackerman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
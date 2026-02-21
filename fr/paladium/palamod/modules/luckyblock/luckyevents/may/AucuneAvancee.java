package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class AucuneAvancee extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.CANCEL_FORWARD.field_76415_H, 6000));
  }
  
  public String getName() {
    return "Aucune avancée";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 170;
  }
  
  public String getTexture() {
    return "may/aucune_avancee";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\AucuneAvancee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
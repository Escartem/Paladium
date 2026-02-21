package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnflammeDancefloor extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (LuckyBlockUtils.isInCombat((EntityPlayer)player))
      return; 
    player.func_70690_d(new PotionEffect(PLuckyBlock.FIRE_WALK.field_76415_H, 300));
    player.func_70690_d(new PotionEffect(Potion.field_76428_l.func_76396_c(), 400, 2));
  }
  
  public String getName() {
    return "Enflamme le dance-floor";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "june/enflamme_dancefloor";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\EnflammeDancefloor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class HellYeah extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.HELLYEAH.field_76415_H, 2400, 0));
    player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 2500, 1));
  }
  
  public String getName() {
    return "Hell Yeah!";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "hell_yeah";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\HellYeah.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
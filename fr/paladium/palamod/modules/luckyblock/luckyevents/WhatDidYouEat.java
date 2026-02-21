package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class WhatDidYouEat extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.FART.func_76396_c(), 600, 1));
    PlayerUtils.sendActionBar(player, "§dT'arrêtes de péter, oui?");
  }
  
  public String getName() {
    return "T'as mangé quoi ?";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 900;
  }
  
  public String getTexture() {
    return "t_a_mange_quoi";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WhatDidYouEat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
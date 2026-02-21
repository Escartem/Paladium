package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Quack extends ALuckyEvent {
  public static final int EVENT_DURATION = 1800000;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyBlockProperties.get((EntityPlayer)player).setQuackDate(System.currentTimeMillis());
    player.func_70690_d(new PotionEffect(PLuckyBlock.QUACK.field_76415_H, 36000));
  }
  
  public String getName() {
    return "Quack";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "june/quack";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\Quack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
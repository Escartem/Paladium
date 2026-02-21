package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ReveilleToi extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(BlocksRegister.ALARM_CLOCK));
  }
  
  public String getName() {
    return "Réveille-toi";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 240;
  }
  
  public String getTexture() {
    return "june/reveille_toi";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\ReveilleToi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
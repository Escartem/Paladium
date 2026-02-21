package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class GolemCrasseux extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemInit.COSMETIC_SKIN_DIRTY, 1));
  }
  
  public String getName() {
    return "Golem Crasseux";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public String getTexture() {
    return "golem_crasseux";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\GolemCrasseux.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class WitherHead extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack item = new ItemStack(ItemsRegister.FUNNY_WITHER_GEM);
    item.func_151001_c("§eTête de wither gentil");
    ItemUtils.spawnItemInWorld(player.field_70170_p, x, y, z, item);
  }
  
  public String getName() {
    return "Tête de wither";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "wither";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WitherHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
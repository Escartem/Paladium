package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class VirusDance extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(ItemsRegister.RADIO));
  }
  
  public String getName() {
    return "Le virus de la dance";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "june/virus_dance";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\VirusDance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
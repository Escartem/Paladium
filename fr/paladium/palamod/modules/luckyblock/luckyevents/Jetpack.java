package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Jetpack extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70099_a(new ItemStack(ItemsRegister.JETPACK), 1.0F);
    player.func_70099_a(new ItemStack(Items.field_151152_bP, 16), 1.0F);
  }
  
  public String getName() {
    return "Envole-moi";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Jetpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
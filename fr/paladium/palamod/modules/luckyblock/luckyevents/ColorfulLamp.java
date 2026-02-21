package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ColorfulLamp extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70099_a(new ItemStack(BlocksRegister.COLORED_LAMP, 16), 1.0F);
  }
  
  public String getName() {
    return "Colorful Lamp";
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ColorfulLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
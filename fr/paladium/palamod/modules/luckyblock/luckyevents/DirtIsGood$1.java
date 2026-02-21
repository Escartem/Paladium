package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(10000L);
      for (int i = 0; i < 40; i++)
        player.field_71071_by.func_70299_a(i, inv[i]); 
      PlayerUtils.sendMessage((EntityPlayer)player, "§aAh c'est mieux");
    } catch (Exception e) {
      for (int i = 0; i < 40; i++)
        player.field_71071_by.func_70299_a(i, inv[i]); 
      PlayerUtils.sendMessage((EntityPlayer)player, "§aAh c'est mieux");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DirtIsGood$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
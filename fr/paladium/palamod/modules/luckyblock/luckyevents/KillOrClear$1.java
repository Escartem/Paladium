package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(20000L);
      if (UsersManager.getKillOrClear().contains(player)) {
        for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
          ItemStack item = player.field_71071_by.field_70462_a[i];
          if (item != null && 
            item.func_77973_b() != Item.func_150898_a((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK) && item
            .func_77973_b() != Item.func_150898_a((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK))
            player.field_71071_by.field_70462_a[i] = null; 
        } 
        player.func_70606_j(0.0F);
        player.func_70106_y();
      } 
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\KillOrClear$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
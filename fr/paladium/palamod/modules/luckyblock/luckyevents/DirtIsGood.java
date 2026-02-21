package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class DirtIsGood extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final ItemStack[] inv = new ItemStack[40];
    for (int i = 0; i < 40; i++) {
      inv[i] = player.field_71071_by.func_70301_a(i);
      player.field_71071_by.func_70299_a(i, new ItemStack(Blocks.field_150346_d));
    } 
    PlayerUtils.sendMessage((EntityPlayer)player, "§cCa sent mauvais ici nan ?");
    (new Thread(new Runnable() {
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
        })).start();
  }
  
  public String getName() {
    return "La dirt, c’est bien";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "dirt";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DirtIsGood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
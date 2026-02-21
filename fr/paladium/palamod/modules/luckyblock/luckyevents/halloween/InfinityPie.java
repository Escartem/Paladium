package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class InfinityPie extends ALuckyEvent {
  private static final String GIFT_MESSAGE = "§aVous avez reçu une récompense, essayez donc de cliquer sur le bloc halloween pour obtenir une récompense!";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = BlockHalloweenTrade.createBlockWithTimeStamp(0L);
    ItemUtils.spawnItemAtEntity((Entity)player, stack);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aVous avez reçu une récompense, essayez donc de cliquer sur le bloc halloween pour obtenir une récompense!" });
  }
  
  public String getName() {
    return "Infinity pie";
  }
  
  public String getTexture() {
    return "big_candies";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\InfinityPie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
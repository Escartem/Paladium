package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class TryAgain extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int random = (new Random()).nextInt(4);
    switch (random) {
      case 1:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3));
        return;
      case 2:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3));
        return;
      case 3:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 3));
        return;
    } 
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3));
  }
  
  public String getName() {
    return "Try Again";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "tryagain";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\TryAgain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
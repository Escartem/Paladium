package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class FishBowl extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (player.func_82169_q(3) != null) {
      PlayerUtils.dropItemStack((Entity)player, x, y, z, player.func_82169_q(3));
      player.func_70062_b(4, null);
    } 
    player.func_70062_b(4, new ItemStack(BlocksRegister.FISH_BOWL));
    PlayerUtils.sendActionBar(player, "Blop Blop");
  }
  
  public String getName() {
    return "Fish and Chips";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FishBowl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
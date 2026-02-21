package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;

public class Camouflage extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_145779_a(Item.func_150898_a(BlocksRegister.FLOWER_LUCKY), 1);
    PlayerUtils.sendActionBar(player, "§bCette fleur est a couper le souffle");
  }
  
  public String getName() {
    return "Camouflage";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Camouflage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
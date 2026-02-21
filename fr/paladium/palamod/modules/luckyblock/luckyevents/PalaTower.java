package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class PalaTower extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EventUtils.spawnAnimatedStructure(player.field_70170_p, x, y + 4, z, 1, 8, 1, BlocksRegister.BLOCK_PALADIUM, "witchMagic", 150, player);
  }
  
  public String getName() {
    return "Pala-Pillone";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PalaTower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
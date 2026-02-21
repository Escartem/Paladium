package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class DontBreak extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EventUtils.spawnStructure(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u + 4, (int)player.field_70161_v, 11, 11, 11, Blocks.field_150418_aU, player);
    EventUtils.spawnStructure(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u + 4, (int)player.field_70161_v, 9, 9, 9, Blocks.field_150350_a, player);
  }
  
  public String getName() {
    return "Ne pas casser";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DontBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
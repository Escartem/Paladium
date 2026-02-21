package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;

public class Triforce extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_147449_b(x, y, z, (Block)BlocksRegister.ENDIUM_CHEST);
    player.field_70170_p.func_72921_c(x, y, z, 1, 1);
  }
  
  public String getName() {
    return "Triforce";
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Triforce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class Trap extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_147449_b(x, y, z, (Block)Blocks.field_150486_ae);
    UsersManager.getTraps().add(new int[] { x, y, z });
  }
  
  public String getName() {
    return "C’est un traquenard !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "traquenard";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Trap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
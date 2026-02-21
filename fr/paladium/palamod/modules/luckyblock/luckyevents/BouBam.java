package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class BouBam extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int bY = y; bY < 255; bY++) {
      Block block = player.field_70170_p.func_147439_a(x, bY, z);
      if (block != null && block != Blocks.field_150350_a)
        break; 
      player.field_70170_p.func_147449_b(x, bY, z, BlocksRegister.BAMBOO);
    } 
  }
  
  public String getName() {
    return "Bou-bam";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "bou_bam";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\BouBam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
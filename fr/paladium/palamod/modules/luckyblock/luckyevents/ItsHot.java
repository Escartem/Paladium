package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class ItsHot extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int cx = x - 2; cx < x + 1; cx++) {
      for (int cz = z - 2; cz < z + 1; cz++) {
        Block b = player.field_70170_p.func_147439_a(cx, y, cz);
        if (EventUtils.canInteract((EntityPlayer)player, cx, y, cz))
          if (b != Blocks.field_150357_h)
            player.field_70170_p.func_147449_b(cx, y, cz, BlocksRegister.FAKE_LAVA);  
      } 
    } 
  }
  
  public String getName() {
    return "Attention c’est chaud";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "attention_c_est_chaud";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ItsHot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
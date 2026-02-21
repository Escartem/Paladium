package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.paladium.common.logics.EndiumChestLogic;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Rodshild extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_130014_f_().func_147449_b(x, y, z, (Block)BlocksRegister.ENDIUM_CHEST);
    EndiumChestLogic te = (EndiumChestLogic)player.func_130014_f_().func_147438_o(x, y, z);
    for (int i = 0; i < te.content.length; i++)
      te.content[i] = new ItemStack(ItemsRegister.PALADIUM_INGOT); 
    PPalaDynamique.instance.addGenerated("LUCKYBLOCK", te.content.length);
  }
  
  public String getName() {
    return "Rodshild";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 5000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Rodshild.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
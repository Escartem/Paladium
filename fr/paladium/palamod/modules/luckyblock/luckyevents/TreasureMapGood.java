package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumChestLogic;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk;

public class TreasureMapGood extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int randomX = EventUtils.random(-20000, 20000);
    int randomZ = EventUtils.random(-20000, 20000);
    (new Thread(() -> {
          Chunk chunk = player.field_70170_p.func_72938_d(randomX, randomZ);
          chunk.func_76631_c();
          int randomY = player.field_70170_p.func_72976_f(randomX, randomZ);
          ItemStack map = new ItemStack(Items.field_151121_aF);
          map.func_151001_c("§eCarte au trésor : §b" + randomX + ", " + randomY + ", " + randomZ);
          player.field_71071_by.func_70441_a(map);
          player.func_130014_f_().func_147449_b(randomX, randomY, randomZ, (Block)BlocksRegister.PALADIUM_CHEST);
          PaladiumChestLogic te = (PaladiumChestLogic)player.func_130014_f_().func_147438_o(randomX, randomY, randomZ);
          for (int i = 0; i < te.content.length; i++)
            te.content[i] = new ItemStack(ItemsRegister.PALADIUM_INGOT); 
          PPalaDynamique.instance.addGenerated("LUCKYBLOCK", te.content.length);
          chunk.func_76623_d();
        })).start();
  }
  
  public String getName() {
    return "Carte au trésor";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "carte_au_tresor_good";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\TreasureMapGood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
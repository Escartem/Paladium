package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.chunk.Chunk;

public class TreasureMapBad extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int randomX = EventUtils.random(-20000, 20000);
    int randomZ = EventUtils.random(-20000, 20000);
    (new Thread(() -> {
          Chunk chunk = player.field_70170_p.func_72938_d(randomX, randomZ);
          chunk.func_76631_c();
          int randomY = player.field_70170_p.func_72976_f(randomX, randomZ);
          ItemStack map = new ItemStack(Items.field_151121_aF);
          map.func_151001_c("§eCarte au trésor : §b" + randomX + ", " + randomY + ", " + randomZ);
          PlayerUtil.addItemStackToInventory(map, player.field_71071_by);
          player.field_70170_p.func_147449_b(randomX, randomY, randomZ, Blocks.field_150472_an);
          TileEntitySign te = (TileEntitySign)player.field_70170_p.func_147438_o(randomX, randomY, randomZ);
          te.field_145915_a = new String[] { "[LuckyBlock]", "Cheh y’a rien", "", "" };
          player.field_70170_p.func_147455_a(randomX, randomY, randomZ, (TileEntity)te);
          chunk.func_76623_d();
        })).start();
  }
  
  public String getName() {
    return "Carte au trésor";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "carte_au_tresor";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\TreasureMapBad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
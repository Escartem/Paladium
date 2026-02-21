package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CageDoree extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int posX = (int)player.field_70165_t;
    int posY = (int)player.field_70163_u;
    int posZ = (int)player.field_70161_v;
    EventUtils.spawnStructure(player.field_70170_p, posX, posY - 1, posZ, 7, 2, 7, BlocksRegister.FAKE_GOLD_BLOCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX, posY + 1, posZ, 3, 3, 3, Blocks.field_150350_a, player);
    EventUtils.spawnStructure(player.field_70170_p, posX, posY + 2, posZ + 3, 7, 5, 2, BlocksRegister.FAKE_GOLD_BLOCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX, posY + 2, posZ - 2, 7, 5, 2, BlocksRegister.FAKE_GOLD_BLOCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX, posY + 4, posZ, 7, 2, 7, BlocksRegister.FAKE_GOLD_BLOCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 3, posY + 1, posZ, 1, 3, 3, BlocksRegister.FAKE_GOLD_BLOCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX - 2, posY + 1, posZ, 1, 3, 3, BlocksRegister.FAKE_GOLD_BLOCK, player);
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.GOLDEN_CAGE));
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu es enfermé dans une cage dorée, mais tu as reçu une petite récompense pour te consoler !" });
  }
  
  public String getName() {
    return "Cage dorée";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "may/cage_doree";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\CageDoree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
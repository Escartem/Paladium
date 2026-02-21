package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PrivateChristmas extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    BlockPos pos = new BlockPos((Entity)player);
    int playerX = pos.getX();
    int playerY = pos.getY();
    int playerZ = pos.getZ();
    EventUtils.spawnStructure(player.field_70170_p, playerX, playerY + 2, playerZ, 5, 6, 5, (Block)BlocksRegister.HARDENED_OBSIDIAN, player);
    player.field_70170_p.func_147468_f(playerX, playerY, playerZ);
    player.field_70170_p.func_147468_f(playerX, playerY + 1, playerZ);
  }
  
  public String getName() {
    return "Privé de Noel";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "private_christmas";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\PrivateChristmas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
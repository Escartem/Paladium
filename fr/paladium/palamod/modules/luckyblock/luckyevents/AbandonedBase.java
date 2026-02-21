package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class AbandonedBase extends ALuckyEvent {
  private final int cubeRadius = 10;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int nX;
    for (nX = x - 5; nX < x + 5; nX++) {
      for (int i = z - 5; i < z + 5; i++)
        tryUpdateBlock(player, nX, y - 1, i); 
    } 
    for (nX = x - 5; nX < x + 5; nX++) {
      for (int nY = y - 6; nY < y + 5; nY++) {
        tryUpdateBlock(player, nX, nY, z - 5);
        tryUpdateBlock(player, nX, nY, z + 5);
      } 
    } 
    for (int nZ = z - 5; nZ < z + 5; nZ++) {
      for (int nY = y - 6; nY < y + 5; nY++) {
        tryUpdateBlock(player, x - 5, nY, nZ);
        tryUpdateBlock(player, x + 5, nY, nZ);
      } 
    } 
  }
  
  public boolean tryUpdateBlock(EntityPlayerMP player, int x, int y, int z) {
    World w = player.field_70170_p;
    Block b = w.func_147439_a(x, y, z);
    if (b == Blocks.field_150357_h)
      return false; 
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return false; 
    w.func_147449_b(x, y, z, (Block)BlocksRegister.HARDENED_OBSIDIAN);
    return true;
  }
  
  public String getName() {
    return "Base abandonnée";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "base_abandone";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\AbandonedBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
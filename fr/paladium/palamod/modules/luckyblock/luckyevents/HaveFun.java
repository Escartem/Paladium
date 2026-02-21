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

public class HaveFun extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int ax, int ay, int az) {
    int x = (int)player.field_70165_t;
    int y = (int)player.field_70163_u;
    int z = (int)player.field_70161_v;
    int nX;
    for (nX = x - 2; nX < x + 2; nX++) {
      for (int i = z - 2; i < z + 2; i++) {
        tryUpdateBlock(player, nX, y - 1, i);
        tryUpdateBlock(player, nX, y + 2, i);
      } 
    } 
    for (nX = x - 2; nX < x + 2; nX++) {
      for (int nY = y - 3; nY < y + 2; nY++) {
        tryUpdateBlock(player, nX, nY, z - 2);
        tryUpdateBlock(player, nX, nY, z + 2);
      } 
    } 
    for (int nZ = z - 2; nZ < z + 2; nZ++) {
      for (int nY = y - 3; nY < y + 2; nY++) {
        tryUpdateBlock(player, x - 2, nY, nZ);
        tryUpdateBlock(player, x + 2, nY, nZ);
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
    return "Have Fun";
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
    return "have_fun";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\HaveFun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
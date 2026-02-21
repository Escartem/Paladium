package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ItsHigh extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int nX;
    for (nX = x - 4; nX < x + 4; nX++) {
      for (int i = z - 4; i < z + 4; i++) {
        tryPlaceBlock(player, nX, 249, i, Blocks.field_150359_w);
        tryPlaceBlock(player, nX, 254, i, Blocks.field_150359_w);
      } 
    } 
    for (nX = x - 4; nX < x + 4; nX++) {
      for (int nY = 249; nY < 254; nY++) {
        tryPlaceBlock(player, nX, nY, z - 4, Blocks.field_150359_w);
        tryPlaceBlock(player, nX, nY, z + 4, Blocks.field_150359_w);
      } 
    } 
    for (int nZ = z - 4; nZ < z + 4; nZ++) {
      for (int nY = 249; nY < 254; nY++) {
        tryPlaceBlock(player, x - 4, nY, nZ, Blocks.field_150359_w);
        tryPlaceBlock(player, x + 4, nY, nZ, Blocks.field_150359_w);
      } 
    } 
    player.func_70634_a(x, 252.0D, z);
  }
  
  private boolean tryPlaceBlock(EntityPlayerMP player, int x, int y, int z, Block block) {
    World w = player.field_70170_p;
    Block b = w.func_147439_a(x, y, z);
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return false; 
    if (b == Blocks.field_150357_h)
      return false; 
    w.func_147449_b(x, y, z, block);
    return true;
  }
  
  public String getName() {
    return "Ca fait haut";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "ca_fait_haut";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ItsHigh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
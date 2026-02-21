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

public class BreakEars extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    tryUpdateBlock(player, x, y, z, Blocks.field_150451_bX);
    tryUpdateBlock(player, x, y + 1, z, BlocksRegister.ALARM);
  }
  
  public boolean tryUpdateBlock(EntityPlayerMP player, int x, int y, int z, Block block) {
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
    return "Casse Oreilles";
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
    return "casse_oreilles";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\BreakEars.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
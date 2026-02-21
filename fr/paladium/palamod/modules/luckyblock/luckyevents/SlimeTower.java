package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class SlimeTower extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    double oy;
    for (oy = pos.getY(); oy < (pos.getY() + 10); oy++) {
      if (EventUtils.canInteract((EntityPlayer)player, pos.getX(), (int)oy, pos.getZ()))
        player.field_70170_p.func_147449_b(pos.getX(), (int)oy, pos.getZ(), BlocksRegister.SLIMEPAD_BLOCK); 
      if (EventUtils.canInteract((EntityPlayer)player, pos.getX() + 1, (int)oy, pos.getZ()))
        player.field_70170_p.func_147449_b(pos.getX() + 1, (int)oy, pos.getZ(), Blocks.field_150347_e); 
      if (EventUtils.canInteract((EntityPlayer)player, pos.getX() - 1, (int)oy, pos.getZ()))
        player.field_70170_p.func_147449_b(pos.getX() - 1, (int)oy, pos.getZ(), Blocks.field_150347_e); 
      if (EventUtils.canInteract((EntityPlayer)player, pos.getX(), (int)oy, pos.getZ() + 1))
        player.field_70170_p.func_147449_b(pos.getX(), (int)oy, pos.getZ() + 1, Blocks.field_150347_e); 
      if (EventUtils.canInteract((EntityPlayer)player, pos.getX(), (int)oy, pos.getZ() - 1))
        player.field_70170_p.func_147449_b(pos.getX(), (int)oy, pos.getZ() - 1, Blocks.field_150347_e); 
    } 
    if (EventUtils.canInteract((EntityPlayer)player, pos.getX(), pos.getY() + 10, pos.getZ()))
      player.field_70170_p.func_147449_b(pos.getX(), pos.getY() + 10, pos.getZ(), (Block)BlocksRegister.SPIKE_PALADIUM); 
    player.func_70634_a(pos.getX() + 0.5D, (pos.getY() + 1), pos.getZ() + 0.5D);
  }
  
  public String getName() {
    return "Tour de slime";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SlimeTower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
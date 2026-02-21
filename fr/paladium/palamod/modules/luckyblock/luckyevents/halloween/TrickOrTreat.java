package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHopperHalloween;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class TrickOrTreat extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_130014_f_().func_147449_b(x, y, z, (Block)BlocksRegister.hopperHallowwen);
    TileEntity tile = player.func_130014_f_().func_147438_o(x, y, z);
    if (tile instanceof TileEntityHopperHalloween)
      ((TileEntityHopperHalloween)tile).setTarget((EntityPlayer)player); 
  }
  
  public String getName() {
    return "Trick or treat";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\TrickOrTreat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
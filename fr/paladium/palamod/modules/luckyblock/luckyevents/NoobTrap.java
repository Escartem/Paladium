package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class NoobTrap extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return; 
    if (player.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150357_h)
      return; 
    player.field_70170_p.func_147449_b(x, y, z, BlocksRegister.JAWSTRAP);
  }
  
  public String getName() {
    return "Piège à Noob";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "piege_a_noob";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\NoobTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
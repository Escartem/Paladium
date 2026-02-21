package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Banana extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
      World world = player.field_70170_p;
      int i;
      for (i = 0; i <= 3; i++)
        world.func_147449_b(x, y + i, z, BlocksRegister.WOOD_BANANIER); 
      for (i = -1; i <= 1; i += 2)
        world.func_147449_b(x + i, y + 4, z, BlocksRegister.LEAVE_BANANIER); 
      for (i = -1; i <= 1; i += 2)
        world.func_147449_b(x, y + 4, z + i, BlocksRegister.LEAVE_BANANIER); 
      for (i = -2; i <= 2; i += 4) {
        int random = (new Random()).nextInt(2);
        world.func_147449_b(x + random, y + 3, z + i, BlocksRegister.LEAVE_BANANIER);
      } 
      for (i = -2; i <= 2; i += 4) {
        int random = (new Random()).nextInt(2);
        world.func_147449_b(x + i, y + 3, z + random, BlocksRegister.LEAVE_BANANIER);
      } 
    } 
  }
  
  public String getName() {
    return "Fruit Exotique";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "banana";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Banana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
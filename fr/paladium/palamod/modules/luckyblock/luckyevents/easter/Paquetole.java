package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Paquetole extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 10; i++) {
      BlockPos pos = generateEggCoordinates((EntityPlayer)player, player.field_70170_p, x, y, z);
      if (pos != null)
        player.field_70170_p.func_147449_b(pos.getX(), pos.getY(), pos.getZ(), BlocksRegister.YELLOW_EASTER_EGG); 
    } 
  }
  
  private BlockPos generateEggCoordinates(EntityPlayer player, World world, int x, int y, int z) {
    int testCpt = 0;
    while (testCpt < 30) {
      BlockPos pos = EventUtils.getRandomCoordsWithinRadius(x, y, z, 10);
      for (int yOffset = -1; yOffset < 2; yOffset++) {
        pos.setY((pos.getY() + yOffset));
        if (world.func_147437_c(pos.getX(), pos.getY(), pos.getZ()) && !world.func_147437_c(pos.getX(), pos.getY() - 1, pos.getZ()) && EventUtils.canInteract(player, pos.getX(), pos.getY(), pos.getZ()))
          return pos; 
      } 
      testCpt++;
    } 
    return null;
  }
  
  public String getName() {
    return "Pâquetole";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 600;
  }
  
  public String getTexture() {
    return "easter/paquetole";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\Paquetole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
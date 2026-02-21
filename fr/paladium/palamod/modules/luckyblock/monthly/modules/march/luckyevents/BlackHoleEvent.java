package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntityBlackHole;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RelativeDirection;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlackHoleEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Trou noir";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 110;
  
  private static final String TEXTURE_PATH = "march/black_hole";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.func_130014_f_();
    DoubleLocation blackHoleLocation = findLocation(world, player);
    EntityBlackHole blackHole = new EntityBlackHole(world, player);
    if (blackHoleLocation == null) {
      blackHole.func_70107_b(x, y, z);
    } else {
      blackHole.func_70107_b(blackHoleLocation.getX(), blackHoleLocation.getY(), blackHoleLocation.getZ());
    } 
    world.func_72838_d((Entity)blackHole);
  }
  
  public String getName() {
    return "Trou noir";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 110;
  }
  
  public String getTexture() {
    return "march/black_hole";
  }
  
  public DoubleLocation findLocation(World world, EntityPlayerMP player) {
    double distance = 5.0D;
    DoubleLocation playerLocation = new DoubleLocation((Entity)player);
    while (true) {
      DoubleLocation direction = RelativeDirection.getRelativeLocation(playerLocation, RelativeDirection.FRONT, distance);
      if (isAir(world, direction.getBlockX(), direction.getBlockY(), direction.getBlockZ()) && 
        isAir(world, direction.getBlockX(), direction.getBlockY() + 1, direction.getBlockZ()))
        return direction; 
      distance--;
      if (distance <= 0.0D)
        return playerLocation; 
    } 
  }
  
  private boolean isAir(World world, int x, int y, int z) {
    return world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\BlackHoleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
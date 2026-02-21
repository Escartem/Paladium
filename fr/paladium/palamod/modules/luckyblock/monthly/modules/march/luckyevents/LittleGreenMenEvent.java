package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntityZombieAstronaut;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.DoubleCuboid;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class LittleGreenMenEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Les petits hommes verts";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 90;
  
  private static final String TEXTURE_PATH = "march/little_green_men";
  
  public static final String STRUCTURE_NAME = "march";
  
  private static final int SPAWN_AMOUNT = 20;
  
  private static final int RADIUS = 3;
  
  private static final int TRY_AMOUNT = 50;
  
  private static final String ACTIVATE_MESSAGE = "§c20 §emini-zombies ont été invoqués autour de vous !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int spawnAmount = 20;
    World world = player.field_70170_p;
    DoubleCuboid cuboid = getCuboid(world, player);
    int i;
    for (i = 0; i < 20; i++) {
      for (int j = 0; j < 50; j++) {
        int randomX = (int)Math.floor(cuboid.getRandomX());
        int randomY = (int)Math.floor(cuboid.getRandomY());
        int randomZ = (int)Math.floor(cuboid.getRandomZ());
        if (world.func_147437_c(randomX, randomY, randomZ)) {
          world.func_72838_d((Entity)new EntityZombieAstronaut(world, randomX, randomY, randomZ));
          spawnAmount--;
          break;
        } 
      } 
    } 
    for (i = 0; i < spawnAmount; i++)
      world.func_72838_d((Entity)new EntityZombieAstronaut(world, x, y, z)); 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§c20 §emini-zombies ont été invoqués autour de vous !" });
  }
  
  public String getName() {
    return "Les petits hommes verts";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 90;
  }
  
  public String getTexture() {
    return "march/little_green_men";
  }
  
  private DoubleCuboid getCuboid(World world, EntityPlayerMP player) {
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    return new DoubleCuboid(world, x + 3.0D, y, z + 3.0D, x - 3.0D, y, z - 3.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\LittleGreenMenEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
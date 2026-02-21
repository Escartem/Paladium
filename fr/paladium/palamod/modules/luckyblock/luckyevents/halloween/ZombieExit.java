package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.DoubleCuboid;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.Interval;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class ZombieExit extends ALuckyEvent {
  private static final Interval INTERVAL = new Interval(6, 12);
  
  private static final int RADIUS = 3;
  
  private static final int TRY_AMOUNT = 50;
  
  public static final String CUSTOM_NAME = "§bZombie de sortie";
  
  private static final String ACTIVATE_MESSAGE = "§c%s §eZombies ont été invoqués autour de vous !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int amount = INTERVAL.getRandom();
    int spawnAmount = amount;
    World world = player.field_70170_p;
    DoubleCuboid cuboid = getCuboid(world, player);
    int i;
    for (i = 0; i < amount; i++) {
      for (int j = 0; j < 50; j++) {
        int randomX = (int)Math.floor(cuboid.getRandomX());
        int randomY = (int)Math.floor(cuboid.getRandomY());
        int randomZ = (int)Math.floor(cuboid.getRandomZ());
        if (world.func_147437_c(randomX, randomY, randomZ)) {
          EntityZombie zombie = new EntityZombie(world);
          zombie.func_70107_b(randomX, randomY, randomZ);
          zombie.func_94058_c("§bZombie de sortie");
          zombie.func_94061_f(true);
          world.func_72838_d((Entity)zombie);
          spawnAmount--;
          break;
        } 
      } 
    } 
    for (i = 0; i < spawnAmount; i++) {
      EntityZombie zombie = new EntityZombie(world);
      zombie.func_70107_b(x, y, z);
      zombie.func_94058_c("§bZombie de sortie");
      zombie.func_94061_f(true);
      world.func_72838_d((Entity)zombie);
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("§c%s §eZombies ont été invoqués autour de vous !", new Object[] { Integer.valueOf(amount) }) });
  }
  
  private DoubleCuboid getCuboid(World world, EntityPlayerMP player) {
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    return new DoubleCuboid(world, x + 3.0D, y, z + 3.0D, x - 3.0D, y, z - 3.0D);
  }
  
  public String getName() {
    return "Zombie de sortie";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "tu_n_es_plus_toi_meme";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\ZombieExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
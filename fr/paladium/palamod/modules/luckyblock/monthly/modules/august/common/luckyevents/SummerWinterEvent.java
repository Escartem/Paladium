package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SummerWinterEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Team été ou team hiver ?";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 500;
  
  private static final String TEXTURE_PATH = "august/summer_winter";
  
  public static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/summer_winter.png";
  
  public static final long DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  public static SummerWinterEvent INSTANCE;
  
  public SummerWinterEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    EntityNPC npc = new EntityNPC(world, "Questionneur", "palamod:textures/entity/npc/summer_winter.png", x, (y - 2), z, DURATION, DURATION, true);
    world.func_72838_d((Entity)npc);
  }
  
  public void setBlocks(Location location, EntityPlayer player, int radius, Block block) {
    World world = location.getWorld();
    int x = location.getBlockX();
    int y = location.getBlockY();
    int z = location.getBlockZ();
    Cuboid cuboid = new Cuboid(location.getWorld(), (x - radius), y, (z - radius), (x + radius), y, (z + radius));
    for (Location newLocation : cuboid.getLocations()) {
      Block newBlock = newLocation.getBlock();
      int newX = newLocation.getBlockX();
      int newY = newLocation.getBlockY();
      int newZ = newLocation.getBlockZ();
      if (!newBlock.isAir((IBlockAccess)world, newX, newY, newZ))
        continue; 
      if (!EventUtils.canInteract(player, newX, newY, newZ))
        continue; 
      if (!EventUtils.canInteract(player, newX, newY - 1, newZ))
        continue; 
      world.func_147449_b(newX, newY, newZ, block);
    } 
  }
  
  public String getName() {
    return "Team été ou team hiver ?";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "august/summer_winter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SummerWinterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
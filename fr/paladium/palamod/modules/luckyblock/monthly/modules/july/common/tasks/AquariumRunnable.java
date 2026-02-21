package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks;

import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.AquariumStructure;
import java.util.TimerTask;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class AquariumRunnable extends TimerTask {
  private final AquariumStructure structure;
  
  private EntityDolphin entity;
  
  public AquariumRunnable(AquariumStructure structure) {
    this.structure = structure;
    this.entity = spawnShark(structure.getPlayer());
  }
  
  public void run() {
    EntityPlayer player = this.structure.getPlayer();
    if (player == null || this.entity == null || this.entity.field_70128_L) {
      stop();
      return;
    } 
    Cuboid cuboid = this.structure.getCuboid();
    Location currentSharkLocation = new Location((Entity)this.entity);
    if (!cuboid.contains(currentSharkLocation)) {
      Location newSharkLocation = this.structure.findRandomSharkLocation();
      this.entity.func_70634_a(newSharkLocation.getX(), newSharkLocation.getY(), newSharkLocation.getZ());
    } 
    if (player.func_70068_e((Entity)this.entity) <= 3.0D) {
      this.structure.teleportAtWinPlace(player);
      stop();
    } 
  }
  
  private void stop() {
    if (this.structure != null)
      this.structure.onExpire(); 
    if (this.entity != null && !this.entity.field_70128_L) {
      this.entity.func_70106_y();
      this.entity = null;
    } 
    cancel();
  }
  
  private EntityDolphin spawnShark(EntityPlayer player) {
    Location sharkLocation = this.structure.findRandomSharkLocation();
    World world = sharkLocation.getWorld();
    EntityDolphin shark = new EntityDolphin(world);
    shark.func_70634_a(sharkLocation.getX(), sharkLocation.getY(), sharkLocation.getZ());
    world.func_72838_d((Entity)shark);
    return shark;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tasks\AquariumRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
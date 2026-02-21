package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures;

import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.VigilanceDutyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.VigilanceRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.ITasked;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class VigilanceStructure extends AbstractStructure implements ITasked {
  public static final long PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(1L);
  
  private static final String TELEPORT_MESSAGE = "&aVous avez été téléporté sur votre devoir de vigie, veuillez rester immobile pour obtenir votre récompense !";
  
  private Timer timer;
  
  public VigilanceStructure(EntityPlayer player) {
    super(0, 0, 50, new Location((Entity)player), -1L, player);
  }
  
  public void init() {
    Location spawnLocation = getSpawnLocation();
    fill(BlocksRegister.EXPIRABLE_BEDROCK, getCuboid().getLocations(), true);
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    EntityPlayer player = getPlayer();
    Location topLocation = getHighestLocation();
    TeleportUtils.teleport(player, topLocation
        
        .getX(), topLocation.getY() + 1.0D, topLocation.getZ(), topLocation.getYaw(), topLocation.getPitch());
    MonthlyUtils.sendMessage(player, new String[] { "&aVous avez été téléporté sur votre devoir de vigie, veuillez rester immobile pour obtenir votre récompense !" });
    startTask();
    return ret;
  }
  
  public void onExpire() {
    super.onExpire();
    EntityPlayer player = getPlayer();
    Location spawnLocation = getSpawnLocation();
    if (player == null)
      return; 
    player.field_70143_R = 0.0F;
    TeleportUtils.teleport(player, spawnLocation
        
        .getX(), spawnLocation.getY(), spawnLocation.getZ(), spawnLocation
        .getYaw(), spawnLocation.getPitch());
    MonthlyUtils.stopHeliosEvent((EntityPlayerMP)player, VigilanceDutyEvent.class);
  }
  
  public boolean canSpawn(Location location) {
    return (StructureUtils.isAirAtLocation(location) && EventUtils.canInteract(getPlayer(), location));
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new VigilanceRunnable(
          getPlayer(), this), 0L, PERIOD_MILLIS);
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  private Location getHighestLocation() {
    return getPlacedBlocks().stream()
      .map(LocatedBlock::getLocation)
      .max((first, second) -> (int)(first.getY() - second.getY()))
      .orElse(null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\structures\VigilanceStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
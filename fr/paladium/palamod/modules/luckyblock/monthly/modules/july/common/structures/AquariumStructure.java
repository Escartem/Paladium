package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers.JulyConfigManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.ServerLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.AquariumRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.ITasked;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class AquariumStructure extends AbstractStructure implements ITasked {
  private Timer timer;
  
  public AquariumStructure(EntityPlayer player) {
    super(8, 8, 14, new Location((Entity)player), -1L, true, false, player.func_110124_au());
  }
  
  public void init() {
    fill(Blocks.field_150355_j, getCuboid().getLocations(), true);
    fillBorders(Blocks.field_150359_w, true);
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    EntityPlayer player = getPlayer();
    Location centerLocation = getCuboid().getCenter();
    TeleportUtils.teleport(player, centerLocation
        
        .getX(), centerLocation.getY() - 4.0D, centerLocation.getZ());
    startTask();
    return ret;
  }
  
  public void onExpire() {
    super.onExpire();
  }
  
  public boolean canSpawn(Location location) {
    return (StructureUtils.isAirAtLocation(location) && EventUtils.canInteract(getPlayer(), location));
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new AquariumRunnable(this), 0L, 500L);
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  public void teleportAtWinPlace(EntityPlayer player) {
    List<ServerLocation> serverLocations = JulyConfigManager.getInstance().getConfig().getAquariumTeleportationLocation();
    String serverName = CommonModule.getInstance().getConfig().getServerName();
    Optional<ServerLocation> optionalServerLocation = serverLocations.stream().filter(serverLocation -> serverLocation.getServerName().equalsIgnoreCase(serverName)).findFirst();
    if (!optionalServerLocation.isPresent()) {
      MonthlyUtils.sendMessage(player, new String[] { "&cAucun emplacement n'a été trouvé sur ce serveur." });
      return;
    } 
    DoubleLocation doubleLocation = ((ServerLocation)optionalServerLocation.get()).getLocation();
    MonthlyUtils.sendMessage(player, new String[] { "&aVous avez été téléporté à l'emplacement de l'événement." });
    TeleportUtils.teleport(player, doubleLocation
        
        .getX(), doubleLocation.getY(), doubleLocation.getZ());
  }
  
  public Location findRandomSharkLocation() {
    return getCuboid().getCenter();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\structures\AquariumStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
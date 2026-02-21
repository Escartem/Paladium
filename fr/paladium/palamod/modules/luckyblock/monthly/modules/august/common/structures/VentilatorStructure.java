package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures;

import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockVentilator;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.VentilatorDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks.VentilatorRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityVentilator;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.ITasked;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class VentilatorStructure extends AbstractStructure implements ITasked {
  private Location ventilatorLocation;
  
  private Location spawnLocation;
  
  private Timer timer;
  
  public VentilatorStructure(EntityPlayer player) {
    super(10, 4, 4, (new Location((Entity)player))
        
        .clone(0.0D, 1.0D, 0.0D), 
        System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30L), false, true, player
        .func_110124_au());
    this.spawnLocation = new Location((Entity)player);
  }
  
  public void init() {
    Location spawnLocation = getMiddle(1).add(2.0D, 0.0D, 0.0D);
    Location middleEast = getMiddle(3);
    EntityPlayer player = getPlayer();
    fillBorders(BlocksRegister.EXPIRABLE_BEDROCK, true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.VENTILATOR).location(middleEast).build(), true);
    this.ventilatorLocation = middleEast;
    TeleportUtils.teleport(player, spawnLocation.getBlockX(), spawnLocation.getBlockY(), spawnLocation.getBlockZ());
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    if (!ret)
      return false; 
    World world = this.ventilatorLocation.getWorld();
    EntityPlayerMP player = (EntityPlayerMP)getPlayer();
    int x = this.ventilatorLocation.getBlockX();
    int y = this.ventilatorLocation.getBlockY();
    int z = this.ventilatorLocation.getBlockZ();
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityVentilator))
      return false; 
    BlockVentilator block = (BlockVentilator)world.func_147439_a(x, y, z);
    TileEntityVentilator ventilator = (TileEntityVentilator)tile;
    block.rotate(player, world, x, y, z, ventilator, VentilatorDirection.WEST);
    block.changeStatus(world, x, y, z, ventilator);
    startTask();
    return ret;
  }
  
  public void onExpire() {
    super.onExpire();
    TeleportUtils.teleport(getPlayer(), this.spawnLocation.getBlockX(), this.spawnLocation.getBlockY(), this.spawnLocation.getBlockZ());
  }
  
  public boolean canSpawn(Location location) {
    return (EventUtils.canInteract(getPlayer(), location) && StructureUtils.isAirAtLocation(location));
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new VentilatorRunnable(this), 0L, 500L);
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  public TileEntityVentilator getVentilator() {
    World world = this.ventilatorLocation.getWorld();
    int x = this.ventilatorLocation.getBlockX();
    int y = this.ventilatorLocation.getBlockY();
    int z = this.ventilatorLocation.getBlockZ();
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityVentilator))
      return null; 
    TileEntityVentilator ventilator = (TileEntityVentilator)tile;
    if (!ventilator.isActive())
      return null; 
    return ventilator;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\structures\VentilatorStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
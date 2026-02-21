package fr.paladium.palamod.modules.luckyblock.monthly.structures.impl;

import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.ITasked;
import fr.paladium.palamod.modules.luckyblock.monthly.tasks.BiiCodeRunnable;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class BiicodeStructure extends AbstractStructure implements ITasked {
  public static final int SQUARED_SIZE = 6;
  
  private final int[] codes;
  
  private final ItemStack[] rewards;
  
  private Timer timer;
  
  public int[] getCodes() {
    return this.codes;
  }
  
  public ItemStack[] getRewards() {
    return this.rewards;
  }
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public BiicodeStructure(EntityPlayer player, int[] codes, ItemStack[] rewards) {
    super(6, 6, 6, (new Location((Entity)player))
        
        .clone(-3.0D, 1.0D, -3.0D), 
        System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5L), player
        .func_110124_au());
    this.codes = codes;
    this.rewards = rewards;
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    AtomicInteger index = new AtomicInteger();
    getBiiCodeLocations().forEach(location -> {
          TileEntity tile = location.getWorld().func_147438_o(location.getBlockX(), location.getBlockY(), location.getBlockZ());
          if (!(tile instanceof TileEntityDigicode))
            return; 
          TileEntityDigicode tileDigicode = (TileEntityDigicode)tile;
          if (index.get() == 0)
            tileDigicode.setMaster(); 
          tileDigicode.setCode(this.codes[index.get()]);
          tileDigicode.forceDiplayNumber(1);
          index.getAndIncrement();
        });
    Location center = getCuboid().getCenter().clone(0.0D, 4.0D, 0.0D);
    TeleportUtils.teleport(getPlayer(), center.getX(), center.getY(), center.getZ());
    startTask();
    return ret;
  }
  
  public void init() {
    fillBorders(BlocksRegister.EXPIRABLE_BEDROCK, true);
    Location northCenter = getMiddle(2);
    addBlock(
        LocatedBlock.builder()
        .block(BlocksRegister.DIGICODE)
        .location(northCenter.clone(-1.0D, 0.0D, 0.0D))
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .block(BlocksRegister.DIGICODE)
        .location(northCenter.clone(-2.0D, 0.0D, 0.0D))
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .block(BlocksRegister.DIGICODE)
        .location(northCenter.clone(-3.0D, 0.0D, 0.0D))
        .build(), true);
  }
  
  public Location getChestLocation() {
    Cuboid cuboid = getCuboid();
    Location center = cuboid.getCenter();
    center.setY(cuboid.getMinY() + 1.0D);
    return center;
  }
  
  public List<Location> getDoorBlocks() {
    Location minLocation = getMinLocation(getCuboid().getNorthBorders());
    return Arrays.asList(new Location[] { minLocation
          .clone(1.0D, 2.0D, 0.0D), minLocation
          .clone(1.0D, 1.0D, 0.0D) });
  }
  
  public List<Location> getBiiCodeLocations() {
    return (List<Location>)getPlacedBlocks().stream()
      .filter(locatedBlock -> locatedBlock.getBlock().equals(BlocksRegister.DIGICODE))
      .map(LocatedBlock::getLocation)
      .collect(Collectors.toList());
  }
  
  public Optional<TileEntityDigicode> getMaster() {
    return getBiiCodeLocations().stream()
      .map(location -> location.getWorld().func_147438_o(location.getBlockX(), location.getBlockY(), location.getBlockZ()))
      
      .filter(tile -> tile instanceof TileEntityDigicode)
      .map(tile -> (TileEntityDigicode)tile)
      .filter(TileEntityDigicode::isMaster)
      .findFirst();
  }
  
  public Optional<TileEntityChest> getChest() {
    Location chestLocation = getChestLocation();
    TileEntity tile = chestLocation.getWorld().func_147438_o(chestLocation
        .getBlockX(), chestLocation.getBlockY(), chestLocation.getBlockZ());
    if (!(tile instanceof TileEntityChest))
      return Optional.empty(); 
    return Optional.of((TileEntityChest)tile);
  }
  
  public boolean canSpawn(Location location) {
    return (EventUtils.canInteract(getPlayer(), location) && StructureUtils.isAirAtLocation(location));
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new BiiCodeRunnable(this), 0L, 500L);
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\structures\impl\BiicodeStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
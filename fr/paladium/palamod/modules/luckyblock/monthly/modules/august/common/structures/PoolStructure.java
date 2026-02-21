package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks.PoolRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.ITasked;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PoolStructure extends AbstractStructure implements ITasked {
  public static int MAX_MONEY_AMOUNT = 20;
  
  public static final String FUZE_SKIN_PATH = "palamod:textures/entity/npc/fuzeiii.png";
  
  public static final String GOLDORAK_SKIN_PATH = "palamod:textures/entity/npc/goldorak85.png";
  
  public static final String LORN_SKIN_PATH = "palamod:textures/entity/npc/lorn87.png";
  
  private Timer timer;
  
  private Random random;
  
  private List<UUID> itemsList;
  
  private List<EntityNPC> npcs;
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public Random getRandom() {
    return this.random;
  }
  
  public List<UUID> getItemsList() {
    return this.itemsList;
  }
  
  public List<EntityNPC> getNpcs() {
    return this.npcs;
  }
  
  public PoolStructure(EntityPlayer player) {
    super(20, 12, 10, (new Location((Entity)player))
        
        .add(0.0D, -10.0D, 0.0D), 
        System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(120L), false, true, player
        .func_110124_au());
    this.random = new Random();
    this.itemsList = new ArrayList<>();
    this.npcs = new ArrayList<>();
  }
  
  public void init() {
    fillBorders(Blocks.field_150359_w, true);
    fill(Blocks.field_150350_a, getCuboid().getTopBorders(), true);
    fill(Blocks.field_150355_j, getCuboid().getLocations(), false);
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    if (!ret)
      return false; 
    List<Location> waterLocations = getWaterLocations();
    for (int i = 0; i < MAX_MONEY_AMOUNT; i++) {
      Location location = pickupRandomLocation(waterLocations);
      this.itemsList.add(spawnItemInWorld(location
            .getWorld(), location
            .getX(), location.getY(), location.getZ(), new ItemStack(ItemsRegister.AUGUST_FAKE_MONEY))
          
          .func_110124_au());
    } 
    spawnNpcs(waterLocations);
    startTask();
    return ret;
  }
  
  public EntityItem spawnItemInWorld(World world, double x, double y, double z, ItemStack stack) {
    float f = 0.7F;
    double xV = (world.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
    double yV = (world.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
    double zV = (world.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
    EntityItem entityitem = new EntityItem(world, x + xV, y + yV, z + zV, stack);
    world.func_72838_d((Entity)entityitem);
    return entityitem;
  }
  
  public void spawnNpcs(List<Location> waterLocations) {
    Location firstLocation = pickupRandomHighestLocation(waterLocations);
    Location secondLocation = pickupRandomHighestLocation(waterLocations);
    Location thirdLocation = pickupRandomHighestLocation(waterLocations);
    World world = firstLocation.getWorld();
    long expiration = System.currentTimeMillis() - getExpirationMillis();
    EntityNPC fuze = new EntityNPC(world, "Roi des pirates", "palamod:textures/entity/npc/fuzeiii.png", firstLocation.getBlockX(), (firstLocation.getBlockY() - 3), firstLocation.getBlockZ(), expiration, expiration, true);
    EntityNPC goldorak = new EntityNPC(world, "Roi des pirates", "palamod:textures/entity/npc/goldorak85.png", secondLocation.getX(), secondLocation.getY() - 3.0D, secondLocation.getZ(), expiration, expiration, true);
    EntityNPC lorn = new EntityNPC(world, "Roi des pirates", "palamod:textures/entity/npc/lorn87.png", thirdLocation.getX(), thirdLocation.getY() - 3.0D, thirdLocation.getZ(), expiration, expiration, true);
    world.func_72838_d((Entity)fuze);
    world.func_72838_d((Entity)goldorak);
    world.func_72838_d((Entity)lorn);
    this.npcs.add(fuze);
    this.npcs.add(goldorak);
    this.npcs.add(lorn);
  }
  
  public Location pickupRandomLocation(List<Location> locations) {
    return locations.get(this.random.nextInt(locations.size()));
  }
  
  public Location pickupRandomHighestLocation(List<Location> locations) {
    List<Location> highestLocations = (List<Location>)locations.stream().filter(location -> (location.getY() == getCuboid().getMaxY() || location.getY() == getCuboid().getMaxY() - 1.0D)).collect(Collectors.toList());
    return highestLocations.get(this.random.nextInt(highestLocations.size()));
  }
  
  public List<Location> getWaterLocations() {
    return (List<Location>)getPlacedBlocks().stream()
      .filter(locatedBlock -> (locatedBlock.getBlock() == Blocks.field_150355_j))
      .map(LocatedBlock::getLocation)
      .collect(Collectors.toList());
  }
  
  public void onExpire() {
    super.onExpire();
    this.npcs.forEach(npc -> {
          if (npc != null && !npc.field_70128_L)
            npc.func_70106_y(); 
        });
  }
  
  public boolean canSpawn(Location location) {
    return EventUtils.canInteract(getPlayer(), location);
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new PoolRunnable(this), 0L, 1000L);
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\structures\PoolStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
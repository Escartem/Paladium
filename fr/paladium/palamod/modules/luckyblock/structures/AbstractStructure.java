package fr.paladium.palamod.modules.luckyblock.structures;

import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.StructureManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AbstractStructure {
  private static final String STRUCTURE_SPAWNED_SUCCESSFULLY = "§aLa structure a été placée avec succès. veuillez regarder autour de vous.";
  
  private int width;
  
  private int length;
  
  private int height;
  
  private boolean destroyable;
  
  private Cuboid cuboid;
  
  private Location spawnLocation;
  
  private long expirationMillis;
  
  private boolean ignoreSpawnLocation;
  
  private List<LocatedBlock> placedBlocks;
  
  private UUID playerUniqueId;
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public void setLength(int length) {
    this.length = length;
  }
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  public void setDestroyable(boolean destroyable) {
    this.destroyable = destroyable;
  }
  
  public void setCuboid(Cuboid cuboid) {
    this.cuboid = cuboid;
  }
  
  public void setSpawnLocation(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setIgnoreSpawnLocation(boolean ignoreSpawnLocation) {
    this.ignoreSpawnLocation = ignoreSpawnLocation;
  }
  
  public void setPlacedBlocks(List<LocatedBlock> placedBlocks) {
    this.placedBlocks = placedBlocks;
  }
  
  public void setPlayerUniqueId(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public int getLength() {
    return this.length;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public boolean isDestroyable() {
    return this.destroyable;
  }
  
  public Cuboid getCuboid() {
    return this.cuboid;
  }
  
  public Location getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public boolean isIgnoreSpawnLocation() {
    return this.ignoreSpawnLocation;
  }
  
  public List<LocatedBlock> getPlacedBlocks() {
    return this.placedBlocks;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public AbstractStructure(int width, int length, int height, Location spawnLocation, long expirationMillis, EntityPlayer player) {
    this(width, length, height, spawnLocation, expirationMillis, false, false, player.func_110124_au());
  }
  
  public AbstractStructure(int width, int length, int height, Location spawnLocation, long expirationMillis, UUID playerUniqueId) {
    this(width, length, height, spawnLocation, expirationMillis, false, false, playerUniqueId);
  }
  
  public AbstractStructure(int width, int length, int height, Location spawnLocation, long expirationMillis, boolean middle, boolean ignoreSpawnLocation, UUID playerUniqueId) {
    this.width = width;
    this.length = length;
    this.height = height;
    this.spawnLocation = spawnLocation;
    this.cuboid = toCuboid();
    this.expirationMillis = expirationMillis;
    this.placedBlocks = new ArrayList<>();
    this.ignoreSpawnLocation = ignoreSpawnLocation;
    this.playerUniqueId = playerUniqueId;
    this.destroyable = false;
    if (middle) {
      double tmpY = this.spawnLocation.getY();
      this.spawnLocation = this.cuboid.getCenter();
      this.spawnLocation.setX(this.spawnLocation.getX() - this.length);
      this.spawnLocation.setY(tmpY);
      this.spawnLocation.setZ(this.spawnLocation.getZ() - this.width);
      this.cuboid = toCuboid();
    } 
    init();
  }
  
  public boolean canSpawn() {
    StructureManager manager = StructureManager.getInstance();
    for (LocatedBlock placedBlock : this.placedBlocks) {
      if (manager.getStructure(placedBlock.getLocation()).isPresent())
        return false; 
      if (!canSpawn(placedBlock.getLocation()))
        return false; 
    } 
    return true;
  }
  
  public boolean spawn() {
    if (this.expirationMillis != -1L)
      StructureManager.getInstance().addStructure(this); 
    return spawn(false);
  }
  
  public boolean spawn(boolean check) {
    init();
    if (getPlayer() == null)
      return false; 
    if (check && !canSpawn())
      return false; 
    for (LocatedBlock located : this.placedBlocks) {
      Location location = located.getLocation();
      Block block = located.getBlock();
      location.getWorld().func_147449_b(location
          .getBlockX(), location
          .getBlockY(), location
          .getBlockZ(), block);
      located.updateExpirationMillis(this.expirationMillis, this);
    } 
    MonthlyUtils.sendMessage(getPlayer(), new String[] { "§aLa structure a été placée avec succès. veuillez regarder autour de vous." });
    return true;
  }
  
  public void onExpire() {
    World world = this.spawnLocation.getWorld();
    this.placedBlocks.forEach(located -> world.func_147468_f(located.getLocation().getBlockX(), located.getLocation().getBlockY(), located.getLocation().getBlockZ()));
    StructureManager.getInstance().remove(this);
  }
  
  public void update(Location location, Block block) {
    Optional<LocatedBlock> result = getLocated(location);
    if (!result.isPresent())
      return; 
    LocatedBlock locatedBlock = result.get();
    locatedBlock.setBlock(block);
    location.getWorld().func_147449_b(location
        .getBlockX(), location
        .getBlockY(), location
        .getBlockZ(), block);
  }
  
  public void onExpire(TileEntity tile) {
    tile.func_145831_w().func_147475_p(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    tile.func_145831_w().func_147449_b(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, Blocks.field_150350_a);
  }
  
  public void update(Location location, Block block, long expirationMillis) {
    Optional<LocatedBlock> result = getLocated(location);
    if (!result.isPresent())
      return; 
    LocatedBlock locatedBlock = result.get();
    locatedBlock.setBlock(block);
    locatedBlock.updateExpirationMillis(expirationMillis, this);
    location.getWorld().func_147449_b(location
        .getBlockX(), location
        .getBlockY(), location
        .getBlockZ(), block);
  }
  
  public void fillBorders(Block block, boolean replace) {
    List<Location> borders = this.cuboid.getBorders();
    borders.forEach(location -> addBlock(LocatedBlock.builder().location(location).block(block).build(), replace));
  }
  
  public void fill(Block block, List<Location> locations, boolean replace) {
    locations.forEach(location -> addBlock(LocatedBlock.builder().location(location).block(block).build(), replace));
  }
  
  public void addBlock(LocatedBlock locatedBlock, boolean replace) {
    Optional<LocatedBlock> result = getLocated(locatedBlock.getLocation());
    if (result.isPresent())
      if (replace) {
        this.placedBlocks.remove(result.get());
      } else {
        return;
      }  
    this.placedBlocks.add(locatedBlock);
  }
  
  public void spawnBlock(LocatedBlock locatedBlock) {
    Optional<LocatedBlock> result = getLocated(locatedBlock.getLocation());
    if (result.isPresent())
      this.placedBlocks.remove(result.get()); 
    this.placedBlocks.add(locatedBlock);
    Location location = locatedBlock.getLocation();
    if (locatedBlock.getBlock().equals(Blocks.field_150350_a)) {
      location.getWorld().func_147468_f(location
          .getBlockX(), location
          .getBlockY(), location
          .getBlockZ());
      return;
    } 
    location.getWorld().func_147449_b(location
        .getBlockX(), location
        .getBlockY(), location
        .getBlockZ(), locatedBlock
        .getBlock());
  }
  
  public Optional<LocatedBlock> getLocated(Location location) {
    return this.placedBlocks.stream()
      .filter(locatedBlock -> locatedBlock.getLocation().equals(location))
      .findFirst();
  }
  
  private Cuboid toCuboid() {
    if (this.width == 0 && this.length == 0)
      return new Cuboid(this.spawnLocation.clone(), this.spawnLocation.clone(0.0D, this.height, 0.0D)); 
    return new Cuboid(new Location(this.spawnLocation
          .getWorld(), this.spawnLocation
          .getBlockX(), this.spawnLocation
          .getBlockY(), this.spawnLocation
          .getBlockZ()), new Location(this.spawnLocation
          
          .getWorld(), (this.spawnLocation
          .getBlockX() + this.width), (this.spawnLocation
          .getBlockY() + this.height), (this.spawnLocation
          .getBlockZ() + this.length)));
  }
  
  public String[] getConditions() {
    return new String[0];
  }
  
  public Location getMinLocation(List<Location> faces) {
    int x = (int)faces.stream().mapToDouble(Location::getX).min().getAsDouble();
    int y = (int)faces.stream().mapToDouble(Location::getY).min().getAsDouble();
    int z = (int)faces.stream().mapToDouble(Location::getZ).min().getAsDouble();
    return new Location(((Location)faces.get(0)).getWorld(), x, y, z);
  }
  
  public Location getMiddle(int direction) {
    List<Location> locations = null;
    switch (direction) {
      case 2:
        locations = getCuboid().getNorthBorders();
        break;
      case 0:
        locations = getCuboid().getSouthBorders();
        break;
      case 3:
        locations = getCuboid().getEastBorders();
        break;
      case 1:
        locations = getCuboid().getWestBorders();
        break;
    } 
    if (locations == null)
      return null; 
    int xSum = 0;
    int ySum = 0;
    int zSum = 0;
    for (Location location : locations) {
      xSum += location.getBlockX();
      ySum += location.getBlockY();
      zSum += location.getBlockZ();
    } 
    return new Location(
        getSpawnLocation().getWorld(), xSum / locations
        .size(), ySum / locations
        .size(), zSum / locations
        .size());
  }
  
  public String[] getFailureMessage(String structureName) {
    return new String[] { "&cVous n'avez pas assez de place pour faire apparaître la structure.", "&eUtilisez la commande &6/structure " + structureName + " &epour réessayer." };
  }
  
  public static String[] getAlertMessage(String structureName) {
    return new String[] { "&eUtilisez la commande &6/structure " + structureName + " &epour faire apparaître la structure." };
  }
  
  public EntityPlayer getPlayer() {
    return PlayerUtils.getPlayer(this.spawnLocation.getWorld(), this.playerUniqueId);
  }
  
  public boolean isExpired() {
    if (this.expirationMillis == -1L)
      return false; 
    return (this.expirationMillis < System.currentTimeMillis());
  }
  
  public abstract void init();
  
  public abstract boolean canSpawn(Location paramLocation);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\AbstractStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package fr.paladium.palamod.modules.luckyblock.structures.sound;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockBrightSound;
import fr.paladium.palamod.modules.luckyblock.constants.LuckyBlockConstants;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.StructureManager;
import fr.paladium.palamod.modules.luckyblock.structures.sound.enums.SoundTurn;
import fr.paladium.palamod.modules.luckyblock.structures.sound.objects.SoundWave;
import fr.paladium.palamod.modules.luckyblock.structures.sound.tasks.SoundWaveRunnable;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;

public abstract class SoundStructure extends AbstractStructure {
  private final UUID uniqueId;
  
  private final UUID targetedUniqueId;
  
  private final Location blockPlacedLocation;
  
  private final List<SoundWave> waves;
  
  private final int structureLevel;
  
  private int currentWaveIndex;
  
  private Timer timer;
  
  private int score;
  
  private int maxScore;
  
  public UUID getUniqueId() {
    return this.uniqueId;
  }
  
  public UUID getTargetedUniqueId() {
    return this.targetedUniqueId;
  }
  
  public Location getBlockPlacedLocation() {
    return this.blockPlacedLocation;
  }
  
  public List<SoundWave> getWaves() {
    return this.waves;
  }
  
  public int getStructureLevel() {
    return this.structureLevel;
  }
  
  public int getCurrentWaveIndex() {
    return this.currentWaveIndex;
  }
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public int getScore() {
    return this.score;
  }
  
  public int getMaxScore() {
    return this.maxScore;
  }
  
  public SoundStructure(int maxScore, int structureLevel, Location spawnLocation, EntityPlayer player, int width, int length, int height) {
    super(width, length, height, spawnLocation, 
        
        System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2L), true, true, player
        
        .func_110124_au());
    this.uniqueId = UUID.randomUUID();
    this.structureLevel = structureLevel;
    this.score = 0;
    this.maxScore = maxScore;
    this.targetedUniqueId = player.func_110124_au();
    this.waves = new ArrayList<>();
    this.currentWaveIndex = 0;
    this.blockPlacedLocation = spawnLocation.clone();
    initWaves();
  }
  
  public void init() {
    fillBorders(Blocks.field_150343_Z, true);
    fill(Blocks.field_150350_a, getCuboid().getTopBorders(), true);
    getDoorBlocks().forEach(location -> addBlock(LocatedBlock.builder().block(Blocks.field_150350_a).location(location).build(), true));
  }
  
  public void onExpire() {
    super.onExpire();
    giveBack();
    stopTask();
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    if (!ret)
      return false; 
    startTask();
    StructureManager.getInstance().addStructure(this);
    EntityPlayer player = getTargetedPlayer();
    if (player == null)
      return false; 
    Location center = getCuboid().getCenter();
    EventUtils.teleportPlayer((EntityPlayerMP)player, center.getX(), center.getY(), center.getZ());
    return true;
  }
  
  private ItemStack toItemStack() {
    ItemStack stack = new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, this.structureLevel);
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74768_a("score", this.maxScore);
    int maximalScore = this.waves.stream().mapToInt(SoundWave::getSoundAmount).sum();
    compound.func_74768_a("maximalScore", maximalScore);
    stack.func_77982_d(compound);
    return stack;
  }
  
  public void giveBack() {
    ItemStack stack = toItemStack();
    Location center = getCuboid().getCenter();
    PlayerUtils.dropItemStack(center.getWorld(), center.getX(), center.getY(), center.getZ(), stack);
  }
  
  public void onExpire(TileEntity tile) {
    super.onExpire(tile);
  }
  
  public boolean canSpawn() {
    List<Chunk> chunks = StructureUtils.getChunks(getCuboid());
    return (!StructureUtils.isClaimed(chunks) && 
      getPlacedBlocks().stream().allMatch(located -> canSpawn(located.getLocation())));
  }
  
  public boolean canSpawn(Location location) {
    if (this.blockPlacedLocation.equals(location))
      return true; 
    EntityPlayer player = getTargetedPlayer();
    if (player == null)
      return false; 
    return (StructureUtils.isAirAtLocation(location) && 
      StructureUtils.isPlayableAtLocation(location) && 
      
      EventUtils.canInteract(player, location.getX(), location.getY(), location.getZ()));
  }
  
  public String[] getConditions() {
    return new String[] { "§eImpossible de placer cette structure car elle ne respecte pas les conditions suivantes :", "§7- §cNe peut pas être placé dans une zone protégée", "§7- §cNe peut pas être placé dans une zone non jouable", "§7- §cNe peut pas être placé dans une zone claim", "§7- §cDoit etre placé dans une zone libre faisant du §e" + (
        
        getWidth() + 1) + "x" + (
        getLength() + 1) + "x" + getHeight() + "§c." };
  }
  
  public void stopTask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate((TimerTask)new SoundWaveRunnable(this), 0L, LuckyBlockConstants.DANCE_TASK_PERIOD + 500L);
  }
  
  public LocatedBlock getRandomSoundBlock() {
    List<LocatedBlock> soundBlocks = getSoundLocatedBlocks();
    if (soundBlocks == null || soundBlocks.isEmpty())
      return null; 
    Random random = new Random();
    return soundBlocks.get(random.nextInt(soundBlocks.size()));
  }
  
  public LocatedBlock getLocatedBlock(Location location) {
    return getPlacedBlocks()
      .stream()
      .filter(locatedBlock -> (locatedBlock.getLocation().equals(location) && locatedBlock.getBlock().equals(BlocksRegister.BRIGHT_SOUND)))
      
      .findFirst()
      .orElse(null);
  }
  
  public void resetGame() {
    this.waves.clear();
    this.currentWaveIndex = 0;
    if (this.score > this.maxScore)
      this.maxScore = this.score; 
    this.score = 0;
    initWaves();
    startTask();
  }
  
  public boolean sound(LocatedBlock located, SoundWave wave, SoundTurn turn) {
    Location location = located.getLocation();
    Block block = location.getBlock();
    if (!block.equals(BlocksRegister.BRIGHT_SOUND))
      return false; 
    BlockBrightSound brightSound = (BlockBrightSound)block;
    if (brightSound.activateBypass(location.getWorld(), (int)location.getX(), (int)location.getY(), (int)location.getZ())) {
      boolean ret = wave.put(located, turn);
      if (ret && turn == SoundTurn.PLAYER)
        this.score++; 
      return ret;
    } 
    return false;
  }
  
  public List<LocatedBlock> getSoundLocatedBlocks() {
    SoundWave wave = getWave(this.currentWaveIndex);
    if (wave == null)
      return Collections.emptyList(); 
    return (List<LocatedBlock>)getPlacedBlocks()
      .stream()
      .filter(locatedBlock -> {
          if (!locatedBlock.getBlock().equals(BlocksRegister.BRIGHT_SOUND))
            return false; 
          BlockBrightSound block = (BlockBrightSound)locatedBlock.getBlock();
          Location location = locatedBlock.getLocation();
          return !block.isState(location.getWorld(), (int)location.getX(), (int)location.getY(), (int)location.getZ());
        }).collect(Collectors.toList());
  }
  
  public void addWave(SoundWave wave) {
    if (this.waves.contains(wave))
      throw new IllegalArgumentException("Wave already exists"); 
    this.waves.add(wave);
  }
  
  public boolean isCurrentWaveFinished(SoundTurn turn) {
    return isWaveFinished(this.currentWaveIndex, turn);
  }
  
  public boolean isWaveFinished(int waveIndex, SoundTurn turn) {
    SoundWave wave = getWave(waveIndex);
    if (wave == null)
      return false; 
    for (int start = 0; start < wave.getSoundAmount(); start++) {
      if (!wave.isSoundFinished(start, turn))
        return false; 
    } 
    return true;
  }
  
  public SoundWave getWave(int waveIndex) {
    try {
      return this.waves.get(waveIndex);
    } catch (IndexOutOfBoundsException e) {
      return null;
    } 
  }
  
  public boolean hasNextWave() {
    return (this.currentWaveIndex + 1 < this.waves.size());
  }
  
  public boolean nextWave() {
    if (!hasNextWave())
      return false; 
    this.currentWaveIndex++;
    startTask();
    return true;
  }
  
  public List<Location> getDoorBlocks() {
    Location minLocation = getMinLocation(getCuboid().getNorthBorders());
    return Arrays.asList(new Location[] { minLocation
          .clone(1.0D, 2.0D, 0.0D), minLocation
          .clone(1.0D, 1.0D, 0.0D) });
  }
  
  public EntityPlayer getTargetedPlayer() {
    return PlayerUtils.getPlayer(getSpawnLocation().getWorld(), this.targetedUniqueId);
  }
  
  public boolean isOwner(EntityPlayer player) {
    return this.targetedUniqueId.equals(player.func_110124_au());
  }
  
  public abstract void initWaves();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\SoundStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
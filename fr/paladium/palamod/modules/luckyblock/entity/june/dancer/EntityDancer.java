package fr.paladium.palamod.modules.luckyblock.entity.june.dancer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.constants.LuckyBlockConstants;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.objects.DancePattern;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.tasks.DancingRunnable;
import fr.paladium.palamod.modules.luckyblock.events.june.DanceEventHandler;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.BattleDanse;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.renders.june.RenderDancer;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityDancer extends EntityLiving implements IEntityAdditionalSpawnData {
  private DancePattern dancePattern;
  
  private String skinPath;
  
  private long expirationMillis;
  
  private String name;
  
  private int danceIndex;
  
  private UUID targetedPlayerId;
  
  private Timer timer;
  
  private Location spawnLocation;
  
  private Location currentLocation;
  
  public void setDancePattern(DancePattern dancePattern) {
    this.dancePattern = dancePattern;
  }
  
  public void setSkinPath(String skinPath) {
    this.skinPath = skinPath;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setDanceIndex(int danceIndex) {
    this.danceIndex = danceIndex;
  }
  
  public void setTargetedPlayerId(UUID targetedPlayerId) {
    this.targetedPlayerId = targetedPlayerId;
  }
  
  public void setTimer(Timer timer) {
    this.timer = timer;
  }
  
  public void setSpawnLocation(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }
  
  public void setCurrentLocation(Location currentLocation) {
    this.currentLocation = currentLocation;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public int getDanceIndex() {
    return this.danceIndex;
  }
  
  public UUID getTargetedPlayerId() {
    return this.targetedPlayerId;
  }
  
  public Timer getTimer() {
    return this.timer;
  }
  
  public Location getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public EntityDancer(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
  }
  
  public EntityDancer(World world, DancePattern dancePattern, EntityPlayer targetedPlayer) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.dancePattern = dancePattern;
    this.name = dancePattern.getUsername();
    this.danceIndex = 0;
    this.targetedPlayerId = targetedPlayer.func_110124_au();
    this.currentLocation = this.spawnLocation;
    this.expirationMillis = System.currentTimeMillis() + LuckyBlockConstants.DANCER_EXPIRATION_MILLIS;
    this.skinPath = dancePattern.getSkinPath();
    Location safeLocation = findSafePosition(new Location((Entity)targetedPlayer));
    this.spawnLocation = safeLocation;
    func_70107_b(safeLocation.getX(), safeLocation.getY(), safeLocation.getZ());
  }
  
  public Location findSafePosition(Location location) {
    Location safeLocation = location.clone();
    List<Location> options = new ArrayList<>();
    for (int i = 3; i > 0; i--) {
      Location testLocation = DanceDirection.getRelativeLocation(safeLocation, DanceDirection.FRONT);
      options.add(testLocation);
      safeLocation = testLocation.clone();
    } 
    safeLocation = null;
    Collections.reverse(options);
    Iterator<Location> iterator = options.iterator();
    if (iterator.hasNext()) {
      Location option = iterator.next();
      safeLocation = option;
    } 
    if (safeLocation == null)
      safeLocation = location.clone(); 
    return new Location(safeLocation.getWorld(), safeLocation
        .getX(), safeLocation.getY(), safeLocation.getZ(), safeLocation.getYaw(), safeLocation
        .getPitch());
  }
  
  public void put(EntityPlayer player) {
    DanceEventHandler.putDancer(player, this);
  }
  
  public void start(EntityPlayer player) {
    this.field_70170_p.func_72838_d((Entity)this);
    put(player);
    startTask();
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    if (this.spawnLocation != null)
      this.field_70759_as = this.spawnLocation.getYaw(); 
    if (this.field_70170_p.field_72995_K)
      return; 
    if (System.currentTimeMillis() >= this.expirationMillis) {
      stopTask();
      func_70106_y();
      DanceEventHandler.removeDancer(this.targetedPlayerId);
    } 
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(LuckyBlockConstants.DANCER_NAME_ID.intValue(), "");
    this.field_70180_af.func_75682_a(LuckyBlockConstants.DANCER_SKIN_ID.intValue(), "textures/entity/steve.png");
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
  }
  
  public boolean func_70067_L() {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public void func_70108_f(Entity entity) {}
  
  public void func_70100_b_(EntityPlayer player) {}
  
  public boolean func_70097_a(DamageSource source, float amount) {
    return false;
  }
  
  public void func_70645_a(DamageSource source) {
    super.func_70645_a(source);
    stopTask();
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    super.func_70109_d(compound);
    if (this.targetedPlayerId == null || this.spawnLocation == null || this.name == null || this.skinPath == null || this.expirationMillis == 0L)
      return; 
    compound.func_74778_a("name", this.name);
    compound.func_74778_a("skin", this.skinPath);
    compound.func_74778_a("targetedPlayerId", this.targetedPlayerId.toString());
    compound.func_74778_a("spawnX", this.spawnLocation.getWorldName());
    compound.func_74780_a("spawnX", this.spawnLocation.getX());
    compound.func_74780_a("spawnY", this.spawnLocation.getY());
    compound.func_74780_a("spawnZ", this.spawnLocation.getZ());
    compound.func_74776_a("spawnYaw", this.spawnLocation.getYaw());
    compound.func_74776_a("spawnPitch", this.spawnLocation.getPitch());
    compound.func_74772_a("expirationMillis", this.expirationMillis);
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
    if (!compound.func_74764_b("targetedPlayerId") || !compound.func_74764_b("spawnX") || 
      !compound.func_74764_b("spawnX") || !compound.func_74764_b("spawnY") || 
      !compound.func_74764_b("spawnZ") || !compound.func_74764_b("spawnYaw") || 
      !compound.func_74764_b("spawnPitch") || !compound.func_74764_b("name") || 
      !compound.func_74764_b("skin") || !compound.func_74764_b("expirationMillis"))
      return; 
    this.targetedPlayerId = UUID.fromString(compound.func_74779_i("targetedPlayerId"));
    this.name = compound.func_74779_i("name");
    this.skinPath = compound.func_74779_i("skin");
    this.expirationMillis = compound.func_74763_f("expirationMillis");
    this
      
      .spawnLocation = new Location(compound.func_74779_i("spawnX"), compound.func_74769_h("spawnX"), compound.func_74769_h("spawnY"), compound.func_74769_h("spawnZ"), compound.func_74760_g("spawnYaw"), compound.func_74760_g("spawnPitch"));
  }
  
  public String func_70005_c_() {
    return "§7(§aDanseur§7) §6" + getName();
  }
  
  public EntityPlayer getTargetedPlayer() {
    if (this.targetedPlayerId == null)
      return null; 
    return PlayerUtils.getPlayer(this.field_70170_p, this.targetedPlayerId);
  }
  
  public void giveReward() {
    EntityPlayer player = getTargetedPlayer();
    if (player == null)
      return; 
    DanceEventHandler.removeDancer(player);
    stopTask();
    func_70106_y();
    PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound(null), (EntityPlayerMP)player);
    PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(ItemsRegister.RADIO));
  }
  
  public void stopTask() {
    EntityPlayer player = getTargetedPlayer();
    if (player != null)
      ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(false, BattleDanse.class.getName()), (EntityPlayerMP)player); 
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  public void startTask() {
    stopTask();
    this.timer = new Timer();
    getDancePattern();
    this.timer.scheduleAtFixedRate((TimerTask)new DancingRunnable(this), 0L, LuckyBlockConstants.DANCE_TASK_PERIOD);
  }
  
  public boolean nextPattern() {
    DancePattern currentPattern = getDancePattern();
    boolean next = false;
    if (currentPattern == LuckyBlockConstants.LORN_DANCE_PATTERN) {
      this.dancePattern = LuckyBlockConstants.GOLDORAK_DANCE_PATTERN;
      next = true;
    } else if (currentPattern == LuckyBlockConstants.GOLDORAK_DANCE_PATTERN) {
      this.dancePattern = LuckyBlockConstants.FUZE_DANCE_PATTERN;
      next = true;
    } 
    if (next) {
      this.danceIndex = 0;
      stopTask();
      startTask();
    } 
    return next;
  }
  
  public DancePattern getDancePattern() {
    if (this.dancePattern == null) {
      stopTask();
      this.dancePattern = LuckyBlockConstants.LORN_DANCE_PATTERN;
    } 
    setName(this.dancePattern.getUsername());
    setSkin(this.dancePattern.getSkinPath());
    return this.dancePattern;
  }
  
  public Location getCurrentLocation() {
    if (this.currentLocation == null)
      this.currentLocation = this.spawnLocation.clone(); 
    return this.currentLocation;
  }
  
  public boolean dance(DanceDirection direction) {
    if (direction == null)
      return false; 
    Location location = DanceDirection.getRelativeLocation(getCurrentLocation(), direction);
    if (location.getBlock() != null && location.getBlock() != Blocks.field_150350_a)
      return false; 
    if (direction == DanceDirection.JUMP) {
      func_70107_b(location.getX(), location.getY(), location.getZ());
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
            public void run() {
              EntityDancer.this.func_70107_b(EntityDancer.this.getCurrentLocation().getX(), EntityDancer.this.getCurrentLocation().getY(), EntityDancer.this.getCurrentLocation().getZ());
            }
          }500L);
    } else {
      func_70107_b(location.getX(), location.getY(), location.getZ());
      this.currentLocation = location;
    } 
    this.danceIndex++;
    return true;
  }
  
  public void reset() {
    resetPosition();
    this.danceIndex = 0;
    startTask();
  }
  
  public void resetPosition() {
    this.currentLocation = this.spawnLocation.clone();
    func_70107_b(this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ());
  }
  
  public ResourceLocation getSkin() {
    String path = getSkinPath();
    if (path == null || path.isEmpty())
      return RenderDancer.STEVE; 
    return new ResourceLocation(path);
  }
  
  public void setSkin(String skinPath) {
    this.skinPath = skinPath;
    this.field_70180_af.func_75692_b(LuckyBlockConstants.DANCER_SKIN_ID.intValue(), skinPath);
  }
  
  public DanceDirection getDanceDirection(int index) {
    List<DanceDirection> directions = getDancePattern().getDirections();
    if (index < 0)
      return directions.get(0); 
    if (index > directions.size() - 1)
      return DanceDirection.FINISHED; 
    return directions.get(index);
  }
  
  public DanceDirection getNextDanceDirection(int index) {
    List<DanceDirection> directions = getDancePattern().getDirections();
    if (index == 0)
      return directions.get(0); 
    if (index > directions.size() - 1)
      return DanceDirection.FINISHED; 
    return directions.get(index);
  }
  
  public DanceDirection getNextDanceDirection() {
    return getNextDanceDirection(this.danceIndex);
  }
  
  public String getName() {
    String currentName = this.field_70180_af.func_75681_e(LuckyBlockConstants.DANCER_NAME_ID.intValue());
    return (currentName != null) ? currentName : this.name;
  }
  
  public void setName(String name) {
    this.name = name;
    this.field_70180_af.func_75692_b(LuckyBlockConstants.DANCER_NAME_ID.intValue(), name);
  }
  
  public String getSkinPath() {
    String currentSkinPath = this.field_70180_af.func_75681_e(LuckyBlockConstants.DANCER_SKIN_ID.intValue());
    return (currentSkinPath != null) ? currentSkinPath : this.skinPath;
  }
  
  public void writeSpawnData(ByteBuf buffer) {}
  
  public void readSpawnData(ByteBuf additionalData) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\june\dancer\EntityDancer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
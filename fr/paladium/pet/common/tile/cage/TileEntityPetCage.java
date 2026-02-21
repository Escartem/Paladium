package fr.paladium.pet.common.tile.cage;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.capture.PetCaptureEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.Interval;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityPetCage extends TileEntity {
  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public void setStatus(CageStatus status) {
    this.status = status;
  }
  
  public void setNextStepMillis(long nextStepMillis) {
    this.nextStepMillis = nextStepMillis;
  }
  
  public void setLastInteractionMillis(long lastInteractionMillis) {
    this.lastInteractionMillis = lastInteractionMillis;
  }
  
  public void setLastTrapMillis(long lastTrapMillis) {
    this.lastTrapMillis = lastTrapMillis;
  }
  
  public void setActiveSinceMillis(long activeSinceMillis) {
    this.activeSinceMillis = activeSinceMillis;
  }
  
  public void setPet(String pet) {
    this.pet = pet;
  }
  
  public void setNotified(boolean notified) {
    this.notified = notified;
  }
  
  public void setPlayer(EntityPlayerMP player) {
    this.player = player;
  }
  
  private static final Interval CATCH_CHANCE_INTERVAL = Interval.of(0.0F, 50.0F);
  
  private static final long MINIMUM_CHECK_MILLIS = TimeUnit.MINUTES.toMillis(2L);
  
  private static final long MINIMUM_ACTIVE_TIME_MILLIS = TimeUnit.MINUTES.toMillis(2L);
  
  private static final long EXPIRATION_TIME_MILLIS = TimeUnit.MINUTES.toMillis(5L);
  
  private static final long NEXT_STEP_MILLIS = TimeUnit.MINUTES.toMillis(2L);
  
  public UUID getOwnerId() {
    return this.ownerId;
  }
  
  public int getScore() {
    return this.score;
  }
  
  public CageStatus getStatus() {
    return this.status;
  }
  
  public long getNextStepMillis() {
    return this.nextStepMillis;
  }
  
  public long getLastInteractionMillis() {
    return this.lastInteractionMillis;
  }
  
  public long getLastTrapMillis() {
    return this.lastTrapMillis;
  }
  
  public long getActiveSinceMillis() {
    return this.activeSinceMillis;
  }
  
  public String getPet() {
    return this.pet;
  }
  
  public boolean isNotified() {
    return this.notified;
  }
  
  private UUID ownerId = null;
  
  private CageStatus status = CageStatus.UNFILLED;
  
  private int score = 0;
  
  private long nextStepMillis = 0L;
  
  private long lastInteractionMillis = 0L;
  
  private long lastTrapMillis = 0L;
  
  private long activeSinceMillis = 0L;
  
  private String pet = PetCommonProxy.getInstance().findRandomDefaultPet();
  
  private boolean notified = false;
  
  public static final String OWNER_ID_TAG = "ownerId";
  
  public static final String SCORE_TAG = "score";
  
  public static final String STATUS_TAG = "status";
  
  public static final String LAST_INTERACTION_MILLIS_TAG = "lastInteractionMillis";
  
  public static final String LAST_TRAP_MILLIS_TAG = "lastTrapMillis";
  
  public static final String ACTIVE_SINCE_MILLIS_TAG = "activeSinceMillis";
  
  public static final String NEXT_STEP_MILLIS_TAG = "nextStepMillis";
  
  public static final String PET_TAG = "pet";
  
  private static final float MINIMUM_CATCH_CHANCE = 15.0F;
  
  private static final long DEFAULT_MILLIS_VALUE = 0L;
  
  private EntityPlayerMP player;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K || this.status == CageStatus.UNFILLED)
      return; 
    long now = System.currentTimeMillis();
    if (CageStatus.isPlaying(this.status)) {
      notifyPlay(getPlayer(), now);
      handleExpiration(now);
      return;
    } 
    if (!isActive(now) || !canCatch(now))
      return; 
    EntityPlayerMP player = getPlayer();
    if (trap(now) && player != null) {
      this.lastInteractionMillis = now;
      PetTranslateEnum trapped = PetTranslateEnum.MESSAGE_TRAPPED_SUCCESSFULLY;
      trapped.message((ICommandSender)player);
      trapped.notification(player);
      updateClientSide(this);
    } 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("ownerId") || !compound.func_74764_b("status"))
      return; 
    this.ownerId = UUIDUtils.parseUUID(compound.func_74779_i("ownerId"));
    this.score = compound.func_74762_e("score");
    this.status = CageStatus.values()[compound.func_74762_e("status")];
    this.nextStepMillis = compound.func_74763_f("nextStepMillis");
    this.lastInteractionMillis = compound.func_74763_f("lastInteractionMillis");
    this.lastTrapMillis = compound.func_74763_f("lastTrapMillis");
    this.activeSinceMillis = compound.func_74763_f("activeSinceMillis");
    this.pet = compound.func_74779_i("pet");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    if (this.ownerId == null || this.status == null || this.pet == null)
      return; 
    compound.func_74778_a("ownerId", UUIDUtils.toString(this.ownerId));
    compound.func_74768_a("score", this.score);
    compound.func_74768_a("status", this.status.ordinal());
    compound.func_74772_a("nextStepMillis", this.nextStepMillis);
    compound.func_74772_a("lastInteractionMillis", this.lastInteractionMillis);
    compound.func_74772_a("lastTrapMillis", this.lastTrapMillis);
    compound.func_74772_a("activeSinceMillis", this.activeSinceMillis);
    compound.func_74778_a("pet", this.pet);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public void handleExpiration(long now) {
    if (!isExpired(now))
      return; 
    EntityPlayerMP player = PlayerUtils.getPlayer(this.ownerId);
    this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    if (player != null) {
      PetTranslateEnum expired = PetTranslateEnum.MESSAGE_FAMILIAR_ESCAPED;
      expired.message((ICommandSender)player);
      expired.notification(player);
    } 
  }
  
  private float getMinimumCatchChance() {
    return 15.0F;
  }
  
  public boolean trap(long now) {
    this.lastInteractionMillis = now;
    this.lastTrapMillis = now;
    float chance = getMinimumCatchChance();
    if (CATCH_CHANCE_INTERVAL.isInferiorOrEquals(chance)) {
      this.status = CageStatus.STEP_1;
      return true;
    } 
    return false;
  }
  
  public void fill(EntityPlayerMP player) {
    this.ownerId = player.func_110124_au();
    this.status = CageStatus.FILLED;
    this.activeSinceMillis = System.currentTimeMillis();
  }
  
  public void updateScore(EntityPlayerMP player, int score) {
    long now = System.currentTimeMillis();
    this.lastInteractionMillis = now;
    this.score += score;
    PetTranslateEnum.MESSAGE_CURRENT_SCORE.message((ICommandSender)player, new Object[] { Integer.valueOf(this.score), Integer.valueOf(this.status.getScore()) });
    if (score < 0) {
      CageStatus status = CageStatus.getStep(this.score);
      if (status != null && status != this.status) {
        this.status = status;
        PetTranslateEnum.MESSAGE_DERANK.message((ICommandSender)player, new Object[] { this.status.getTranslate().text() });
      } 
    } 
    if (this.score <= 0) {
      onFail(player);
      return;
    } 
    if (this.score >= this.status.getScore()) {
      if (this.status == CageStatus.STEP_3) {
        onWin(player);
        return;
      } 
      onSuccess(player, now);
    } 
  }
  
  private void onWin(EntityPlayerMP player) {
    this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    DoubleLocation location = new DoubleLocation(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    PetCaptureEvent event = new PetCaptureEvent((EntityPlayer)player, pet, location);
    if (pet == null || pet.has() || MinecraftForge.EVENT_BUS.post((Event)event))
      return; 
    PetTranslateEnum.MESSAGE_WIN.message((ICommandSender)player);
    pet.unlock(player, this.pet);
  }
  
  private void onSuccess(EntityPlayerMP player, long now) {
    CageStatus oldStatus = this.status;
    this.status = CageStatus.getNextStep(this.status);
    this.nextStepMillis = now + NEXT_STEP_MILLIS;
    this.notified = false;
    PetTranslateEnum success = PetTranslateEnum.MESSAGE_STEP_SUCCESSFULLY;
    String duration = DurationConverter.fromMillisToString(NEXT_STEP_MILLIS);
    success.message((ICommandSender)player, new Object[] { oldStatus.getTranslate().text(), duration });
    success.notification(player, new Object[] { oldStatus.getTranslate().text(), duration });
  }
  
  private void onFail(EntityPlayerMP player) {
    player.func_70097_a(DamageSource.field_76376_m, 2.0F);
    this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    PetTranslateEnum.MESSAGE_FAMILIAR_ESCAPED.message((ICommandSender)player);
  }
  
  public void notifyPlay(EntityPlayerMP player, long now) {
    if (player == null)
      return; 
    boolean result = canPlayNextStep(now);
    if (result && !this.notified) {
      this.notified = true;
      PetTranslateEnum wait = PetTranslateEnum.MESSAGE_CAN_REPLAY;
      wait.message((ICommandSender)player);
      wait.notification(player);
      updateClientSide(this);
    } 
  }
  
  public boolean canPlayNextStep(long now) {
    return (this.nextStepMillis <= now);
  }
  
  public boolean canPlayNextStep(EntityPlayerMP player, long now) {
    boolean result = canPlayNextStep(now);
    if (!result && player != null) {
      PetTranslateEnum wait = PetTranslateEnum.MESSAGE_WAIT;
      String duration = DurationConverter.fromMillisToString(this.nextStepMillis - now);
      wait.message((ICommandSender)player, new Object[] { duration });
      wait.notification(player, new Object[] { duration });
    } 
    return result;
  }
  
  public boolean isOwner(EntityPlayerMP player) {
    if (this.ownerId == null)
      return true; 
    return this.ownerId.equals(player.func_110124_au());
  }
  
  private boolean isExpired(long now) {
    return (this.activeSinceMillis + EXPIRATION_TIME_MILLIS <= now && this.lastInteractionMillis + EXPIRATION_TIME_MILLIS <= now);
  }
  
  private boolean canCatch(long now) {
    if (this.lastTrapMillis == 0L) {
      this.lastTrapMillis = now;
      return false;
    } 
    return (this.lastTrapMillis + MINIMUM_CHECK_MILLIS <= now);
  }
  
  private boolean isActive(long now) {
    return (this.activeSinceMillis + MINIMUM_ACTIVE_TIME_MILLIS <= now);
  }
  
  public boolean isFilled() {
    return (this.status == CageStatus.FILLED);
  }
  
  public EntityPlayerMP getPlayer() {
    if (this.player == null)
      this.player = PlayerUtils.getPlayer(this.ownerId); 
    if (this.player != null && this.player.field_70128_L)
      this.player = null; 
    return this.player;
  }
  
  public static void updateClientSide(TileEntityPetCage cage) {
    double radius = 32.0D;
    Packet packet = cage.func_145844_m();
    List<EntityPlayerMP> players = cage.func_145831_w().func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_72330_a(cage.field_145851_c - 32.0D, cage.field_145848_d - 32.0D, cage.field_145849_e - 32.0D, cage.field_145851_c + 32.0D, cage.field_145848_d + 32.0D, cage.field_145849_e + 32.0D));
    for (EntityPlayerMP playerArround : players) {
      EntityPlayerMP playerMPArround = playerArround;
      playerMPArround.field_71135_a.func_147359_a(packet);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\tile\cage\TileEntityPetCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */